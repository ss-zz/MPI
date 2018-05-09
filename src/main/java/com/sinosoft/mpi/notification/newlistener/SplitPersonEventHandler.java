package com.sinosoft.mpi.notification.newlistener;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.sinosoft.block.service.BlockService;
import com.sinosoft.match.model.Record;
import com.sinosoft.match.model.RecordPair;
import com.sinosoft.match.service.MatchService;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.MpiCombineRec;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.notification.event.IEvent;
import com.sinosoft.mpi.notification.listener.IEventHandler;
import com.sinosoft.mpi.service.BookLogService;
import com.sinosoft.mpi.service.IndexIdentifierRelService;
import com.sinosoft.mpi.service.MpiAbstService;
import com.sinosoft.mpi.service.MpiCombineLevelService;
import com.sinosoft.mpi.service.MpiCombineRecService;
import com.sinosoft.mpi.service.PersonIdxLogService;
import com.sinosoft.mpi.service.PersonIndexService;
import com.sinosoft.mpi.service.PersonIndexUpdateService;
import com.sinosoft.mpi.service.PersonInfoService;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.NumberUtils;

/**
 * 拆分主索引信息
 */
public class SplitPersonEventHandler implements IEventHandler {
	
	@Resource
	private IndexIdentifierRelService indexIdentifierRelService;
	@Resource
	private PersonIndexService personIndexService;
	@Resource
	private PersonInfoService personInfoService;
	@Resource
	private BookLogService bookLogService;
	@Resource
	private PersonIndexUpdateService personIndexUpdateService;
	@Resource
	private MpiCombineRecService mpiCombineRecService;
	@Resource
	private MpiCombineLevelService mpiCombineLevelService;
	@Resource
	private PersonIdxLogService personIdxLogService;
	@Resource
	private PersonInfoService personinfoService;
	@Resource
	private MpiAbstService mpiAbstService;
	@Resource
	private BlockService blockService;
	@Resource
	private MatchService matchServcie;

	private Logger logger = Logger.getLogger(UpdatePersonEventHandler.class);

	@Override
	public void processEvent(IEvent event) {
		Object obj = event.getValue();
		if (obj instanceof PersonInfo) {
			PersonInfo personinfo = (PersonInfo) obj;
			IndexIdentifierRel iir = indexIdentifierRelService.queryByFieldPK(personinfo.getRELATION_PK());
			// 是否有对应的居民信息记录
			if (iir != null) {

				List<IndexIdentifierRel> iirs = indexIdentifierRelService.queryByMpiPK(iir.getMPI_PK());
				int lastMerg = -1;
				// 取当前信息合并记录的上一条
				for (IndexIdentifierRel temp : iirs) {
					lastMerg++;
					if (iir.getCOMBINE_NO().equals(temp.getCOMBINE_NO())) {
						break;
					}
				}
				if (lastMerg != 0) {
					// 取合并记录的对应主索引值,封装成主索引对象
					MpiCombineRec mpiCombineRec = mpiCombineRecService
							.queryByCombineNo(iirs.get(lastMerg - 1).getCOMBINE_NO());
					PersonIndex mergindex = mpiCombineRec.mpiCombineRecToPersonIndex();
					mergindex.setMPI_PK(iir.getMPI_PK());
					// 删除对应合并的记录之后的记录 级联清理合并关系，合并记录及字段合并级别记录
					indexIdentifierRelService.deleteRecurByCombinNo(iirs.get(lastMerg).getCOMBINE_NO());
					// 重新合并相关居民信息
					for (int i = lastMerg + 1; i < iirs.size(); i++) {
						PersonInfo mergInfo = personInfoService.getObject(iirs.get(i).getFIELD_PK());
						mergInfo.setDOMAIN_ID(iirs.get(i).getDOMAIN_ID());
						// 更新索引信息
						mergindex = personIndexUpdateService.updateIndex(mergindex, mergInfo);

						// 添加订阅处理日志
						bookLogService.save(mergInfo.getFIELD_PK(), mergindex.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
						// 添加索引操作日志
						addIPersonIdxLogService(mergInfo.getFIELD_PK(), mergindex.getMPI_PK(), mergInfo.getDOMAIN_ID(),
								Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_AUTO_SPLIT,
								"[" + mergInfo.getNAME_CN() + "]重新合并到主索引[" + mergindex.getNAME_CN());
					}
					// 更新人员摘要
					mpiAbstService.update(mergindex);
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
					for (int i = lastMerg + 1; i < iirs.size(); i++) {
						PersonInfo p = personInfoService.queryPersonsByFieldPK(iirs.get(i).getFIELD_PK());
						p.setDOMAIN_ID(iirs.get(i).getDOMAIN_ID());
						savePersonIndex(p);
					}

				}

			} else {
				// 如果索引信息库不为空，则
				throw new RuntimeException("没有相对应要拆分的居民信息的记录");
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
}
