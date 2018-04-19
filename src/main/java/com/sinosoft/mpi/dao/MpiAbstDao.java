package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.util.IDUtil;

@Repository("mpiAbstDao")
public class MpiAbstDao implements IMpiAbstDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addMpiAbst(final PersonIndex personIndex, final String seach_condition, final String abst) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into MPI_ABST(").append(" NAME,BIRTH_DATE,GENDER_CD,CARD_MARITAL_ST_CD,ID_NO_CD,")
				.append("ID_NO,SEARCH_CONDITION,MPI_PK,MEDICAL_INSURANCE_NO,CARD_OCCU_TYPE_CD,")
				.append(" MEDICARE_CD,CARD_NATION_CD,NATIONALITY_CD,HR_ID,TEL_TYPE_CD,")
				.append(" PERSON_TEL_NO,ABST1,FIELD_PK)").append(" values(?,?,?,?,?,").append("?,?,?,?,?,")
				.append("?,?,?,?,?,").append("?,?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, personIndex.getNAME_CN());
				ps.setDate(2, new java.sql.Date(personIndex.getBIRTH_DATE().getTime()));
				ps.setString(3, personIndex.getGENDER_CD());
				ps.setString(4, personIndex.getCARD_MARITAL_ST_CD());
				ps.setString(5, personIndex.getID_NO_CD());
				ps.setString(6, personIndex.getID_NO());
				ps.setString(7, seach_condition);
				ps.setString(8, personIndex.getMPI_PK());
				ps.setString(9, personIndex.getMEDICAL_INSURANCE_NO());
				ps.setString(10, personIndex.getCARD_OCCU_TYPE_CD());
				ps.setString(11, personIndex.getMEDICARE_CD());
				ps.setString(12, personIndex.getCARD_NATION_CD());
				ps.setString(13, personIndex.getNATIONALITY_CD());
				ps.setString(14, personIndex.getHR_ID());
				ps.setString(15, personIndex.getTEL_TYPE_CD());
				ps.setString(16, personIndex.getPERSON_TEL_NO());
				ps.setString(17, abst);
				ps.setString(18, IDUtil.getUUID());

			}
		});

	}

	@Override
	public void deleteById(final PersonIndex personIndex) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"update  PINYIN_KIND set times=times-1 where pinyinkey in (select search_condition from mpi_abst where mpi_pk=?)");
		jdbcTemplate.update(sql.toString(), personIndex.getMPI_PK());
		sql.setLength(0);
		sql.append("delete from mpi_abst where mpi_pk = ?");
		jdbcTemplate.update(sql.toString(), personIndex.getMPI_PK());

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
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public PersonIndex findById(PersonIndex entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonIndex> find(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonIndex> find(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonIndex> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(PersonIndex entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(PersonIndex entity) {
		// TODO Auto-generated method stub

	}
}
