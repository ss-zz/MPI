package com.sinosoft.mpi.notification.newlistener;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.sinosoft.block.service.IBlockService;
import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.match.model.Record;
import com.sinosoft.match.model.RecordPair;
import com.sinosoft.match.service.IMatchService;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.model.MpiCombineRec;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.notification.event.IEvent;
import com.sinosoft.mpi.notification.listener.IEventHandler;
import com.sinosoft.mpi.service.IBookLogService;
import com.sinosoft.mpi.service.IIndexIdentifierRelService;
import com.sinosoft.mpi.service.IManOpPersonService;
import com.sinosoft.mpi.service.IMpiAbstService;
import com.sinosoft.mpi.service.IMpiCombineLevelService;
import com.sinosoft.mpi.service.IMpiCombineRecService;
import com.sinosoft.mpi.service.IPersonIdxLogService;
import com.sinosoft.mpi.service.IPersonIndexService;
import com.sinosoft.mpi.service.IPersonIndexUpdateService;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.NumberUtils;

/**
 * 增加主索引信息
 */
public class AddPersonEventHandler implements IEventHandler {
	@Resource
	private IBlockService blockService;

	@Resource
	private IMatchService matchServcie;

	@Resource
	IPersonIdxLogService personIdxLogService;

	@Resource
	IBookLogService bookLogService;

	@Resource
	IMpiCombineRecService mpiCombineRecService;

	@Resource
	IMpiCombineLevelService mpiCombineLevelService;
	@Resource
	private IPersonIndexService personIndexService;

	@Resource
	private IPersonIndexUpdateService personIndexUpdateService;
	@Resource
	private IIndexIdentifierRelService indexIdentifierRelService;

	private Logger logger = Logger.getLogger(AddPersonEventHandler.class);

	@Resource
	private IMpiAbstService mpiAbstService;
	@Resource
	private IManOpPersonService manOpPersonService;

	@Override
	public void processEvent(IEvent event) {

		Object obj = event.getValue();
		if (obj instanceof PersonInfo) {
			PersonInfo personinfo = (PersonInfo) obj;
			/*
			 * IndexIdentifierRel iir = indexIdentifierRelService
			 * .queryByFieldPK(personinfo.getFIELD_PK()); // 查看是否居民是否首次入库 if (iir == null) {
			 */
			Record<PersonInfo> personRecord = new Record<PersonInfo>(personinfo);
			personRecord.setRecordId(personinfo.getFIELD_PK());
			List<Record<PersonIndex>> records = blockService.findCandidates(personRecord);
			// 找出匹配情况。
			List<RecordPair> pairs = matchServcie.match(personRecord, records);

			RecordPair pair = null;
			// 没有初步匹配者，直接添加索引。
			if (records.size() == 0 || pairs.size() == 0) {
				// 主索引信息入库
				PersonIndex index = addPersonIndex(personinfo);
				// 添加人员摘要
				mpiAbstService.save(index);
			} else {
				// 如果信息匹配结果，则合并相关主索引信息,更新关联表
				pair = matchServcie.matchedPair(pairs);
				if (pair != null) {
					PersonIndex personindex = (PersonIndex) pair.getRightRecord().getObject();
					// 更新索引信息
					personindex = personIndexUpdateService.updateIndex(personindex, personinfo);
					// 添加人员摘要
					mpiAbstService.update(personindex);
					// 添加订阅处理日志
					bookLogService.save(personinfo.getFIELD_PK(), personindex.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
					// 添加索引操作日志
					addIPersonIdxLogService(personinfo.getFIELD_PK(), personindex.getMPI_PK(),
							personinfo.getDOMAIN_ID(), Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_MERGE,
							"[" + personinfo.getNAME_CN() + "]合并到主索引[" + personindex.getNAME_CN() + "],系统匹配度:"
									+ NumberUtils.toPercentStr(pair.getWeight()));
					logger.debug("新记录合并入库");
					// TODO lpk update 2013年5月13日
					// TODO 可能匹配值0.4<整体匹配阀值<完全匹配值1.0 同时人工干预表中添加一条记录进行标识
					if (pair.getWeight() >= MatchConfig.getInstanse().getMatchWeightThreshold()
							&& pair.getWeight() < MatchConfig.getInstanse().getAgreementWeightThreshold()) {
						addMenOpPerson(personinfo, personindex.getMPI_PK());
					}
				} else {
					// addPersonIndex(personinfo);
					// 主索引信息入库
					PersonIndex index = addPersonIndex(personinfo);
					// 添加人员摘要
					mpiAbstService.save(index);
				}
			}
			/*
			 * }else { // 如果索引信息库不为空，则 throw new RuntimeException("当前索引库中已合并过该记录" ); }
			 */
		} else {
			logger.error("错误的事件参数,参数应为Personinfo对象,但实际为" + obj);
			throw new RuntimeException("错误的事件参数,参数应为Personinfo对象,但实际为" + obj);
		}

	}

	private PersonIndex addPersonIndex(PersonInfo personinfo) {
		PersonIndex personindex = personinfo.personInfoToPersonIndex();
		personIndexService.save(personindex);
		// 合并信息入库（第一次入库也记录一次合并信息）
		String personid = "";
		if ((String) personinfo.getTYPE() == "0") {
			personid = personinfo.getHR_ID();
		} else {
			personid = personinfo.getMEDICALSERVICE_NO();
		}
		long comboNO = addIndexIdentifierRelService(personinfo.getDOMAIN_ID(), personindex.getMPI_PK(),
				personinfo.getFIELD_PK(), personid);
		// 主索引合并级别记录入库
		MpiCombineRec mpiCombineRec = personindex.indexToMpiCombineRec();
		mpiCombineRec.setCOMBINE_NO(comboNO);
		mpiCombineRecService.save(mpiCombineRec);
		// 合并记录字段级别入库
		mpiCombineLevelService.batchAddLevel(personindex, comboNO, personinfo.getSRC_LEVEL(), null);
		// 添加订阅处理日志
		bookLogService.save(personinfo.getFIELD_PK(), personindex.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
		// 添加索引操作日志
		addIPersonIdxLogService(personinfo.getFIELD_PK(), personindex.getMPI_PK(), personinfo.getDOMAIN_ID(),
				Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_NEW,
				"新建主索引:[" + personindex.getNAME_CN() + "]");
		return personindex;

	}

	/**
	 * 主索引操作日志插入服务
	 */
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

	/**
	 * 合并信息操作插入服务
	 */
	private Long addIndexIdentifierRelService(String domain_id, String mpi_pk, String field_pk, String personid) {
		IndexIdentifierRel iir = new IndexIdentifierRel();
		iir.setCOMBINE_REC(-1l);
		iir.setDOMAIN_ID(domain_id);
		iir.setFIELD_PK(field_pk);
		iir.setMPI_PK(mpi_pk);
		iir.setPERSON_IDENTIFIER(personid);
		indexIdentifierRelService.save(iir);
		return iir.getCOMBINE_NO();
	}

	/* 需人工干预记录 */
	private void addMenOpPerson(PersonInfo person, String mpi_pk) {
		ManOpPerson manOpPerson = new ManOpPerson();
		manOpPerson.setMAN_OP_STATUS("0");
		manOpPerson.setMAN_OP_TIME(DateUtil.getTimeNow(new Date()));
		manOpPerson.setFIELD_PK(person.getFIELD_PK());
		manOpPerson.setMPI_PK(mpi_pk);
		manOpPersonService.save(manOpPerson);
	}

}
