package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.BookLogDao;
import com.sinosoft.mpi.model.BookLog;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 订阅日志业务操作类
 */
@Service
public class BookLogService {

	@Resource
	private BookLogDao bookLogDao;

	public void save(BookLog t) {
		bookLogDao.add(t);
	}

	public void update(BookLog t) {
		bookLogDao.update(t);
	}

	public void delete(BookLog t) {
		bookLogDao.deleteById(t);
	}

	public BookLog getObject(String id) {
		BookLog t = new BookLog();
		t.setBookId(id);
		t = bookLogDao.findById(t);
		return t;
	}

	public List<BookLog> queryForPage(BookLog t, PageInfo page) {
		String sql = "select * from mpi_book_log where 1=1 ";
		String countSql = page.buildCountSql(sql);
		page.setTotal(bookLogDao.getCount(countSql, new Object[] {}));
		String querySql = page.buildPageSql(sql);
		return bookLogDao.find(querySql, new Object[] {});
	}

	/**
	 * 自动填充其他数据 入库
	 * 
	 * @param personId
	 *            居民id
	 * @param indexid
	 *            索引id
	 * @param eventType
	 *            操作类型 0-关联至索引 1-与索引解除关联
	 * @return 入库的BookLog对象
	 */
	public BookLog save(String personId, String indexid, String eventType) {
		return bookLogDao.autoFillAdd(personId, indexid, eventType);
	}

	/**
	 * 自动填充其他数据入库(与索引解除关联 批量)
	 * 
	 * @param personId
	 *            居民id
	 * @return 入库的BookLog对象
	 */
	public List<BookLog> save(String personId) {
		return bookLogDao.autoFillAdd(personId);
	}

	/**
	 * 查询取得需要发送的数据
	 */
	public List<BookLog> queryDatas(IdentifierDomain domain) {
		// XXX ben 此处可能需要更加详细的现在
		// 比如发送几次后就不再发送 产生通知
		// 暂按最简实现
		String sql = "select * from mpi_book_log t where t.domain_id = ? and t.op_result != '1' ";
		PageInfo page = new PageInfo(0, 100);

		return bookLogDao.find(page.buildPageSql(sql), new Object[] { domain.getDOMAIN_ID() });
	}

	/**
	 * 查询取得需要发送的数据
	 */
	public List<BookLog> queryDatasByDomain(String domainUniqueSign) {
		// XXX ben 此处可能需要更详细修改 暂按最简实现
		String sql = "select * from mpi_book_log t where t.op_result != '1' and t.unique_sign = ?";
		// 每次查询出100条返回
		PageInfo page = new PageInfo(0, 100);
		String querySql = page.buildPageSql(sql);

		return bookLogDao.find(querySql, new Object[] { domainUniqueSign });
	}

}
