package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.mpi.dao.mpi.IndexIdentifierRelDao;
import com.sinosoft.mpi.dao.mpi.PersonIndexDao;
import com.sinosoft.mpi.dics.LogOpStyle;
import com.sinosoft.mpi.dics.LogOpType;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.util.DateUtil;

/**
 * 人员主索引与原始人员关系服务
 */
@Service
public class IndexIdentifierRelService {

	@Resource
	PersonIdxLogService personIdxLogService;
	@Resource
	PersonIndexService personIndexService;
	@Resource
	UserService userService;

	@Resource
	IndexIdentifierRelDao indexIdentifierRelDao;
	@Resource
	PersonIndexDao personIndexDao;

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public IndexIdentifierRel update(IndexIdentifierRel t) {
		return indexIdentifierRelDao.save(t);
	}

	/**
	 * 保存
	 *
	 * @param fieldPk
	 *            人员主键
	 * @param mpiPk
	 *            主索引主键
	 * @param domainId
	 *            domainId
	 * @param personIdentifier
	 *            人员唯一标识
	 * 
	 */
	public IndexIdentifierRel add(String fieldPk, String mpiPk, String domainId, String personIdentifier,
			LogOpType opType, LogOpStyle opStyle, String otherInfo) {
		return add(fieldPk, mpiPk, domainId, personIdentifier, opType, opStyle, otherInfo, null);
	}

	/**
	 * 保存-带合并号码
	 *
	 * @param fieldPk
	 *            人员主键
	 * @param mpiPk
	 *            主索引主键
	 * @param domainId
	 *            domainId
	 * @param personIdentifier
	 *            人员唯一标识
	 * 
	 */
	public IndexIdentifierRel add(String fieldPk, String mpiPk, String domainId, String personIdentifier,
			LogOpType opType, LogOpStyle opStyle, String otherInfo, Long combineNo) {

		String info = "【" + opType.getDesc() + "-新增主索引与人员关联关系-" + opStyle.getDesc() + "】" + otherInfo;
		// 记录日志
		saveIndexLog(fieldPk, mpiPk, domainId, opType, opStyle, info);

		IndexIdentifierRel iir = new IndexIdentifierRel();
		iir.setFieldPk(fieldPk);
		iir.setMpiPk(mpiPk);
		iir.setDomainId(domainId);
		iir.setPersonIdentifier(personIdentifier);
		iir.setCombineNo(combineNo);
		return indexIdentifierRelDao.save(iir);
	}

	/**
	 * 保存日志
	 * 
	 * @param fieldPk
	 * @param mpiPk
	 * @param domainId
	 * @param opType
	 * @param opStyle
	 * @param desc
	 */
	public void saveIndexLog(String fieldPk, String mpiPk, String domainId, LogOpType opType, LogOpStyle opStyle,
			String desc) {
		PersonIdxLog result = new PersonIdxLog();
		result.setOpType(opType.getCode());
		result.setOpStyle(opStyle.getCode());
		result.setOpTime(DateUtil.getTimeNow());
		result.setOpUserId(userService.getLoginUserName());
		result.setOpDesc(desc);
		result.setInfoSour(domainId);
		result.setMpiPk(mpiPk);
		result.setFieldPk(fieldPk);
		personIdxLogService.save(result);
	}

	/**
	 * 删除-根据合并号
	 * 
	 * @param t
	 */
	public void delete(Long comboNo, LogOpType opType, LogOpStyle opStyle, String otherInfo) {
		IndexIdentifierRel rel = indexIdentifierRelDao.getOne(comboNo);
		delete(rel, opType, opStyle, otherInfo);
	}

	/**
	 * 根据对象删除
	 * 
	 * @param rel
	 */
	private void delete(IndexIdentifierRel rel, LogOpType opType, LogOpStyle opStyle, String otherInfo) {
		if (rel != null && rel.getMpiPk() != null) {
			indexIdentifierRelDao.deleteByFieldPk(rel.getMpiPk());
		}
	}

	/**
	 * 根据人员主键删除
	 * 
	 * @param fieldPk
	 *            人员主键
	 */
	@Transactional
	public void deleteByFieldPk(String fieldPk, LogOpType opType, LogOpStyle opStyle, String otherInfo) {

		IndexIdentifierRel rel = queryByFieldPK(fieldPk);
		if (rel != null) {
			String info = "【" + opType.getDesc() + "-删除主索引与人员关联关系-" + opStyle.getDesc() + "】" + otherInfo;
			// 记录日志
			saveIndexLog(fieldPk, rel.getMpiPk(), rel.getDomainId(), opType, opStyle, info);

			// 删除关联关系
			indexIdentifierRelDao.deleteByFieldPk(fieldPk);
		}
	}

	/**
	 * 根据人员id查询
	 * 
	 * @param fieldPk
	 * @return
	 */
	public IndexIdentifierRel queryByFieldPK(String fieldPk) {
		return indexIdentifierRelDao.findFirstByFieldPk(fieldPk);
	}

	/**
	 * 根据主索引id查询
	 * 
	 * @param mpiPk
	 * @return
	 */
	public List<IndexIdentifierRel> queryByMpiPK(String mpiPk) {
		return indexIdentifierRelDao.findByMpiPk(mpiPk);
	}

	/**
	 * 根据合并号删除
	 * 
	 * @param combineNo
	 */
	public void deleteRecurByCombinNo(Long combineNo, LogOpType opType, LogOpStyle opStyle, String otherInfo) {
		List<IndexIdentifierRel> rels = indexIdentifierRelDao.findByRecur(combineNo);
		for (IndexIdentifierRel rel : rels) {
			delete(rel, opType, opStyle, otherInfo);
		}
	}

	/**
	 * 根据合并号删除
	 * 
	 * @param combineNo
	 */
	public void deleteRecurByCombinNo(Long combineNo, LogOpType opType, LogOpStyle opStyle) {
		deleteRecurByCombinNo(combineNo, opType, opStyle, null);
	}

	/**
	 * 根据主索引id查询第一个匹配的合并记录（上一条合并号）
	 * 
	 * @param mpiPk
	 * @return
	 */
	public IndexIdentifierRel findFirstByMpiPkOrderByCombineRecDesc(String mpiPk) {
		return indexIdentifierRelDao.findFirstByMpiPkOrderByCombineRecDesc(mpiPk);
	}

}
