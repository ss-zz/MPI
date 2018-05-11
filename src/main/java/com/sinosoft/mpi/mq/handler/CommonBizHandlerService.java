package com.sinosoft.mpi.mq.handler;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.bizblock.service.BizBlockService;
import com.sinosoft.bizmatch.service.BizMatchService;
import com.sinosoft.match.model.Record;
import com.sinosoft.match.model.RecordPairBiz;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.biz.BizIndex;
import com.sinosoft.mpi.model.biz.BizInfo;
import com.sinosoft.mpi.service.biz.BizIdxLogService;
import com.sinosoft.mpi.service.biz.BizIndexService;
import com.sinosoft.mpi.service.biz.BizIndexUpdateService;
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
	@Resource
	BizIndexUpdateService bizIndexUpdateService;

	/**
	 * 保存业务信息
	 * 
	 * @param bizInfo
	 *            业务信息
	 * @param patientId
	 *            人员id
	 * @param mpiPk
	 *            主索引id
	 * @return
	 */
	public String saveBizInfo(BizInfo bizInfo, String patientId, String mpiPk) {
		Record<BizInfo> bizRecord = new Record<BizInfo>(bizInfo);
		bizRecord.setRecordId(bizInfo.getBizId());
		List<Record<BizIndex>> records = bizBlockService.findCandidates(bizRecord);
		// 找出匹配情况。
		List<RecordPairBiz> pairs = bizMatchServcie.match(bizRecord, records);

		RecordPairBiz pair = null;
		// 没有初步匹配者，直接添加索引。
		if (records.size() == 0 || pairs.size() == 0) {
			// 主索引信息入库
			BizIndex index = addBizIndex(bizInfo);
			return index.getId();
		} else {
			// 如果信息匹配结果，则使用相同次id
			pair = bizMatchServcie.matchedPair(pairs);
			if (pair != null) {
				BizIndex bizIndex = pair.getRightRecord().getObject();
				// 添加索引操作日志
				bizIdxLogService.saveIndexLog(bizInfo.getBizId(), bizIndex.getId(), bizInfo.getSystemId(),
						Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_MERGE,
						"[" + bizInfo.getBizId() + "]合并到主索引[" + pair.getRightRecord().getObject().getBizId()
								+ "],系统匹配度:" + NumberUtils.toPercentStr(pair.getWeight()));
				// 主索引信息入库
				bizInfo.setBizSerialId(pair.getRightRecord().getObject().getBizSerialId());
				BizIndex index = addBizIndex(bizInfo);
				return index.getId();
			} else {
				// 主索引信息入库
				BizIndex index = addBizIndex(bizInfo);
				return index.getId();
			}
		}
	}

	/**
	 * 新增业务信息并生成主索引
	 *
	 * @param bizInfo
	 *            业务信息
	 * @return 业务主索引信息
	 */
	private BizIndex addBizIndex(BizInfo bizInfo) {
		BizIndex bizIndex = bizInfo.toIndex();
		if (bizIndex.getBizSerialId() == null) {
			bizIndex.setBizSerialId(UUID.randomUUID().toString());
		}
		bizIndexService.save(bizIndex);
		// 添加索引操作日志
		bizIdxLogService.saveIndexLog(bizInfo.getBizId(), bizIndex.getId(), bizInfo.getSystemId(),
				Constant.IDX_LOG_TYPE_MATCH, Constant.IDX_LOG_STYLE_AUTO_NEW, "新建业务主索引:[" + bizIndex.getId() + "]");
		return bizIndex;
	}

}