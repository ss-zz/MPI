package com.sinosoft.index.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.block.model.BlockField;
import com.sinosoft.block.model.BlockRound;
import com.sinosoft.index.entity.BizCommonFieldConfig;
import com.sinosoft.index.entity.BizMatchConfig;
import com.sinosoft.index.entity.BizMatchFieldConfig;
import com.sinosoft.index.entity.BizMatchServConfig;
import com.sinosoft.index.entity.MpiBizRel;
import com.sinosoft.index.entity.MpiIndex;
import com.sinosoft.index.entity.MpiIndexConfig;
import com.sinosoft.index.model.IndexHandlerResult;
import com.sinosoft.index.model.IndexRegister;
import com.sinosoft.index.model.IndexRegisterResultStatus;
import com.sinosoft.index.model.match.MatchPair;
import com.sinosoft.match.MatchException;
import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.match.model.ComparisonVector;
import com.sinosoft.match.model.MatchField;
import com.sinosoft.match.model.Record;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.service.IPersonIdxLogService;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.NumberUtils;
import com.sinosoft.stringcomparison.service.IStringComparisonService;

/**
 * mpi 队列数据处理
 * 
 * @author sinosoft
 *
 */
@Service
public class MpiMqMessageHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MpiIndexService mpiIndexService;
	@Autowired
	MpiIndexRelService mpiIndexRelService;

	@Autowired
	MpiBizRelService mpiBizRelService;

	@Autowired
	MpiConfigService mpiConfigService;

	@Autowired
	IStringComparisonService stringComparisonService;

	@Autowired
	IPersonIdxLogService personIdxLogService;

	@Autowired
	MqService mqService;

	private ObjectMapper om = new ObjectMapper();

	/**
	 * 队列数据处理：获取消息->获取业务配置信息->初筛->计算匹配->处理结果
	 * 
	 * @param message
	 *            消息内容
	 */
	public void handleMessage(Object message) {
		// 是否为空
		if (message == null) {
			logger.warn("队列中获取的消息为空");
			return;
		}
		// 消息类型是否正确
		if (!(message instanceof IndexRegister)) {
			logger.error("队列中获取的消息类型错误，应为{}，实际为{}", IndexRegister.class, message.getClass());
			return;
		}

		// 类型转换
		IndexRegister indexRegister = (IndexRegister) message;

		/*
		 * 提取消息中信息
		 */
		// 业务唯一标识
		String bizKey = indexRegister.getBizKey();
		// 原始业务id
		String srcBizId = indexRegister.getBizId();
		// 原始人员id
		String srcPersonId = indexRegister.getPersonId();
		// 数据注册类型：添加、编辑、删除
		String type = indexRegister.getType();
		// 人员数据
		Map<String, String> personData;
		// 最终匹配或者生成的mpi主键
		String mpiId = null;
		try {
			personData = changeJsonStrToMap(indexRegister.getPersonData());
		} catch (IOException e) {
			sendErrorMsgToResultQueue(indexRegister, IndexRegisterResultStatus.FAIL_CHECK.getCode(), "人员数据转换失败");
			return;
		}
		Record<Map<String, String>> personRecord = new Record<Map<String, String>>(personData, srcPersonId);

		/*
		 * 获取配置信息
		 */
		MpiIndexConfig indexConfig = mpiConfigService.getMpiConfig();

		// 初筛
		// TODO 获取初筛配置
		List<BlockRound> firstFilterGroups = new ArrayList<BlockRound>();

		if (firstFilterGroups == null || firstFilterGroups.size() == 0) {
			sendErrorMsgToResultQueue(indexRegister, IndexRegisterResultStatus.FAIL_CHECK.getCode(),
					"业务[" + bizKey + "]初筛配置为空");
			return;
		}

		// 检索待合并数据-初筛检索数据库
		Query qIndex = new Query();
		Criteria orCriteriaIndex = new Criteria();
		for (BlockRound firstFilterGroup : firstFilterGroups) {
			List<BlockField> blockFields = firstFilterGroup.getBlockFields();
			if (blockFields == null || blockFields.size() == 0) {
				sendErrorMsgToResultQueue(indexRegister, IndexRegisterResultStatus.FAIL_CHECK.getCode(),
						"业务[" + bizKey + "]初筛配置组为空");
				return;
			}
			Criteria andCriteria = new Criteria();
			for (BlockField blockField : blockFields) {
				String fieldName = blockField.getDbField();
				andCriteria.andOperator(Criteria.where(fieldName).is(personData.get(fieldName)));
			}
			orCriteriaIndex.orOperator(andCriteria);
		}
		qIndex.addCriteria(orCriteriaIndex);
		List<MpiIndex> firstFilterDatas = mpiIndexService.findIndex(qIndex);
		// 判断是否有初筛结果
		if (firstFilterDatas == null || firstFilterDatas.size() == 0) {// 无初筛结果
			// 主索引入库
			saveMpiIndex(indexRegister, personData);
			return;
		}
		List<Record<Map<String, String>>> firstFilterRecords = new ArrayList<Record<Map<String, String>>>();
		for (MpiIndex firstFilterData : firstFilterDatas) {
			firstFilterRecords.add(new Record<Map<String, String>>(firstFilterData.getData(), firstFilterData.getId()));
		}

		// TODO 获取匹配配置
		MatchConfig matchConfig = MatchConfig.getInstanse();
		List<MatchField> matchFields = matchConfig.getMatchFields();

		// 计算属性匹配度
		List<MatchPair> matchPairs = new ArrayList<MatchPair>(0);
		for (Record<Map<String, String>> indexRecord : firstFilterRecords) {
			MatchPair pair = this.match(personRecord, indexRecord, matchFields);
			if (pair.getWeight() > MatchConfig.getInstanse().getMatchWeightThreshold()) {
				matchPairs.add(pair);
			}
		}

		// 获取第一条匹配数据
		MatchPair matchPair = null;
		for (MatchPair pair : matchPairs) {
			double weight = pair.getWeight();
			// 权重大于完全匹配或者可能匹配值
			if (weight >= matchConfig.getAgreementWeightThreshold()
					|| weight >= matchConfig.getMatchWeightThreshold()) {
				matchPair = pair;
				break;
			}
		}

		// 计算匹配数据为空
		if (matchPair == null) {
			// 主索引入库
			saveMpiIndex(indexRegister, personData);
			return;
		}

		// 匹配的主索引id
		mpiId = matchPair.getIndexRecord().getRecordId();

		// 添加索引操作日志
		Record<Map<String, String>> matchRecordIndex = matchPair.getIndexRecord();
		Record<Map<String, String>> matchRecordsrc = matchPair.getSrcRecord();
		addIPersonIdxLogService(srcBizId, matchRecordIndex.getRecordId(), Constant.IDX_LOG_TYPE_MATCH,
				Constant.IDX_LOG_STYLE_AUTO_MERGE,
				"注册人员[" + matchRecordsrc.getRecordId() + "]合并到主索引[" + matchRecordIndex.getRecordId() + "],系统匹配度:"
						+ NumberUtils.toPercentStr(matchPair.getWeight()));

		// 更新索引信息：有值的覆盖没值的，新的覆盖旧的
		Map<String, String> newIndexData = new HashMap<String, String>();
		Map<String, String> matchIndexData = matchRecordIndex.getObject();
		Map<String, String> matchSrcData = matchRecordsrc.getObject();
		for (String indexKey : matchIndexData.keySet()) {// 遍历索引数据
			String indexValue = matchIndexData.get(indexKey);
			String srcValue = matchSrcData.get(indexKey);
			newIndexData.put(indexKey, indexValue);
			if (!isNull(srcValue)) {
				newIndexData.put(indexKey, srcValue);
			}
		}
		for (String srcKey : matchSrcData.keySet()) {// 遍历原始数据
			String srcValue = matchSrcData.get(srcKey);
			if (!isNull(srcValue)) {
				newIndexData.put(srcKey, srcValue);
			} else {
				if (!newIndexData.containsKey(srcKey)) {
					newIndexData.put(srcKey, srcValue);
				}
			}
		}

		// 更新主索引
		updateMpiIndex(indexRegister, newIndexData, mpiId);

		/**
		 * 业务数据匹配
		 */
		String bizDataStr = indexRegister.getBizData();
		// 业务数据若为空，则不处理业务数据逻辑
		if (isNull(bizDataStr)) {
			return;
		}
		// 业务数据
		Map<String, String> bizData;
		try {
			bizData = changeJsonStrToMap(bizDataStr);
		} catch (IOException e) {
			sendErrorMsgToResultQueue(indexRegister, IndexRegisterResultStatus.FAIL_CHECK.getCode(), "业务数据转换失败");
			return;
		}

		// 获取业务配置
		BizMatchConfig bizMatchConfig = indexConfig.getBizMatchConfig();
		// 获取业务通用字段配置
		List<BizCommonFieldConfig> bizCommonFieldConfigs = bizMatchConfig.getCommonFieldConfig();
		if (bizCommonFieldConfigs.size() == 0) {
			sendErrorMsgToResultQueue(indexRegister, IndexRegisterResultStatus.FAIL_CHECK.getCode(), "通用业务字段配置为空");
			return;
		}
		// 获取业务配置
		BizMatchServConfig bizMatchServConfig = mpiConfigService.getBizMatchServConfigByBizName(bizKey);
		if (bizMatchServConfig == null) {
			sendErrorMsgToResultQueue(indexRegister, IndexRegisterResultStatus.FAIL_CHECK.getCode(),
					"业务[" + bizKey + "]匹配配置为空");
			return;
		}
		// 获取业务字段配置
		List<BizMatchFieldConfig> bizMatchFieldConfigs = bizMatchServConfig.getBizMatchFieldConfigs();
		if (bizMatchFieldConfigs.size() == 0) {
			sendErrorMsgToResultQueue(indexRegister, IndexRegisterResultStatus.FAIL_CHECK.getCode(),
					"业务[" + bizKey + "]字段配置为空");
			return;
		}

		// 根据业务匹配配置查询匹配的数据
		// 检索符合条件数据
		Query qBiz = new Query();
		Criteria orCriteriaBiz = new Criteria();
		for (BizMatchFieldConfig bizMatchFieldConfig : bizMatchFieldConfigs) {
			Criteria andCriteria = new Criteria();
			String fieldName = bizMatchFieldConfig.getFieldName();
			// TODO 根据算法不同、查询方式也不同
			String algorithm = bizMatchFieldConfig.getAlgorithm();
			andCriteria.andOperator(Criteria.where(fieldName).is(bizData.get(fieldName)));
			orCriteriaBiz.orOperator(andCriteria);
		}
		qBiz.addCriteria(orCriteriaBiz);

		// 查询匹配的数据
		List<MpiBizRel> bizMatchs = mpiBizRelService.find(qBiz);
		
		// 是否有匹配的数据
		MpiBizRel newMpiBizRel = new MpiBizRel();
		newMpiBizRel.setSrcBizId(srcBizId);
		newMpiBizRel.setSrcData(bizData);
		if(bizMatchs.size() > 0) {
			// 取第一条
			MpiBizRel mpiBizRel = bizMatchs.get(0);
			newMpiBizRel.setMpiIndexId(mpiBizRel.getMpiIndexId());
			newMpiBizRel.setGroupId(mpiBizRel.getGroupId());
		}else {
			newMpiBizRel.setMpiIndexId(mpiId);
			newMpiBizRel.setGroupId(UUID.randomUUID().toString());
		}
		newMpiBizRel = mpiBizRelService.save(newMpiBizRel);

	}

	/**
	 * 是否为空
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNull(String str) {
		return str == null || str.trim().equals("") ? true : false;
	}

	/**
	 * 主索引操作日志插入服务
	 */
	private void addIPersonIdxLogService(String person, String index, String opType, String opStyle, String desc) {
		PersonIdxLog result = new PersonIdxLog();
		result.setOpType(opType);
		result.setOpStyle(opStyle);
		result.setOpTime(DateUtil.getTimeNow(new Date()));
		result.setOpUserId("0");
		result.setOpDesc(desc);
		result.setMpipk(index);
		result.setFieldpk(person);
		// 自动标志
		personIdxLogService.save(result);
	}

	/**
	 * 主索引数据入库-新增
	 * 
	 * @param indexRegister
	 *            注册数据
	 * @param personData
	 *            人员数据
	 */
	private void saveMpiIndex(IndexRegister indexRegister, Map<String, String> personData) {
		updateMpiIndex(indexRegister, personData, null);
	}

	/**
	 * 主索引数据入库-更新
	 * 
	 * @param indexRegister
	 *            注册数据
	 * @param personData
	 *            人员数据
	 */
	private void updateMpiIndex(IndexRegister indexRegister, Map<String, String> personData, String mpiId) {

		// 主索引数据入库
		MpiIndex mpiIndex = new MpiIndex();
		mpiIndex.setId(mpiId);
		mpiIndex.setCreateTime(new Date());
		mpiIndex.setData(personData);
		mpiIndex.setUpdateTime(new Date());
		mpiIndex = mpiIndexService.saveIndex(mpiIndex);

		// TODO 记录操作日志

		// 发送消息
		sendSuccessMsgToResultQueue(indexRegister, mpiIndex.getId());
	}

	/**
	 * 根据匹配配置进行匹配计算
	 * 
	 * @param srcRecord
	 *            原始数据
	 * @param indexRecord
	 *            索引数据
	 * @param matchFields
	 *            匹配配置
	 * @return 匹配计算结果
	 */
	private MatchPair match(Record<Map<String, String>> srcRecord, Record<Map<String, String>> indexRecord,
			List<MatchField> matchFields) {
		MatchPair pair = new MatchPair(srcRecord, indexRecord);
		// 计算比较
		scoreRecordPair(pair, matchFields);
		// 计算合并匹配度
		calculateWeight(pair, matchFields);

		return pair;
	}

	/**
	 * 计算每个属性的匹配度/得分
	 * 
	 * @param pair
	 *            匹配对
	 * @param matchFields
	 *            匹配配置
	 */
	private void scoreRecordPair(MatchPair pair, List<MatchField> matchFields) {
		pair.setComparisonVector(new ComparisonVector(matchFields));
		double score = 0.0;
		for (int i = 0; i < matchFields.size(); i++) {
			MatchField matchField = matchFields.get(i);
			String fieldName = matchField.getFieldName();
			String srcValue = pair.getSrcRecord().getObject().get(fieldName);
			String indexValue = pair.getIndexRecord().getObject().get(fieldName);
			if (!srcValue.equals("") && !indexValue.equals("")) {
				score = stringComparisonService.score(matchField.getComparatorFunction(), srcValue, indexValue);
			}
			pair.getComparisonVector().setScore(i, score);
		}
	}

	/**
	 * 计算总的匹配度
	 * 
	 * @param pair
	 *            匹配对
	 * @param matchFields
	 *            匹配配置
	 */
	private void calculateWeight(MatchPair pair, List<MatchField> matchFields) {
		double wight = 0;
		double[] scores = pair.getComparisonVector().getScores();
		if (pair.getComparisonVector() != null && matchFields.size() == pair.getComparisonVector().getScores().length) {
			for (int i = 0; i < matchFields.size(); i++) {
				wight += matchFields.get(i).getWeight() * scores[i];
			}
			if (wight > 1.0) {
				wight = 1.0;
			}
		} else {
			throw new MatchException("匹配失败，检查是否计算匹配度");
		}
		if (wight > 1) {
			wight = 1;
		}
		pair.setWeight(wight);
	}

	/**
	 * 发送成功消息到结果队列中
	 * 
	 * @param indexRegister
	 * @param indexId
	 */
	private void sendSuccessMsgToResultQueue(IndexRegister indexRegister, String indexId) {
		sendMsgToResultQueue(createIndexHandlerResult(indexRegister, IndexRegisterResultStatus.SUCCESS.getCode(),
				IndexRegisterResultStatus.SUCCESS.getDesc(), indexId));
	}

	/**
	 * 发送失败消息到结果队列中
	 * 
	 * @param indexRegister
	 * @param message
	 */
	private void sendErrorMsgToResultQueue(IndexRegister indexRegister, String code, String message) {
		sendMsgToResultQueue(createIndexHandlerResult(indexRegister, code, message, null));
	}

	/**
	 * 发送消息到结果队列中
	 * 
	 * @param result
	 */
	private void sendMsgToResultQueue(IndexHandlerResult result) {
		if (result != null) {
			mqService.sendMessageToResult(result);
		}
	}

	/**
	 * 构造IndexHandlerResult
	 * 
	 * @param indexRegister
	 *            注册数据
	 * @param code
	 *            编码
	 * @param message
	 *            消息
	 * @param indexId
	 *            主索引id
	 * @return IndexHandlerResult
	 */
	private IndexHandlerResult createIndexHandlerResult(IndexRegister indexRegister, String code, String message,
			String indexId) {
		IndexHandlerResult result = new IndexHandlerResult();
		result.setCode(code);
		result.setMessage(message);
		result.setIndexId(indexId);
		if (indexRegister != null) {
			result.setBizKey(indexRegister.getBizKey());
			result.setType(indexRegister.getType());
			result.setBizId(indexRegister.getBizId());
			result.setPersonId(indexRegister.getPersonId());
		}
		return result;
	}

	/**
	 * 将json字符串转换为map
	 * 
	 * @param jsonData
	 *            json字符串
	 * @return map数据
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	private Map<String, String> changeJsonStrToMap(String jsonData) throws IOException {
		return om.readValue(jsonData, new TypeReference<HashMap<String, String>>() {
		});
	}

}
