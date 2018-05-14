package com.sinosoft.mpi.service.biz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.biz.MpiBizIdxLogDao;
import com.sinosoft.mpi.model.biz.MpiBizIdxLog;

/**
 * 业务主索引日志服务
 */
@Service
public class BizIdxLogService {

	@Resource
	private MpiBizIdxLogDao bizIdxLogDao;

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(MpiBizIdxLog t) {
		bizIdxLogDao.delete(t);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		bizIdxLogDao.delete(id);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public MpiBizIdxLog getObject(String id) {
		return bizIdxLogDao.getOne(id);
	}

	/**
	 * 根据人员id查询操作日志列表
	 * 
	 * @param personId
	 * @return
	 */
	public List<MpiBizIdxLog> queryOpLogByPersonId(String personId) {
		return bizIdxLogDao.findByBlUserId(personId);
	}

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public MpiBizIdxLog save(MpiBizIdxLog t) {
		return bizIdxLogDao.save(t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public MpiBizIdxLog update(MpiBizIdxLog t) {
		return bizIdxLogDao.save(t);
	}

	/**
	 * 新增主索引操作日志
	 * 
	 * @param srcBizId
	 *            原始业务id
	 * @param bizId
	 *            新业务id
	 * @param domainId
	 *            域id
	 * @param opType
	 *            操作类型
	 * @param desc
	 *            操作描述
	 * @param weight
	 *            匹配度
	 */
	public void saveIndexLog(String srcBizId, String bizId, String domainId, String opType, String desc,
			Double weight) {
		MpiBizIdxLog result = new MpiBizIdxLog();
		result.setBlType(opType);
		result.setBlTime(new Date());
		result.setBlDesc(desc);
		result.setBlInfoSour(domainId);
		result.setBlBizId(bizId);
		result.setBlMatched(weight == null ? null : String.valueOf(weight));
		// 自动标志
		save(result);
	}

}
