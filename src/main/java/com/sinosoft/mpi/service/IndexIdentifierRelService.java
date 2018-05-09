package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IndexIdentifierRelDao;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.util.PageInfo;

@Service
public class IndexIdentifierRelService {

	@Resource
	private IndexIdentifierRelDao indexIdentifierRelDao;

	public void save(IndexIdentifierRel t) {
		indexIdentifierRelDao.add(t);
	}

	public void update(IndexIdentifierRel t) {
	}

	public void delete(IndexIdentifierRel t) {
		indexIdentifierRelDao.deleteById(t);
	}

	public void deleteByFieldPk(String fieldpk) {
		indexIdentifierRelDao.deleteByFieldPK(fieldpk);
	}

	public IndexIdentifierRel getObject(String id) {
		return null;
	}

	public List<IndexIdentifierRel> queryForPage(IndexIdentifierRel t, PageInfo page) {
		return null;
	}

	public IndexIdentifierRel queryByIdentifierID(String identifierID) {
		String sql = "select index_id,identifier_id from MPI_INDEX_IDENTIFIER_REL where identifier_id=?";
		List<IndexIdentifierRel> rels = indexIdentifierRelDao.find(sql, new Object[] { identifierID });
		if (rels.size() == 0) {
			return null;
		}
		return rels.get(0);
	}

	public IndexIdentifierRel queryByFieldPK(String field_pk) {
		IndexIdentifierRel rel = indexIdentifierRelDao.findByFieldPK(field_pk);
		return rel;
	}

	public List<IndexIdentifierRel> queryByMpiPK(String mpi_pk) {
		List<IndexIdentifierRel> rels = indexIdentifierRelDao.findByMpiPK(mpi_pk);
		return rels;
	}

	public void deleteRecurByCombinNo(Long combineNo) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				" delete from MPI_INDEX_IDENTIFIER_REL r where r.combine_no in (select combine_no from MPI_INDEX_IDENTIFIER_REL t start with combine_no = ? ");
		sb.append("connect by combine_rec = prior combine_no)");
		indexIdentifierRelDao.deleteRecurByCombinNo(sb.toString(), combineNo);

	}

}
