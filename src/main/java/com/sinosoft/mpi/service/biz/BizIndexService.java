package com.sinosoft.mpi.service.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.biz.BizIdxLogDao;
import com.sinosoft.mpi.dao.biz.BizIndexDao;
import com.sinosoft.mpi.model.biz.BizIndex;
import com.sinosoft.mpi.model.biz.BizInfo;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 主索引业务服务
 */
@Service
@CacheDefaults(cacheName = "bizCache")
public class BizIndexService {

	@Resource
	private BizIndexDao bizIndexDao;
	@Resource
	private BizIndexUpdateService bizIndexUpdateService;
	@Resource
	private BizIdxLogDao bizIdxLogDao;
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 构建查询条件
	 * 
	 * @param sql
	 * @param p
	 * @return
	 */
	private List<Object> buildQueryConditions(final StringBuilder sql, BizIndex p) {
		List<Object> args = new ArrayList<Object>();

		return args;
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(BizIndex t) {
		bizIndexDao.deleteById(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public BizIndex getObject(String id) {
		BizIndex t = new BizIndex();
		t.setId(id);
		t = bizIndexDao.findById(t);
		return t;
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<BizIndex> queryForPage(BizIndex t, PageInfo page) {
		String sql = " select * from mpi_person_index where 1=1 ";
		sql = page.buildPageSql(sql);
		return bizIndexDao.find(sql, new Object[] {});
	}

	/**
	 * 分页查询
	 * 
	 * @param index
	 * @param page
	 * @return
	 */
	@CacheResult
	public List<Map<String, Object>> queryForSplitPage(BizIndex index, PageInfo page) {

		StringBuilder sql = new StringBuilder();
		sql.append(
				"select * from (select a.MPI_PK,a.MPI_PK row_id,1 row_type,a.name_cn,getCodeValue('GBT226112003',a.gender_cd) gender_cd,a.gender_dn,a.birth_date,a.id_no,a.person_tel_no,nvl(b.person_count,0) person_count, ");
		sql.append(" decode(nvl(b.person_count,0),0,'open','closed') \"state\" ");
		sql.append(
				" ,(select distinct (g.mpi_pk) from mpi_index_operate g where g.op_style = 4 and g.mpi_pk = a.MPI_PK) mergeStatus");
		sql.append(" from mpi_person_index a left join ( select count(c.DOMAIN_ID) person_count,c.MPI_PK ");
		sql.append(
				" from mpi_index_identifier_rel c group by c.MPI_PK ) b on a.MPI_PK = b.MPI_PK where 1=1 and a.state != 1");

		// 添加查询条件
		List<Object> args = buildQueryConditions(sql, index);
		sql.append(" ) l ");

		return querySplitData(page, sql, args);
	}

	/**
	 * 分页查询
	 * 
	 * @param index
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryForSplitPage(BizIndex index, String fromIndexId, PageInfo page) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select a.index_id,a.index_id row_id,1 row_type,a.name,a.sex,a.birth_date,a.id_no,a.phone_one,nvl(b.person_count,0) person_count, ");
		sql.append(" decode(nvl(b.person_count,0),0,'open','closed') \"state\" ");
		sql.append(" from mpi_person_index a left join ( select count(c.identifier_id) person_count,c.index_id ");
		sql.append(
				" from mpi_index_identifier_rel c group by c.index_id ) b on a.index_id = b.index_id where 1=1 and a.index_id != ? ");
		// 添加查询条件
		List<Object> args = buildQueryConditions(sql, index);
		args.add(0, fromIndexId);

		return querySplitData(page, sql, args);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param sql
	 * @param args
	 * @return
	 */
	private List<Map<String, Object>> querySplitData(PageInfo page, StringBuilder sql, List<Object> args) {
		// 取得总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询设置分页记录的总记录数
		page.setTotal(bizIndexDao.getCount(countSql, args.toArray()));
		// 取得分页查询语句
		String querySql = page.buildPageSql(sql);
		return bizIndexDao.findForMap(querySql, args.toArray());
	}

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(BizIndex t) {
		bizIndexDao.add(t);
	}

	/**
	 * 直接更新 索引信息
	 */
	public void updateIndexDirect(BizInfo person, String id) {
		BizIndex idx = person.toIndex();
		idx.setId(id);
		bizIndexDao.update(idx);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(BizIndex t) {
		bizIndexDao.update(t);
	}

}
