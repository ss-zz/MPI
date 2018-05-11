package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IndexIdentifierRelDao;
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
		indexIdentifierRelDao.add(t);
	}

	/**
	 * 删除-根据合并号
	 * 
	 * @param t
	 */
	public void delete(IndexIdentifierRel t) {
		indexIdentifierRelDao.deleteById(t);
	}

	/**
	 * 根据主键删除
	 * 
	 * @param fieldpk
	 */
	public void deleteByFieldPk(String fieldpk) {
		indexIdentifierRelDao.deleteByFieldPK(fieldpk);
	}

	/**
	 * 根据人员id查询
	 * 
	 * @param field_pk
	 * @return
	 */
	public IndexIdentifierRel queryByFieldPK(String field_pk) {
		IndexIdentifierRel rel = indexIdentifierRelDao.findByFieldPK(field_pk);
		return rel;
	}

	/**
	 * 根据主索引id查询
	 * 
	 * @param mpi_pk
	 * @return
	 */
	public List<IndexIdentifierRel> queryByMpiPK(String mpi_pk) {
		List<IndexIdentifierRel> rels = indexIdentifierRelDao.findByMpiPK(mpi_pk);
		return rels;
	}

	/**
	 * 根据合并号删除
	 * 
	 * @param combineNo
	 */
	public void deleteRecurByCombinNo(Long combineNo) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				" delete from MPI_INDEX_IDENTIFIER_REL r where r.combine_no in (select combine_no from MPI_INDEX_IDENTIFIER_REL t start with combine_no = ? ");
		sb.append("connect by combine_rec = prior combine_no)");
		indexIdentifierRelDao.deleteRecurByCombinNo(sb.toString(), combineNo);
	}

}
