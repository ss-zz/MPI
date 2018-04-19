package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.util.IDUtil;

@Repository("identifierDomainDao")
public class IdentifierDomainDao implements IIdentifierDomainDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final IdentifierDomain entity) {
		entity.setDOMAIN_ID(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append("insert into mpi_identifier_domain ")
				.append("(domain_id,unique_sign,domain_type,domain_desc,push_addr,book_type, ")
				.append(" create_user_id,create_date,update_user_id,update_date,domain_level) ")
				.append(" values(?,?,?,?,?,?,?,sysdate,?,sysdate,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getDOMAIN_ID());
				ps.setString(2, entity.getUNIQUE_SIGN());
				ps.setString(3, entity.getDOMAIN_TYPE());
				ps.setString(4, entity.getDOMAIN_DESC());
				ps.setString(5, entity.getPUSH_ADDR());
				ps.setString(6, entity.getBOOK_TYPE());
				ps.setString(7, entity.getCREATE_USER_ID());
				ps.setString(8, entity.getUPDATE_USER_ID());
				ps.setString(9, entity.getDOMAIN_LEVEL());
			}
		});

	}

	@Override
	public void deleteById(final IdentifierDomain entity) {
		String sql = " delete from mpi_identifier_domain where domain_id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getDOMAIN_ID());
			}
		});

	}

	@Override
	public void update(final IdentifierDomain entity) {
		if (entity == null || entity.getDOMAIN_ID() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_identifier_domain set ").append(
				" unique_sign=?,domain_type=?,domain_desc=?,push_addr=?,book_type=?,update_user_id=?,update_date=sysdate ")
				.append(" ,domain_level=? where domain_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getUNIQUE_SIGN());
				ps.setString(2, entity.getDOMAIN_TYPE());
				ps.setString(3, entity.getDOMAIN_DESC());
				ps.setString(4, entity.getPUSH_ADDR());
				ps.setString(5, entity.getBOOK_TYPE());
				ps.setString(6, entity.getUPDATE_USER_ID());
				ps.setString(7, entity.getDOMAIN_LEVEL());
				ps.setString(8, entity.getDOMAIN_ID());
			}
		});

	}

	@Override
	public IdentifierDomain findById(IdentifierDomain entity) {
		String sql = " select * from mpi_identifier_domain where domain_id = ? ";
		List<IdentifierDomain> datas = find(sql, new Object[] { entity.getDOMAIN_ID() });
		IdentifierDomain result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	public IdentifierDomain findById(String domainid) {
		String sql = " select * from mpi_identifier_domain where domain_id = ? ";
		List<IdentifierDomain> datas = find(sql, new Object[] { domainid });
		IdentifierDomain result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<IdentifierDomain> find(String sql) {
		List<IdentifierDomain> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<IdentifierDomain> find(String sql, Object[] args) {
		List<IdentifierDomain> datas = jdbcTemplate.query(sql, args, new RowMapper<IdentifierDomain>() {
			@Override
			public IdentifierDomain mapRow(ResultSet rs, int row) throws SQLException {
				IdentifierDomain result = new IdentifierDomain();
				result.setDOMAIN_ID(rs.getString("DOMAIN_ID"));
				result.setUNIQUE_SIGN(rs.getString("UNIQUE_SIGN"));
				result.setDOMAIN_TYPE(rs.getString("DOMAIN_TYPE"));
				result.setDOMAIN_DESC(rs.getString("DOMAIN_DESC"));
				result.setPUSH_ADDR(rs.getString("PUSH_ADDR"));
				result.setBOOK_TYPE(rs.getString("BOOK_TYPE"));
				result.setCREATE_USER_ID(rs.getString("CREATE_USER_ID"));
				result.setCREATE_DATE(rs.getTimestamp("CREATE_DATE"));
				result.setUPDATE_USER_ID(rs.getString("UPDATE_USER_ID"));
				result.setUPDATE_DATE(rs.getTimestamp("UPDATE_DATE"));
				result.setDOMAIN_LEVEL(rs.getString("DOMAIN_LEVEL"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<IdentifierDomain> findAll() {
		String sql = " select * from mpi_identifier_domain where 1=1 ";
		List<IdentifierDomain> datas = find(sql);
		return datas;
	}

	@Override
	public int getCount(String sql) {
		return getCount(sql, new Object[] {});
	}

	@Override
	public int getCount(String sql, Object[] args) {
		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public IdentifierDomain getByUniqueSign(String sign) {
		String sql = " select * from mpi_identifier_domain where unique_sign = ? and rownum <2 ";
		List<IdentifierDomain> list = find(sql, new Object[] { sign });
		IdentifierDomain domain = null;
		if (list != null && !list.isEmpty()) {
			domain = list.get(0);
		}
		return domain;
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public IdentifierDomain getByPersonId(String personId) {
		String sql = " select * from mpi_identifier_domain a where exists( select 1 from mpi_index_identifier_rel b "
				+ " where b.domain_id = a.domain_id and b.field_pk = ? ) ";
		IdentifierDomain result = null;
		List<IdentifierDomain> list = find(sql, new Object[] { personId });
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}
}
