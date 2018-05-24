package com.sinosoft.mpi.mq.handler.biz;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.bizblock.service.BizBlockService;
import com.sinosoft.bizmatch.service.BizMatchService;
import com.sinosoft.match.model.Record;
import com.sinosoft.match.model.RecordPairBiz;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.biz.MpiBizIndex;
import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.mq.handler.model.BizHandlerResult;
import com.sinosoft.mpi.service.biz.BizIdxLogService;
import com.sinosoft.mpi.service.biz.BizIndexService;
import com.sinosoft.mpi.util.NumberUtils;

/**
 * 主索引处理过程中的公共服务
 */
@Service
public class CommonBizHandlerService {

	@Resource
	BizIndexService bizIndexService;
	@Resource
	BizIdxLogService bizIdxLogService;
	@Resource
	BizBlockService bizBlockService;
	@Resource
	BizMatchService bizMatchServcie;

	/**
	 * 保存业务信息
	 * 
	 * @param bizInfo
	 *            业务信息
	 * @param patientId
	 *            人员id
	 * @param mpiPk
	 *            主索引id
	 * @param bizRegister
	 *            注册信息
	 * @return
	 */
	public BizHandlerResult saveBizInfo(MpiBizInfoRegister bizInfo, String patientId, String mpiPk,
			BizRegister bizRegister) {
		BizHandlerResult result = new BizHandlerResult();
		// 原始业务id
		result.setSrcBizId(bizInfo.getBizId());

		Record<MpiBizInfoRegister> bizRecord = new Record<MpiBizInfoRegister>(bizInfo);
		bizRecord.setRecordId(bizInfo.getBizId());
		List<Record<MpiBizIndex>> records = bizBlockService.findCandidates(bizRecord);
		// 找出匹配情况。
		List<RecordPairBiz> pairs = bizMatchServcie.match(bizRecord, records);

		RecordPairBiz pair = null;
		// 次id
		String serialId = null;

		// 匹配的权重
		Double weightPair = null;

		// 日志的组装信息
		String logInfo = null;
		// 是否匹配上
		Boolean isPair = false;
		// 没有初步匹配者，或者匹配度不够，直接添加索引
		if (records.size() == 0 || pairs.size() == 0 || (pair = bizMatchServcie.matchedPair(pairs)) == null) {
			isPair = false;
			// 生成新次id
			serialId = UUID.randomUUID().toString();
		} else {
			isPair = true;
			// 如果信息匹配结果，则使用相同次id
			MpiBizIndex bizIndexPair = pair.getRightRecord().getObject();
			weightPair = pair.getWeight();
			// 次id
			serialId = bizIndexPair.getBizSerialId();
		}
		// 主索引信息入库
		MpiBizIndex bizIndex = bizInfo.toIndex();
		bizIndex.setBizSerialId(serialId);// 次id
		bizIndex.setBizPatientId(patientId);// 原始人员id
		bizIndex.setBizSystemId(bizRegister.getSystemKey());// 业务唯一标识
		bizIndex = bizIndexService.save(bizIndex);

		if (isPair) {// 匹配上
			logInfo = "业务[" + bizInfo.getBizId() + "]匹配到业务[" + pair.getRightRecord().getObject().getBizId() + "],匹配度:"
					+ NumberUtils.toPercentStr(weightPair);
		} else {// 未匹配上
			logInfo = "新建业务主索引:[" + bizIndex.getId() + "]";
		}

		// 添加索引操作日志
		bizIdxLogService.saveIndexLog(bizInfo.getBizId(), bizIndex.getId(), bizIndex.getBizSystemId(),
				Constant.IDX_LOG_TYPE_MATCH, logInfo, weightPair);

		// 整理返回的信息
		result.setNewBizId(bizIndex.getId());
		result.setTimeId(serialId);
		return result;
	}

}
