package com.sinosoft.mpi.notification.listener;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.sinosoft.block.service.IBlockService;
import com.sinosoft.match.model.Record;
import com.sinosoft.match.model.RecordPair;
import com.sinosoft.match.service.IMatchService;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.dao.IIdentifierDomainDao;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.model.MatchResult;
import com.sinosoft.mpi.model.PersonIdentifier;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.notification.event.IEvent;
import com.sinosoft.mpi.service.IBookLogService;
import com.sinosoft.mpi.service.IIndexIdentifierRelService;
import com.sinosoft.mpi.service.IManOpPersonService;
import com.sinosoft.mpi.service.IMatchResultService;
import com.sinosoft.mpi.service.IPersonIdxLogService;
import com.sinosoft.mpi.service.IPersonIndexService;
import com.sinosoft.mpi.service.IPersonIndexUpdateService;
import com.sinosoft.mpi.service.IPersonInfoService;
import com.sinosoft.mpi.service.MpiAbstService;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.NumberUtils;

/**
 * 添加人员事件处理
 */
public class AddPersonEventHandler implements IEventHandler {
	private static Logger logger = Logger.getLogger(AddPersonEventHandler.class);
	@Resource
	private IBlockService blockService;

	@Resource
	private IMatchService matchServcie;

	@Resource
	private IPersonIndexService personIndexService;

	@Resource
	private IPersonInfoService personInfoService;

	@Resource
	private IIndexIdentifierRelService indexIdentifierRelService;

	@Resource
	private IPersonIdxLogService personIdxLogService;

	@Resource
	private IMatchResultService matchResultService;

	@Resource
	private IManOpPersonService manOpPersonService;

	@Resource
	private IBookLogService bookLogService;

	@Resource
	private IPersonIndexUpdateService personIndexUpdateService;
	@Resource
	private IIdentifierDomainDao identifierDomainDao;

	@Resource
	private MpiAbstService mpiAbstService;

	@Override
	public void processEvent(IEvent event) {
		Object obj = event.getValue();
		if (obj instanceof PersonIdentifier) {
			PersonIdentifier personIdentifier = (PersonIdentifier) obj;
			proccess(personIdentifier);
		} else {
			logger.error("错误的事件参数,参数应为PersonIdentifier对象,但实际为" + obj);
			throw new RuntimeException("错误的事件参数,参数应为PersonIdentifier对象,但实际为" + obj);
		}
	}

	/**
	 * 1.得到患者初步匹配索引 2.找到最佳的匹配者 3.连接匹配索引和患者
	 * 
	 * @param personIdentifier
	 * @author qinshouxin
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void proccess(PersonIdentifier personIdentifier) {
		PersonInfo person = personInfoService.getObject(personIdentifier.getPersonId());
		Record personRecord = new Record(person);
		personRecord.setRecordId(person.getFIELD_PK());
		List<Record<PersonIndex>> records = blockService.findCandidates(personRecord);
		// 找出匹配情况。
		List<RecordPair> pairs = matchServcie.match(personRecord, records);

		PersonIndex index = null;
		RecordPair pair = null;

		// 没有初步匹配者，直接添加索引。
		if (records.size() == 0 || pairs.size() == 0) {
			index = person.personInfoToPersonIndex();
			addIndex(index, personIdentifier);
			// 添加人员摘要
			mpiAbstService.save(index);
			// 添加订阅处理日志
			bookLogService.save(person.getFIELD_PK(), index.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
			// 添加匹配操作日志
			addIPersonIdxLogService(person.getFIELD_PK(), index.getMPI_PK(), personIdentifier.getDomainId(),
					Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_NEW, "新建主索引:[" + index.getNAME_CN() + "]");
		} else {
			pair = matchServcie.matchedPair(pairs);
			if (pair != null) {
				index = (PersonIndex) pair.getRightRecord().getObject();
				addIndex(index, personIdentifier);
				// 更新索引信息
				personIndexUpdateService.updateIndex(person, index.getMPI_PK());
				// 添加订阅处理日志
				bookLogService.save(person.getFIELD_PK(), index.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
				// 添加匹配操作日志
				addIPersonIdxLogService(person.getFIELD_PK(), index.getMPI_PK(), personIdentifier.getDomainId(),
						Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_MERGE,
						"[" + person.getNAME_CN() + "]合并到主索引[" + index.getNAME_CN() + "],系统匹配度:"
								+ NumberUtils.toPercentStr(pair.getWeight()));
			}
		}

		if (index != null) {
			if (pair != null) {
				// 添加匹配度
				addMatchDegree(pair);
			}
		} else {
			// 添加人工干预
			addMenOpPerson(person);
			// 添加匹配度
			for (RecordPair tpair : pairs) {
				addMatchDegree(tpair);
			}

		}

	}

	private void addMenOpPerson(PersonInfo person) {
		ManOpPerson manOpPerson = new ManOpPerson();
		manOpPerson.setMAN_OP_STATUS("0");
		manOpPerson.setMAN_OP_TIME(DateUtil.getTimeNow(new Date()));
		manOpPerson.setFIELD_PK(person.getFIELD_PK());
		manOpPersonService.save(manOpPerson);
	}

	// 增加匹配度
	private void addMatchDegree(RecordPair pair) {
		MatchResult matchResult = new MatchResult();

		matchResult.setMpiPk(pair.getRightRecord().getRecordId());
		matchResult.setFieldPk(pair.getLeftRecord().getRecordId());
		matchResult.setMatchDegree(String.valueOf(pair.getWeight()));
		matchResult.setFieldMatDegrees(pair.getComparisonVector().getScoresAsString());
		matchResultService.save(matchResult);

	}

	// 需要修改
	private void addIPersonIdxLogService(String person, String index, String domainId, String opType, String opStyle,
			String desc) {
		PersonIdxLog result = new PersonIdxLog();
		result.setOpType(opType);
		result.setOpStyle(opStyle);
		result.setOpTime(DateUtil.getTimeNow(new Date()));
		result.setOpUserId("0");
		result.setOpDesc(desc);
		result.setInfoSour(domainId);
		result.setMpipk(index);
		result.setFieldpk(person);
		// 自动标志
		personIdxLogService.save(result);
	}

	// 添加索引以及连接
	private void addIndex(PersonIndex index, PersonIdentifier personIdentifier) {
		// 添加索引
		if (index.getMPI_PK() == null) {
			// 设置索引源数据级别
			IdentifierDomain domain = identifierDomainDao.getByPersonId(personIdentifier.getPersonId());
			index.setDOMAIN_LEVEL(domain.getDOMAIN_LEVEL());
			personIndexService.save(index);
		}
		// 连接索引和居民信息
		IndexIdentifierRel rel = new IndexIdentifierRel();
		rel.setMPI_PK(index.getMPI_PK());
		indexIdentifierRelService.save(rel);
	}

	public void setBlockService(IBlockService blockService) {
		this.blockService = blockService;
	}

	public void setMatchServcie(IMatchService matchServcie) {
		this.matchServcie = matchServcie;
	}

	public void setPersonIndexService(IPersonIndexService personIndexService) {
		this.personIndexService = personIndexService;
	}

	public void setPersonInfoService(IPersonInfoService personInfoService) {
		this.personInfoService = personInfoService;
	}

	public void setIndexIdentifierRelService(IIndexIdentifierRelService indexIdentifierRelService) {
		this.indexIdentifierRelService = indexIdentifierRelService;
	}

	public void setPersonIdxLogService(IPersonIdxLogService personIdxLogService) {
		this.personIdxLogService = personIdxLogService;
	}

	public void setMatchResultService(IMatchResultService matchResultService) {
		this.matchResultService = matchResultService;
	}

	public void setManOpPersonService(IManOpPersonService manOpPersonService) {
		this.manOpPersonService = manOpPersonService;
	}

	public void setBookLogService(IBookLogService bookLogService) {
		this.bookLogService = bookLogService;
	}

	public void setPersonIndexUpdateService(IPersonIndexUpdateService personIndexUpdateService) {
		this.personIndexUpdateService = personIndexUpdateService;
	}

	public void setIdentifierDomainDao(IIdentifierDomainDao identifierDomainDao) {
		this.identifierDomainDao = identifierDomainDao;
	}

}