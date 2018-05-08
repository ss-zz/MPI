package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.util.IDUtil;

@Repository
public class IndexIdentifierRelDao extends IBaseDao<IndexIdentifierRel> {

	@Override
	public void add(final IndexIdentifierRel entity) {
		StringBuilder sql = new StringBuilder();
		entity.setCOMBINE_NO(IDUtil.query4Long(jdbcTemplate, "select SEQ_COMBINE_NO.nextval from dual"));
		sql.append(
				"insert into mpi_index_identifier_rel(combine_no,domain_id,mpi_pk,field_pk,combine_rec,PERSON_IDENTIFIER) values(?,?,?,?,?,?)");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, entity.getCOMBINE_NO());
				ps.setString(2, entity.getDOMAIN_ID());
				ps.setString(3, entity.getMPI_PK());
				ps.setString(4, entity.getFIELD_PK());
				ps.setLong(5, entity.getCOMBINE_REC());
				ps.setString(6, entity.getPERSON_IDENTIFIER());
			}
		});

	}

	@Override
	public void deleteById(final IndexIdentifierRel entity) {
		String sql = " delete from mpi_index_identifier_rel where combine_no=? ";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, entity.getCOMBINE_NO());
			}
		});
	}

	public void deleteByFieldPK(final String field_pk) {
		String sql = " delete from mpi_index_identifier_rel where field_pk = ? ";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, field_pk);
			}
		});
	}

	public void deleteByMpiPK(final String mpi_pk) {
		String sql = " delete from mpi_index_identifier_rel where mpi_pk = ? ";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, mpi_pk);
			}
		});
	}

	@Override
	public void update(final IndexIdentifierRel entity) {
		// XXX ben 关系表不需更新操作 这里不实现
	}

	@Override
	public IndexIdentifierRel findById(IndexIdentifierRel entity) {
		String sql = " select index_id,identifier_id from mpi_index_identifier_rel where index_id = ? and identifier_id = ? ";
		List<IndexIdentifierRel> datas = find(sql, new Object[] { entity.getMPI_PK(), entity.getPERSON_IDENTIFIER() });
		IndexIdentifierRel result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<IndexIdentifierRel> find(String sql) {
		List<IndexIdentifierRel> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<IndexIdentifierRel> find(String sql, Object[] args) {
		List<IndexIdentifierRel> datas = jdbcTemplate.query(sql, args, new RowMapper<IndexIdentifierRel>() {
			@Override
			public IndexIdentifierRel mapRow(ResultSet rs, int row) throws SQLException {
				IndexIdentifierRel result = new IndexIdentifierRel();
				result.setMPI_PK(rs.getString("MPI_PK"));
				result.setPERSON_IDENTIFIER(rs.getString("PERSON_IDENTIFIER"));
				result.setFIELD_PK(rs.getString("FIELD_PK"));
				result.setCOMBINE_REC(new Long(rs.getString("COMBINE_REC")));
				result.setDOMAIN_ID(rs.getString("DOMAIN_ID"));
				result.setCOMBINE_NO(new Long(rs.getString("COMBINE_NO")));
				// result.setCOMBINE_TYPE(rs.getString("COMBINE_TYPE"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<IndexIdentifierRel> findAll() {
		String sql = " select * from mpi_index_identifier_rel where 1=1 ";
		List<IndexIdentifierRel> datas = find(sql);
		return datas;
	}

	public IndexIdentifierRel findByFieldPK(String field_pk) {
		String sql = " select * from mpi_index_identifier_rel where field_pk = ? order by combine_no desc";
		List<IndexIdentifierRel> list = find(sql, new Object[] { field_pk });
		IndexIdentifierRel result = null;
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	public List<IndexIdentifierRel> findByMpiPK(String mpi_pk) {
		String sql = " select * from mpi_index_identifier_rel where mpi_pk = ? order by combine_no asc";
		List<IndexIdentifierRel> list = find(sql, new Object[] { mpi_pk });
		return list;
	}

	public IndexIdentifierRel findByMpiPKByLatest(String mpi_pk) {
		String sql = " select * from mpi_index_identifier_rel  where mpi_pk = ? order by COMBINE_REC desc ";
		List<IndexIdentifierRel> list = find(sql, new Object[] { mpi_pk });
		IndexIdentifierRel result = null;
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	public void deleteRecurByCombinNo(String sql, Long args) {
		jdbcTemplate.update(sql, new Object[] { args });
	}

	public void deleteByMpiPKFieldPk(final String mpi_pk, String field_pk) {
		String sql = " delete from mpi_index_identifier_rel where mpi_pk = '" + mpi_pk + "' and field_pk = '" + field_pk
				+ "' ";
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
