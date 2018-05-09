package com.sinosoft.mpi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.ManOpPersonDao;
import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.util.PageInfo;

@Service
public class ManOpPersonService {

	private Logger logger = Logger.getLogger(ManOpPersonService.class);

	@Resource
	private ManOpPersonDao manOpPersonDao;

	public void save(ManOpPerson t) {
		manOpPersonDao.add(t);
		logger.debug("Add ManOpPerson:" + t);
	}

	public void update(ManOpPerson t) {
		manOpPersonDao.update(t);
		logger.debug("update ManOpPerson:" + t);
	}

	public void delete(ManOpPerson t) {
		manOpPersonDao.deleteById(t);
		logger.debug("delete ManOpPerson,manOpId=" + t.getMAN_OP_ID());
	}

	public ManOpPerson getObject(String id) {
		ManOpPerson t = new ManOpPerson();
		t.setMAN_OP_ID(id);
		t = manOpPersonDao.findById(t);
		logger.debug("Load ManOpPerson:manOpId=" + id + ",result=" + t);
		return t;
	}

	public List<ManOpPerson> queryForPage(ManOpPerson t, PageInfo page) {
		String sql = " select * from mpi_man_op_person where 1=1 ";
		sql = page.buildPageSql(sql);
		logger.debug("Execute sql:" + sql);
		return manOpPersonDao.find(sql, new Object[] {});
	}

	public List<Map<String, Object>> queryForMapPage(ManOpPerson t, PageInfo page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.man_op_id,a.field_pk,a.man_op_status,a.man_op_time,b.name_CN,b.gender_cd ")
				.append(" ,b.id_no,b.birth_date,b.person_tel_no,d.domain_desc from mpi_man_op_person a ")
				.append(" left join mpi_person_info b on a.field_pk = b.field_pk ")
				.append(" left join mpi_index_identifier_rel c on b.field_pk = c.field_pk ")
				.append(" left join mpi_identifier_domain d on c.domain_id = d.domain_id where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		// 设置查询条件
		if (StringUtils.isNotBlank(t.getMAN_OP_STATUS())) {
			sql.append(" and a.man_op_status = ? ");
			args.add(t.getMAN_OP_STATUS());
		}

		if (StringUtils.isNotBlank(t.getFIELD_PK())) {
			sql.append(" and b.name like ? ");
			args.add(buildLikeStr(t.getFIELD_PK()));
		}

		if (StringUtils.isNotBlank(t.getMAN_OP_ID())) {
			sql.append(" and b.gender_cd = ? ");
			args.add(t.getMAN_OP_ID());
		}

		// 取得总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询设置分页记录的总记录数
		page.setTotal(manOpPersonDao.getCount(countSql, args.toArray()));
		logger.debug("Execute sql:" + countSql);
		// 取得分页查询语句
		sql.append(" order by a.man_op_status asc, a.man_op_time desc ");
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:" + querySql);
		return manOpPersonDao.findForMap(querySql, args.toArray());
	}

	private String buildLikeStr(String arg) {
		return "%" + StringUtils.trim(arg) + "%";
	}
}
