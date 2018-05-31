package com.sinosoft.mpi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.mpi.ManOpPersonDao;
import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 需要人工干预的居民服务
 */
@Service
public class ManOpPersonService {

	@Resource
	private ManOpPersonDao manOpPersonDao;
	@Resource
	JdbcTemplate jdbcTemplate;

	/**
	 * 保存
	 */
	public void save(ManOpPerson t) {
		manOpPersonDao.save(t);
	}

	/**
	 * 更新
	 */
	public void update(ManOpPerson t) {
		manOpPersonDao.save(t);
	}

	/**
	 * 删除-根据id
	 */
	public void delete(ManOpPerson t) {
		manOpPersonDao.delete(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public ManOpPerson get(String id) {
		return manOpPersonDao.findOne(id);
	}

	/**
	 * 更新数据状态
	 * 
	 * @param manOpId
	 * @param state
	 */
	public int updateState(String manOpId, String state) {
		return manOpPersonDao.updateState(manOpId, state, DateUtil.getTimeNow());
	}

	/**
	 * 更新数据状态-已操作
	 * 
	 * @param manOpId
	 */
	public int updateStateDone(String manOpId) {
		return updateState(manOpId, "1");
	}

	/**
	 * 更新数据状态-未操作
	 * 
	 * @param manOpId
	 */
	public int updateStateUnDone(String manOpId) {
		return updateState(manOpId, "0");
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<ManOpPerson> queryForPage(ManOpPerson t, PageInfo page) {
		return manOpPersonDao.findAll(page).getContent();
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryForMapPage(Map<String, String> params, PageInfo page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.man_op_id,a.field_pk,a.man_op_status,a.man_op_time,b.name_cn,b.gender_cd,b.gender_dn ")
				.append(" ,b.id_no,to_char(b.birth_date, 'yyyy-MM-dd') birth_date,b.person_tel_no,d.domain_desc from mpi_man_op_person a ")
				.append(" left join mpi_person_info b on a.field_pk = b.field_pk ")
				.append(" left join mpi_index_identifier_rel c on b.field_pk = c.field_pk ")
				.append(" left join mpi_identifier_domain d on c.domain_id = d.domain_id where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		// 设置查询条件
		if (StringUtils.isNotBlank(params.get("manOpStatus"))) {
			sql.append(" and a.man_op_status = ? ");
			args.add(params.get("manOpStatus"));
		}

		if (StringUtils.isNotBlank(params.get("personName"))) {
			sql.append(" and b.name like ? ");
			args.add(buildLikeStr(params.get("personName")));
		}

		if (StringUtils.isNotBlank(params.get("genderCd"))) {
			sql.append(" and b.gender_cd = ? ");
			args.add(params.get("genderCd"));
		}

		// 取得总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询设置分页记录的总记录数
		page.setTotal(jdbcTemplate.queryForObject(countSql, args.toArray(), Integer.class));
		// 取得分页查询语句
		sql.append(" order by a.man_op_status asc, a.man_op_time desc ");
		String querySql = page.buildPageSql(sql);
		return jdbcTemplate.queryForList(querySql.toString(), args.toArray());
	}

	private String buildLikeStr(String arg) {
		return "%" + StringUtils.trim(arg) + "%";
	}
}
