package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.BookLog;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.IDUtil;

@Component
public class BookLogDao extends IBaseDao<BookLog> {

	@Resource
	private IndexIdentifierRelDao indexIdentifierRelDao;

	@Override
	public void add(final BookLog entity) {
		entity.setBookId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into mpi_book_log (book_id, event_type, mpi_pk, field_pk, ")
				.append(" domain_id, unique_sign, person_identifier, op_time, op_count, op_result) values ")
				.append(" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getBookId());
				ps.setString(2, entity.getEventType());
				ps.setString(3, entity.getMpipk());
				ps.setString(4, entity.getFieldPK());
				ps.setString(5, entity.getDomainId());
				ps.setString(6, entity.getUniqueSign());
				ps.setString(7, entity.getPersonIdentifier());
				ps.setString(8, entity.getOpTime());
				ps.setString(9, entity.getOpCount());
				ps.setString(10, entity.getOpResult());
			}
		});
	}

	@Override
	public void deleteById(final BookLog entity) {
		String sql = " delete from mpi_book_log where book_id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getBookId());
			}
		});

	}

	@Override
	public void update(final BookLog entity) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_book_log set event_type = ?,mpi_pk = ?,field_pk = ?,domain_id = ?, ")
				.append(" unique_sign = ?,person_identifier = ?,op_time = ?,op_count = ?,op_result = ? ")
				.append(" where book_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getEventType());
				ps.setString(2, entity.getMpipk());
				ps.setString(3, entity.getFieldPK());
				ps.setString(4, entity.getDomainId());
				ps.setString(5, entity.getUniqueSign());
				ps.setString(6, entity.getPersonIdentifier());
				ps.setString(7, entity.getOpTime());
				ps.setString(8, entity.getOpCount());
				ps.setString(9, entity.getOpResult());
				ps.setString(10, entity.getBookId());
			}
		});
	}

	@Override
	public BookLog findById(BookLog entity) {
		String sql = "select * from mpi_book_log where book_id = ?";
		List<BookLog> datas = find(sql, new Object[] { entity.getBookId() });
		BookLog result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<BookLog> find(String sql) {
		List<BookLog> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<BookLog> find(String sql, Object[] args) {
		List<BookLog> datas = jdbcTemplate.query(sql, args, new RowMapper<BookLog>() {
			@Override
			public BookLog mapRow(ResultSet rs, int rowNum) throws SQLException {
				BookLog result = new BookLog();
				result.setBookId(rs.getString("BOOK_ID"));
				result.setDomainId(rs.getString("DOMAIN_ID"));
				result.setEventType(rs.getString("EVENT_TYPE"));
				result.setMpipk(rs.getString("MPI_PK"));
				result.setOpCount(rs.getString("OP_COUNT"));
				result.setOpResult(rs.getString("OP_RESULT"));
				result.setOpTime(rs.getString("OP_TIME"));
				result.setFieldPK(rs.getString("FIELD_PK"));
				result.setPersonIdentifier(rs.getString("PERSON_IDENTIFIER"));
				result.setUniqueSign(rs.getString("UNIQUE_SIGN"));
				return result;
			}

		});
		return datas;
	}

	@Override
	public List<BookLog> findAll() {
		String sql = " select * from mpi_book_log where 1=1 ";
		List<BookLog> datas = find(sql);
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

	@Override
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	public BookLog autoFillAdd(String fieldpk, String mpipk, String eventType) {
		Map<String, Object> map = getDomainInfo(fieldpk);
		BookLog t = new BookLog();
		t.setDomainId(map.get("DOMAIN_ID").toString());
		t.setPersonIdentifier("");
		t.setUniqueSign(map.get("UNIQUE_SIGN").toString());
		t.setEventType(eventType);
		t.setMpipk(mpipk);
		t.setOpCount("0");
		t.setOpResult("0");
		t.setOpTime(DateUtil.getTimeNow(new Date()));
		t.setFieldPK(fieldpk);
		add(t);
		return t;
	}

	private Map<String, Object> getDomainInfo(String field_pk) {
		String sql = " select a.field_pk,c.unique_sign,c.domain_id,b.mpi_pk from mpi_person_info a "
				+ " left join mpi_index_identifier_rel b on a.field_pk = b.field_pk "
				+ " left join mpi_identifier_domain c on c.domain_id = b.domain_id where a.field_pk = ? ";
		List<Map<String, Object>> list = findForMap(sql, new Object[] { field_pk });
		if (list == null || list.isEmpty()) {
			throw new ValidationException("无法根据居民id[" + field_pk + "]取得相关域信息");
		}
		Map<String, Object> map = list.get(0);
		return map;
	}

	public List<BookLog> autoFillAdd(String personId) {
		// 取得域相关信息
		Map<String, Object> map = getDomainInfo(personId);
		// 取得所有关联的索引 (正常情况下只能有一个关联,多个情况属于异常)
		String sql = "select mpi_pk,field_pk from MPI_INDEX_IDENTIFIER_REL where field_pk=?";
		String domainId = (String) map.get("DOMAIN_ID");
		String personIdentifier = (String) map.get("PERSON_IDENTIFIER");
		String uniqueSign = (String) map.get("UNIQUE_SIGN");
		List<IndexIdentifierRel> rels = indexIdentifierRelDao.find(sql, new Object[] { map.get("identifier_id") });
		List<BookLog> result = new ArrayList<BookLog>(rels.size());
		// 迭代创建订阅日志对象并返回
		for (IndexIdentifierRel rel : rels) {
			BookLog t = new BookLog();
			t.setDomainId(domainId);
			t.setPersonIdentifier(personIdentifier);
			t.setUniqueSign(uniqueSign);
			t.setEventType(Constant.BOOK_LOG_TYPE_RELEASE);
			t.setMpipk(rel.getMPI_PK());
			t.setOpCount("0");
			t.setOpResult("0");
			t.setOpTime(DateUtil.getTimeNow(new Date()));
			t.setFieldPK(personId);
			add(t);
			result.add(t);
		}
		return result;
	}

}
