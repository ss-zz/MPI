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
import com.sinosoft.mpi.service.IPersonInfoService;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.NumberUtils;

public class UpdatePersonEventHandler implements IEventHandler {
	@Resource
	IMpiCombineRecService mpiCombineRecService;
	@Resource
	IMpiCombineLevelService mpiCombineLevelService;
	@Resource
	private IIndexIdentifierRelService indexIdentifierRelService;
	@Resource
	private IPersonIndexService personIndexService;
	@Resource
	private IPersonInfoService personInfoService;
	@Resource
	private IBookLogService bookLogService;
	@Resource
	private IPersonIndexUpdateService personIndexUpdateService;
	@Resource
	IPersonIdxLogService personIdxLogService;

	private Logger logger = Logger.getLogger(UpdatePersonEventHandler.class);
	@Resource
	private IMpiAbstService mpiAbstService;

	@Resource
	private IBlockService blockService;

	@Resource
	private IMatchService matchServcie;
	@Resource
	private IManOpPersonService manOpPersonService;

	@Override
	public void processEvent(IEvent event) {

		Object obj = event.getValue();
		if (obj instanceof PersonInfo) {
			PersonInfo personinfo = (PersonInfo) obj;
			logger.debug("relationpk=" + personinfo.getRELATION_PK());
			// 查询本次更新的原记录
			IndexIdentifierRel iir = indexIdentifierRelService.queryByFieldPK(personinfo.getRELATION_PK());

			// 取原记录对应的主索引与居民的合并关系，并定位合并位置
			if (iir != null) {
				List<IndexIdentifierRel> iirs = indexIdentifierRelService.queryByMpiPK(iir.getMPI_PK());
				int lastMerg = -1;
				// 查找出更新的居民信息的合并记录
				// combine_rec指代原合并上一条记录号
				for (IndexIdentifierRel temp : iirs) {
					lastMerg++;
					if (iir.getCOMBINE_NO().equals(temp.getCOMBINE_NO())) {
						break;
					}

				}
				// 如果更新信息相关索引关联不止一个人员信息需要更新当前人员信息并和其他关联人员信息再进行索引合并
				if (lastMerg != 0) {
					// 将主索引恢复到合并位置前的情况
					MpiCombineRec mpiCombineRec = mpiCombineRecService
							.queryByCombineNo(iirs.get(lastMerg - 1).getCOMBINE_NO());
					PersonIndex mergindex = mpiCombineRec.mpiCombineRecToPersonIndex();
					mergindex.setMPI_PK(iir.getMPI_PK());
					mergindex = personIndexUpdateService.updateIndex(mergindex, personinfo);
					// 级联清理当前合并记录后的合并关系，合并记录及字段合并级别记录
					indexIdentifierRelService.deleteRecurByCombinNo(iirs.get(lastMerg).getCOMBINE_NO());
					for (int i = lastMerg + 1; i < iirs.size(); i++) {
						PersonInfo mergInfo = personInfoService.getObject(iirs.get(i).getFIELD_PK());
						mergInfo.setDOMAIN_ID(iirs.get(i).getDOMAIN_ID());
						// 更新索引信息
						mergindex = personIndexUpdateService.updateIndex(mergindex, mergInfo);
						// //添加人员摘要
						mpiAbstService.update(mergindex);
						// 添加订阅处理日志
						bookLogService.save(mergInfo.getFIELD_PK(), mergindex.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
						// 添加索引操作日志
						addIPersonIdxLogService(mergInfo.getFIELD_PK(), mergindex.getMPI_PK(), mergInfo.getDOMAIN_ID(),
								Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_AUTO_MERGE,
								"[" + mergInfo.getNAME_CN() + "]重新合并到主索引[" + mergindex.getNAME_CN());
					}
					savePersonIndex(personinfo);
				} else {
					// 删除主索引及相关记录
					// 级联清理当前合并记录后的合并关系，合并记录及字段合并级别记录
					indexIdentifierRelService.deleteRecurByCombinNo(iirs.get(lastMerg).getCOMBINE_NO());
					for (IndexIdentifierRel temp : iirs) {
						indexIdentifierRelService.delete(temp);
					}
					PersonIndex personindex = new PersonIndex();
					personindex.setMPI_PK(iir.getMPI_PK());
					mpiAbstService.delete(personindex);
					personIndexService.delete(personindex);
					savePersonIndex(personinfo);
					for (int i = lastMerg + 1; i < iirs.size(); i++) {
						PersonInfo p = personInfoService.queryPersonsByFieldPK(iirs.get(i).getFIELD_PK());
						p.setDOMAIN_ID(iirs.get(i).getDOMAIN_ID());
						savePersonIndex(p);
					}
				}

				logger.debug("更新记录");
			} else {
				// 如果索引信息库不为空，则
				throw new RuntimeException("没有相对应要合并居民信息的记录");
			}

		} else {
			logger.error("错误的事件参数,参数应为Personinfo对象,但实际为" + obj);
			throw new RuntimeException("错误的事件参数,参数应为Personinfo对象,但实际为" + obj);
		}
	}

	public void savePersonIndex(PersonInfo personinfo) {
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
				addIPersonIdxLogService(personinfo.getFIELD_PK(), personindex.getMPI_PK(), personinfo.getDOMAIN_ID(),
						Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_MERGE,
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
				// 主索引信息入库
				addPersonIndex(personinfo);
				// 添加人员摘要
				// mpiAbstService.save(index);
			}
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
