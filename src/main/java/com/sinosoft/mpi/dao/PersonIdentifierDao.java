package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.PersonIdentifier;
import com.sinosoft.mpi.util.IDUtil;

@Repository
public class PersonIdentifierDao extends IBaseDao<PersonIdentifier> {

	@Override
	public void add(final PersonIdentifier entity) {
		entity.setIdentifierId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append("insert into mpi_person_identifier_rel ")
				.append(" (identifier_id,person_id,domain_id,person_identifier) ").append(" values(?,?,?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getIdentifierId());
				ps.setString(2, entity.getPersonId());
				ps.setString(3, entity.getDomainId());
				ps.setString(4, entity.getPersonIdentifier());
			}
		});

	}

	@Override
	public void deleteById(final PersonIdentifier entity) {
		String sql = " delete from mpi_person_identifier_rel where identifier_id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getIdentifierId());
			}
		});

	}

	@Override
	public void update(final PersonIdentifier entity) {
		if (entity == null || entity.getIdentifierId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_person_identifier set ").append(" person_id = ?,domain_id = ?,person_identifier = ? ")
				.append(" where identifier_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getPersonId());
				ps.setString(2, entity.getDomainId());
				ps.setString(3, entity.getPersonIdentifier());
				ps.setString(4, entity.getIdentifierId());
			}
		});
	}

	@Override
	public PersonIdentifier findById(PersonIdentifier entity) {
		String sql = " select * from mpi_person_identifier where identifier_id = ? ";
		List<PersonIdentifier> datas = find(sql, new Object[] { entity.getIdentifierId() });
		PersonIdentifier result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<PersonIdentifier> find(String sql) {
		List<PersonIdentifier> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<PersonIdentifier> find(String sql, Object[] args) {
		List<PersonIdentifier> datas = jdbcTemplate.query(sql, args, new RowMapper<PersonIdentifier>() {
			@Override
			public PersonIdentifier mapRow(ResultSet rs, int row) throws SQLException {
				PersonIdentifier result = new PersonIdentifier();
				result.setIdentifierId(rs.getString("IDENTIFIER_ID"));
				result.setPersonId(rs.getString("PERSON_ID"));
				result.setDomainId(rs.getString("DOMAIN_ID"));
				result.setPersonIdentifier(rs.getString("PERSON_IDENTIFIER"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<PersonIdentifier> findAll() {
		String sql = " select * from mpi_person_identifier where 1=1 ";
		List<PersonIdentifier> datas = find(sql, new Object[] {});
		return datas;
	}

}
