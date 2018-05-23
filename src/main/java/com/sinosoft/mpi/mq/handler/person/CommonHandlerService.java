package com.sinosoft.mpi.mq.handler.person;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.block.service.BlockService;
import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.match.model.Record;
import com.sinosoft.match.model.RecordPair;
import com.sinosoft.match.service.MatchService;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.model.MpiCombineRec;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.service.DomainSrcLevelService;
import com.sinosoft.mpi.service.IndexIdentifierRelService;
import com.sinosoft.mpi.service.ManOpPersonService;
import com.sinosoft.mpi.service.MpiCombineLevelService;
import com.sinosoft.mpi.service.MpiCombineRecService;
import com.sinosoft.mpi.service.PersonIdxLogService;
import com.sinosoft.mpi.service.PersonIndexService;
import com.sinosoft.mpi.service.PersonIndexUpdateService;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.NumberUtils;

/**
 * 主索引处理过程中的公共服务
 */
@Service
public class CommonHandlerService {

	@Resource
	PersonIndexService personIndexService;
	@Resource
	MpiCombineRecService mpiCombineRecService;
	@Resource
	DomainSrcLevelService domainSrcLevelService;
	@Resource
	IndexIdentifierRelService indexIdentifierRelService;
	@Resource
	MpiCombineLevelService mpiCombineLevelService;
	@Resource
	PersonIdxLogService personIdxLogService;
	@Resource
	ManOpPersonService manOpPersonService;
	@Resource
	BlockService blockService;
	@Resource
	MatchService matchServcie;
	@Resource
	PersonIndexUpdateService personIndexUpdateService;

	/**
	 * 处理人员信息并处理主索引
	 * 
	 * @param personinfo
	 *            人员信息
	 * @return 主索引id
	 */
	public String savePersonIndex(PersonInfo personinfo) {
		Record<PersonInfo> personRecord = new Record<PersonInfo>(personinfo);
		personRecord.setRecordId(personinfo.getFieldPk());
		List<Record<PersonIndex>> records = blockService.findCandidates(personRecord);
		// 找出匹配情况。
		List<RecordPair> pairs = matchServcie.match(personRecord, records);

		RecordPair pair = null;
		// 没有初步匹配者，直接添加索引。
		if (records.size() == 0 || pairs.size() == 0) {
			// 主索引信息入库
			PersonIndex index = addPersonIndex(personinfo);
			return index.getMpiPk();
		} else {
			// 如果信息匹配结果，则合并相关主索引信息,更新关联表
			pair = matchServcie.matchedPair(pairs);
			if (pair != null) {
				PersonIndex personindex = pair.getRightRecord().getObject();
				// 添加索引操作日志
				addIPersonIdxLogService(personinfo.getFieldPk(), personindex.getMpiPk(), personinfo.getDomainId(),
						Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_MERGE,
						"[" + personinfo.getNameCn() + "]合并到主索引[" + pair.getRightRecord().getObject().getNameCn()
								+ "],系统匹配度:" + NumberUtils.toPercentStr(pair.getWeight()));
				// 更新索引信息
				personindex = personIndexUpdateService.updateIndex(personindex, personinfo);
				// 可能匹配值0.4<整体匹配阀值<完全匹配值1.0 同时人工干预表中添加一条记录进行标识
				if (pair.getWeight() >= MatchConfig.getInstanse().getMatchWeightThreshold()
						&& pair.getWeight() < MatchConfig.getInstanse().getAgreementWeightThreshold()) {
					addMenOpPerson(personinfo, personindex.getMpiPk());
				}
				return personindex.getMpiPk();
			} else {
				// 主索引信息入库
				PersonIndex index = addPersonIndex(personinfo);
				return index.getMpiPk();
			}
		}
	}

	/**
	 * 新增人员信息并生成主索引
	 *
	 * @param personinfo
	 *            人员信息
	 * @return 主索引信息
	 */
	private PersonIndex addPersonIndex(PersonInfo personinfo) {
		PersonIndex personindex = personinfo.toPersonIndex();
		if (personinfo.getState() == 0) {// 新增

			personIndexService.save(personindex);

			// 合并信息入库（第一次入库也记录一次合并信息）
			long comboNO = addIndexIdentifierRelService(personinfo.getDomainId(), personindex.getMpiPk(),
					personinfo.getFieldPk(), null);// 居民唯一标志暂为空
			// 主索引合并级别记录入库
			MpiCombineRec mpiCombineRec = personindex.indexToMpiCombineRec();
			mpiCombineRec.setCombineNo(comboNO);
			mpiCombineRecService.save(mpiCombineRec);
			// 域数据源字段级别
			List<DomainSrcLevel> srcLevelcolmap = domainSrcLevelService.queryByDomainID(personinfo.getDomainId());
			// 合并记录字段级别入库
			mpiCombineLevelService.batchAddLevel(personindex, comboNO, personinfo.getSrcLevel(), srcLevelcolmap);
			// 添加索引操作日志
			addIPersonIdxLogService(personinfo.getFieldPk(), personindex.getMpiPk(), personinfo.getDomainId(),
					Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_NEW,
					"新建主索引:[" + personindex.getNameCn() + "]");

		} else if (personinfo.getState() == 1) {// 编辑
			// 获取人员信息主键
			String infoPk = personinfo.getFieldPk();
			// 获取原始主索引信息主键
			String indexPk = indexIdentifierRelService.queryByFieldPK(infoPk).getMpiPk();
			personindex.setMpiPk(indexPk);
			// 按照修改主索引信息
			personIndexService.update(personindex);

			// 添加索引操作日志
			addIPersonIdxLogService(personinfo.getFieldPk(), indexPk, personinfo.getDomainId(),
					Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_NEW,
					"修改主索引:[" + personindex.getNameCn() + "]");

		} else if (personinfo.getState() == 2) {// 删除
			// 获取人员信息主键
			String infoPk = personinfo.getFieldPk();
			// 获取原始主索引信息主键
			String indexPk = indexIdentifierRelService.queryByFieldPK(infoPk).getMpiPk();
			int indexPkSize = indexIdentifierRelService.queryByMpiPK(indexPk).size();
			if (indexPkSize == 1) {
				// 删除关联关系记录
				indexIdentifierRelService.deleteByFieldPk(infoPk);
				personindex.setMpiPk(indexPk);
				// 删除主索引信息记录
				personIndexService.delete(personindex);

				// 添加索引操作日志
				addIPersonIdxLogService(personinfo.getFieldPk(), indexPk, personinfo.getDomainId(),
						Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_NEW,
						"删除主索引:[" + personindex.getNameCn() + "]");

			} else if (indexPkSize > 1) {
				// 删除关联关系记录
				indexIdentifierRelService.deleteByFieldPk(infoPk);
			}

		}

		return personindex;

	}

	/**
	 * 主索引操作日志插入服务
	 */
	public void addIPersonIdxLogService(String person, String index, String domainId, String opType, String opStyle,
			String desc) {
		PersonIdxLog result = new PersonIdxLog();
		result.setOpType(opType);
		result.setOpStyle(opStyle);
		result.setOpTime(DateUtil.getTimeNow(new Date()));
		result.setOpUserId("0");
		result.setOpDesc(desc);
		result.setInfoSour(domainId);
		result.setMpiPk(index);
		result.setFieldPk(person);
		personIdxLogService.save(result);
	}

	/**
	 * 合并信息操作插入服务
	 */
	public Long addIndexIdentifierRelService(String domainId, String mpiPk, String fieldPk, String personid) {
		IndexIdentifierRel iir = new IndexIdentifierRel();
		iir.setCombineRec(-1L);
		iir.setDomainId(domainId);
		iir.setFieldPk(fieldPk);
		iir.setMpiPk(mpiPk);
		iir.setPersonIdentifier(personid);
		indexIdentifierRelService.save(iir);
		return iir.getCombineNo();
	}

	/**
	 * 需人工干预记录
	 */
	public void addMenOpPerson(PersonInfo person, String mpiPk) {
		ManOpPerson manOpPerson = new ManOpPerson();
		manOpPerson.setManOpStatus("0");
		manOpPerson.setManOpTime(DateUtil.getTimeNow(new Date()));
		manOpPerson.setFieldPk(person.getFieldPk());
		manOpPerson.setMpiPk(mpiPk);
		manOpPersonService.save(manOpPerson);
	}

}
