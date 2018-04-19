package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IIndexIdentifierRelDao;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.util.PageInfo;

@Service("indexIdentifierRelService")
public class IndexIdentifierRelService implements IIndexIdentifierRelService {
	private Logger logger = Logger.getLogger(IndexIdentifierRelService.class);
	@Resource
	private IIndexIdentifierRelDao indexIdentifierRelDao;

	@Override
	public void save(IndexIdentifierRel t) {
		indexIdentifierRelDao.add(t);
		logger.debug("Add IndexIdentifierRel:"+t);
	}

	@Override
	public void update(IndexIdentifierRel t) {
		// XXX ben 中间表操作 无需实现更新
	}

	@Override
	public void delete(IndexIdentifierRel t) {
		indexIdentifierRelDao.deleteById(t);	
		logger.debug("Del IndexIdentifierRel:indexId="+t.getMPI_PK()+",identifierId="+t.getPERSON_IDENTIFIER());
	}
	
	@Override
	public void deleteByFieldPk(String fieldpk) {
		indexIdentifierRelDao.deleteByFieldPK(fieldpk);;	
		logger.debug("Del IndexIdentifierRel:field_pk="+fieldpk);
	}
	
	

	@Override
	public IndexIdentifierRel getObject(String id) {
		// XXX ben 中间表操作 无需实现
		return null;
	}

	@Override
	public List<IndexIdentifierRel> queryForPage(IndexIdentifierRel t, PageInfo page) {
		// XXX ben 中间表操作 无需实现
		return null;
	}

	public IIndexIdentifierRelDao getIndexIdentifierRelDao() {
		return indexIdentifierRelDao;
	}

	public void setIndexIdentifierRelDao(IIndexIdentifierRelDao indexIdentifierRelDao) {
		this.indexIdentifierRelDao = indexIdentifierRelDao;
	}

	@Override
	public IndexIdentifierRel queryByIdentifierID(String identifierID) {
		String sql="select index_id,identifier_id from MPI_INDEX_IDENTIFIER_REL where identifier_id=?";
		List<IndexIdentifierRel> rels=indexIdentifierRelDao.find(sql, new Object[]{identifierID});
		if(rels.size()==0){
			return null;
		}
		return rels.get(0);
	}
	
	@Override
	public IndexIdentifierRel queryByFieldPK(String field_pk) {
			IndexIdentifierRel rel=indexIdentifierRelDao.findByFieldPK(field_pk);
		return rel;
	}
	@Override
	public List<IndexIdentifierRel> queryByMpiPK(String mpi_pk){
		List<IndexIdentifierRel> rels=indexIdentifierRelDao.findByMpiPK(mpi_pk);
		return rels;
	}

	@Override
	public void deleteRecurByCombinNo(Long combineNo) {
		StringBuilder sb=new StringBuilder();
		sb.append(" delete from MPI_INDEX_IDENTIFIER_REL r where r.combine_no in (select combine_no from MPI_INDEX_IDENTIFIER_REL t start with combine_no = ? ");
		sb.append("connect by combine_rec = prior combine_no)");
		indexIdentifierRelDao.deleteRecurByCombinNo(sb.toString(), combineNo);
		
	}
		
}
