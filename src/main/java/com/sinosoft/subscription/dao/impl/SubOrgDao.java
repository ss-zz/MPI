package com.sinosoft.subscription.dao.impl;

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

import com.sinosoft.subscription.dao.ISubOrgDao;
import com.sinosoft.subscription.model.SubscribeOrg;

/**
 * @ClassName: SubOrgDao
 * @Description: TODO订阅发布机构dao层
 * @author sinosoft_SunWG
 * @date 2013-11-19 下午3:45:35
 */
@Repository("subOrgDao")
public class SubOrgDao implements ISubOrgDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final SubscribeOrg orgConfig) {
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into PS_SUB_ORG_CONFIG(id,sb_orgcode,sb_orgname,sys_name, ");
		sb.append("      sys_code,sys_ip,sys_port,sys_deply,sys_admin,sys_tel, ");
		sb.append("      sys_flag,domain_id,zc_dt,ws_url,ws_method) ");
		sb.append(" values(SEQ_TABLE_ID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,? )");
		jdbcTemplate.update(sb.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, orgConfig.getSbOrgcode());
				ps.setString(2, orgConfig.getSbOrgname());
				ps.setString(3, orgConfig.getSysName());
				ps.setString(4, orgConfig.getSysCode());
				ps.setString(5, orgConfig.getSysIp());
				ps.setString(6, orgConfig.getSysPort());
				ps.setString(7, orgConfig.getSysDeply());
				ps.setString(8, orgConfig.getSysAdmin());
				ps.setString(9, orgConfig.getSysTel());
				ps.setString(10, orgConfig.getSysFlag());
				ps.setString(11, orgConfig.getDomainId());
				ps.setString(12, orgConfig.getWsUrl());
				ps.setString(13, orgConfig.getWsMethod());
			}
		});
	}

	@Override
	public void deleteById(final SubscribeOrg entity) {
		jdbcTemplate.update("DELETE FROM PS_SUB_CONFIG T WHERE T.ID = '" + entity.getId() + "'");
		String sql = " delete from PS_SUB_ORG_CONFIG where id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getId());
			}
		});
	}

	@Override
	public void update(final SubscribeOrg orgConfig) {
		if (orgConfig == null || orgConfig.getId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update PS_SUB_ORG_CONFIG t ");
		sql.append(" set t.sys_name = ?, ");
		sql.append("    t.sys_code = ?, ");
		sql.append("    t.sys_ip = ? , ");
		sql.append("    t.sys_port = ?, ");
		sql.append("    t.sys_deply = ?, ");
		sql.append("    t.sys_admin = ?, ");
		sql.append("    t.sys_tel = ?, 	");
		sql.append("    t.sys_flag = ?, ");
		sql.append("    t.domain_id = ?, ");
		sql.append("    t.ws_url = ?,  ");
		sql.append("    t.ws_method = ?	");
		sql.append(" where t.id = ?  ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, orgConfig.getSysName());
				ps.setString(2, orgConfig.getSysCode());
				ps.setString(3, orgConfig.getSysIp());
				ps.setString(4, orgConfig.getSysPort());
				ps.setString(5, orgConfig.getSysDeply());
				ps.setString(6, orgConfig.getSysAdmin());
				ps.setString(7, orgConfig.getSysTel());
				ps.setString(8, orgConfig.getSysFlag());
				ps.setString(9, orgConfig.getDomainId());
				ps.setString(10, orgConfig.getWsUrl());
				ps.setString(11, orgConfig.getWsMethod());
				ps.setString(12, orgConfig.getId());
			}
		});
	}

	@Override
	public SubscribeOrg findById(SubscribeOrg entity) {
		return null;
	}

	@Override
	public List<SubscribeOrg> find(String sql) {
		return find(sql, new Object[] {});
	}

	public List<SubscribeOrg> find(String sql, Object[] args) {
		List<SubscribeOrg> datas = jdbcTemplate.query(sql, args, new RowMapper<SubscribeOrg>() {
			@Override
			public SubscribeOrg mapRow(ResultSet rs, int row) throws SQLException {
				SubscribeOrg result = new SubscribeOrg();
				result.setId(rs.getString("ID"));
				result.setSbOrgcode(rs.getString("ORGCODE"));
				result.setSbOrgname(rs.getString("ORGNAME"));
				result.setSysName(rs.getString("SYSNAME"));
				result.setSysCode(rs.getString("SYSCODE"));
				result.setSysIp(rs.getString("SYSIP"));
				result.setSysPort(rs.getString("SYSPORT"));
				result.setSysDeply(rs.getString("SYSDEPLY"));
				result.setSysAdmin(rs.getString("SYSADMIN"));
				result.setSysTel(rs.getString("SYSTEL"));
				result.setSysFlag(rs.getString("SYSFLAG"));
				result.setDomainId(rs.getString("DOMAINID"));
				result.setZcDate(rs.getString("ZCDT"));
				result.setWsUrl(rs.getString("WSURL"));
				result.setWsMethod(rs.getString("METHOD"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<SubscribeOrg> findAll() {
		return null;
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

}
