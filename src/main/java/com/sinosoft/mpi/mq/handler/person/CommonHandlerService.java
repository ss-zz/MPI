package com.sinosoft.mpi.mq.handler.person;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.block.service.BlockService;
import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.match.model.Record;
import com.sinosoft.match.model.RecordPair;
import com.sinosoft.match.service.MatchService;
import com.sinosoft.mpi.dics.IndexRegisterType;
import com.sinosoft.mpi.dics.LogOpStyle;
import com.sinosoft.mpi.dics.LogOpType;
import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.model.MatchResult;
import com.sinosoft.mpi.model.MpiCombineRec;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.service.DomainSrcLevelService;
import com.sinosoft.mpi.service.IndexIdentifierRelService;
import com.sinosoft.mpi.service.ManOpPersonService;
import com.sinosoft.mpi.service.MatchResultService;
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
	MatchResultService matchResultService;
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
		// 查询初筛数据
		List<Record<PersonIndex>> records = blockService.findCandidates(personRecord);
		// 找出匹配情况。
		List<RecordPair> pairs = matchServcie.match(personRecord, records);

		// 最符合的匹配对象
		RecordPair pair = null;
		// 没有初步匹配者或者匹配度太低，则直接添加索引。
		if (records.size() == 0 || pairs.size() == 0 || (pair = matchServcie.matchedPair(pairs)) == null) {
			// 主索引信息入库
			PersonIndex index = addPersonIndex(personinfo);
			return index.getMpiPk();
		} else {
			// 如果信息匹配结果，则合并相关主索引信息,更新关联表
			PersonIndex personIndexPair = pair.getRightRecord().getObject();
			// 完全匹配的数据才更新索引，否则需要人工干预
			if (pair.getWeight() >= MatchConfig.getInstanse().getMatchWeightThreshold()
					&& pair.getWeight() < MatchConfig.getInstanse().getAgreementWeightThreshold()) {// 不完全匹配
				addMenOpPerson(personinfo, personIndexPair.getMpiPk());

				// 可能匹配的对（不包含完全匹配）
				List<RecordPair> possiblePairs = matchServcie.possibleMatchedPairs(pairs);
				// 将匹配结果保存到匹配结果表中
				List<MatchResult> matchResults = new ArrayList<>();
				for (RecordPair possiblePair : possiblePairs) {
					MatchResult matchResult = new MatchResult();
					matchResult.setFieldMatDegrees(possiblePair.getComparisonVector().getScoresAsString());
					matchResult.setMatchDegree("" + possiblePair.getWeight());
					matchResult.setMpiPk(possiblePair.getRightRecord().getObject().getMpiPk());
					matchResult.setFieldPk(personinfo.getFieldPk());
					matchResults.add(matchResult);
				}
				matchResultService.batchSave(matchResults);

			}
			// 更新索引信息
			personIndexPair = personIndexUpdateService.updateIndex(personIndexPair, personinfo, LogOpType.MODIFY,
					LogOpStyle.AUTO_MERGE,
					"[" + personinfo.getNameCn() + "]合并到主索引[" + pair.getRightRecord().getObject().getNameCn()
							+ "],系统匹配度:" + NumberUtils.toPercentStr(pair.getWeight()));
			return personIndexPair.getMpiPk();
		}
	}

	/**
	 * 新增人员信息并生成主索引
	 *
	 * @param personInfo
	 *            人员信息
	 * @return 主索引信息
	 */
	private PersonIndex addPersonIndex(PersonInfo personInfo) {
		PersonIndex personIndex = personInfo.toPersonIndex();
		Short state = personInfo.getState();
		if (IndexRegisterType.ADD.getCode() == state) {// 新增

			// 主索引新增
			personIndexService.save(personIndex);

			// 合并信息入库（第一次入库也记录一次合并信息）
			IndexIdentifierRel newIir = indexIdentifierRelService.add(personInfo.getFieldPk(), personIndex.getMpiPk(),
					personInfo.getDomainId(), personInfo.getIdentifier(), LogOpType.MATCH, LogOpStyle.AUTO_NEW,
					"新建主索引:[" + personIndex.getNameCn() + "]");

			// 新合并号
			long comboNO = newIir.getCombineNo();

			// 主索引合并级别记录入库
			MpiCombineRec mpiCombineRec = personIndex.indexToMpiCombineRec();
			mpiCombineRec.setCombineNo(comboNO);
			mpiCombineRecService.save(mpiCombineRec);

			// 域数据源字段级别
			List<DomainSrcLevel> srcLevelcolmap = domainSrcLevelService.queryByDomainID(personInfo.getDomainId());
			// 合并记录字段级别入库
			mpiCombineLevelService.batchAddLevel(personIndex, comboNO, personInfo.getSrcLevel(), srcLevelcolmap);

		} else if (IndexRegisterType.UPDATE.getCode() == state) {// 编辑

			// 获取人员信息主键
			String infoPk = personInfo.getFieldPk();

			// 获取原始主索引信息主键
			String indexPk = indexIdentifierRelService.queryByFieldPK(infoPk).getMpiPk();
			personIndex.setMpiPk(indexPk);

			// 按照修改主索引信息
			personIndexService.update(personIndex);

			// 添加索引操作日志
			indexIdentifierRelService.saveIndexLog(personInfo.getFieldPk(), indexPk, personInfo.getDomainId(),
					LogOpType.MATCH, LogOpStyle.AUTO_NEW, "修改主索引:[" + personIndex.getNameCn() + "]");

		} else if (IndexRegisterType.DEL.getCode() == state) {// 删除
			// 获取人员信息主键
			String infoPk = personInfo.getFieldPk();
			// 获取原始主索引信息主键
			String indexPk = indexIdentifierRelService.queryByFieldPK(infoPk).getMpiPk();
			int indexPkSize = indexIdentifierRelService.queryByMpiPK(indexPk).size();
			if (indexPkSize == 1) {
				// 删除关联关系记录
				indexIdentifierRelService.deleteByFieldPk(infoPk, LogOpType.MATCH, LogOpStyle.AUTO_REMOVE,
						"删除主索引:[" + personIndex.getNameCn() + "]");
				// 删除主索引信息记录
				personIndexService.deleteById(indexPk);

			} else if (indexPkSize > 1) {
				// 删除关联关系记录
				indexIdentifierRelService.deleteByFieldPk(infoPk, LogOpType.MATCH, LogOpStyle.AUTO_REMOVE, null);
			}

		}

		return personIndex;

	}

	/**
	 * 需人工干预记录
	 */
	public void addMenOpPerson(PersonInfo person, String mpiPk) {
		ManOpPerson manOpPerson = new ManOpPerson();
		manOpPerson.setManOpStatus("0");
		manOpPerson.setManOpTime(DateUtil.getTimeNow());
		manOpPerson.setFieldPk(person.getFieldPk());
		manOpPerson.setMpiPk(mpiPk);
		manOpPersonService.save(manOpPerson);
	}

}
