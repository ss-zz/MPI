package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.mpi.IndexIdentifierRelDao;
import com.sinosoft.mpi.model.IndexIdentifierRel;

/**
 * 人员主索引与原始人员关系服务
 */
@Service
public class IndexIdentifierRelService {

	@Resource
	private IndexIdentifierRelDao indexIdentifierRelDao;

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(IndexIdentifierRel t) {
		indexIdentifierRelDao.save(t);
	}

	/**
	 * 删除-根据合并号
	 * 
	 * @param t
	 */
	public void delete(IndexIdentifierRel t) {
		indexIdentifierRelDao.delete(t);
	}

	/**
	 * 根据主键删除
	 * 
	 * @param fieldPk
	 */
	public void deleteByFieldPk(String fieldPk) {
		indexIdentifierRelDao.delete(fieldPk);
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
	public void deleteRecurByCombinNo(Long combineNo) {
		indexIdentifierRelDao.deleteRecurByCombinNo(combineNo);
	}

}
