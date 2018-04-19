package com.sinosoft.mpi.mq.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

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
import com.sinosoft.mpi.service.IBookLogService;
import com.sinosoft.mpi.service.IDomainSrcLevelService;
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
@Service
public class AddPersonHandler {

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

	private Logger logger = Logger.getLogger(AddPersonHandler.class);

	@Resource
	private IMpiAbstService mpiAbstService;
	@Resource
	private IManOpPersonService manOpPersonService;
	@Resource
	private IDomainSrcLevelService domainSrcLevelService;

	/**
	 * 处理消息队列中的人员, 对当前人员信息进行主索引初筛匹配,生成主索引
	 *
	 * @param obj
	 */
	public void handleMessage(PersonInfo personinfo) {
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
				PersonIndex personindex = pair.getRightRecord().getObject();
				// 添加索引操作日志
				addIPersonIdxLogService(personinfo.getFIELD_PK(), personindex.getMPI_PK(), personinfo.getDOMAIN_ID(),
						Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_MERGE,
						"[" + personinfo.getNAME_CN() + "]合并到主索引[" + pair.getRightRecord().getObject().getNAME_CN()
								+ "],系统匹配度:" + NumberUtils.toPercentStr(pair.getWeight()));
				// 更新索引信息
				personindex = personIndexUpdateService.updateIndex(personindex, personinfo);
				// 添加人员摘要
				mpiAbstService.update(personindex);
				// 添加订阅处理日志
				bookLogService.save(personinfo.getFIELD_PK(), personindex.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
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
				if (index.getSTATE() == 0) {
					mpiAbstService.save(index);
				} else if (index.getSTATE() == 1) {
					mpiAbstService.update(index);
				} else if (index.getSTATE() == 2) {
					mpiAbstService.delete(index);
				}

			}
		}
	}

	/**
	 * 新增主索引
	 *
	 * @param personinfo
	 * @return
	 */

	private PersonIndex addPersonIndex(PersonInfo personinfo) {

		/* update WHN 2014-05-26 */
		PersonIndex personindex = personinfo.personInfoToPersonIndex();

		try {
			if (personinfo.getSTATE() == 0) {

				personIndexService.save(personindex);
				String type = personinfo.getTYPE();
				// 合并信息入库（第一次入库也记录一次合并信息）
				String personid = "";
				if ("0".equals(type)) {
					// 城乡居民健康档案编号
					personid = personinfo.getHR_ID();
				} else if ("1".equals(type)) {
					// 医疗服务编号
					personid = personinfo.getMEDICALSERVICE_NO();
				} else if ("3".equals(type)) {
					// 病人ID
					personid = personinfo.getPATIENT_ID();
				}
				long comboNO = addIndexIdentifierRelService(personinfo.getDOMAIN_ID(), personindex.getMPI_PK(),
						personinfo.getFIELD_PK(), personid);
				// 主索引合并级别记录入库
				MpiCombineRec mpiCombineRec = personindex.indexToMpiCombineRec();
				mpiCombineRec.setCOMBINE_NO(comboNO);
				mpiCombineRecService.save(mpiCombineRec);
				// 域数据源字段级别 lpk 2013年4月27日11:04:35
				List<Map<String, Object>> srcLevelcolmap = domainSrcLevelService
						.queryByDomainID(personinfo.getDOMAIN_ID());
				// 合并记录字段级别入库
				mpiCombineLevelService.batchAddLevel(personindex, comboNO, personinfo.getSRC_LEVEL(), srcLevelcolmap);
				// 添加订阅处理日志
				bookLogService.save(personinfo.getFIELD_PK(), personindex.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
				// 添加索引操作日志
				addIPersonIdxLogService(personinfo.getFIELD_PK(), personindex.getMPI_PK(), personinfo.getDOMAIN_ID(),
						Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_NEW,
						"新建主索引:[" + personindex.getNAME_CN() + "]");

			} else if (personinfo.getSTATE() == 1) {
				// 获取人员信息主键
				String infoPk = personinfo.getFIELD_PK();
				// 获取原始主索引信息主键
				String indexPk = indexIdentifierRelService.queryByFieldPK(infoPk).getMPI_PK();
				personindex.setMPI_PK(indexPk);
				// 按照修改主索引信息
				personIndexService.update(personindex);

				// 添加索引操作日志
				addIPersonIdxLogService(personinfo.getFIELD_PK(), indexPk, personinfo.getDOMAIN_ID(),
						Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_NEW,
						"修改主索引:[" + personindex.getNAME_CN() + "]");

			} else if (personinfo.getSTATE() == 2) {
				// 获取人员信息主键
				String infoPk = personinfo.getFIELD_PK();
				// 获取原始主索引信息主键
				String indexPk = indexIdentifierRelService.queryByFieldPK(infoPk).getMPI_PK();
				int indexPkSize = indexIdentifierRelService.queryByMpiPK(indexPk).size();
				if (indexPkSize == 1) {
					// 删除关联关系记录
					indexIdentifierRelService.deleteByFieldPk(infoPk);
					personindex.setMPI_PK(indexPk);
					// 删除主索引信息记录
					personIndexService.delete(personindex);

					// 添加索引操作日志
					addIPersonIdxLogService(personinfo.getFIELD_PK(), indexPk, personinfo.getDOMAIN_ID(),
							Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_NEW,
							"删除主索引:[" + personindex.getNAME_CN() + "]");

				} else if (indexPkSize > 1) {
					// 删除关联关系记录
					indexIdentifierRelService.deleteByFieldPk(infoPk);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

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
	private Long addIndexIdentifierRelService(String domainId, String mpiPk, String fieldPk, String personid) {
		IndexIdentifierRel iir = new IndexIdentifierRel();
		iir.setCOMBINE_REC(-1L);
		iir.setDOMAIN_ID(domainId);
		iir.setFIELD_PK(fieldPk);
		iir.setMPI_PK(mpiPk);
		iir.setPERSON_IDENTIFIER(personid);
		indexIdentifierRelService.save(iir);
		return iir.getCOMBINE_NO();
	}

	/**
	 * 需人工干预记录
	 */
	private void addMenOpPerson(PersonInfo person, String mpiPk) {
		ManOpPerson manOpPerson = new ManOpPerson();
		manOpPerson.setMAN_OP_STATUS("0");
		manOpPerson.setMAN_OP_TIME(DateUtil.getTimeNow(new Date()));
		manOpPerson.setFIELD_PK(person.getFIELD_PK());
		manOpPerson.setMPI_PK(mpiPk);
		manOpPersonService.save(manOpPerson);
	}
}
