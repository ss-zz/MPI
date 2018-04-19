package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IBookLogDao;
import com.sinosoft.mpi.model.BookLog;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.util.PageInfo;

@Service("bookLogService")
public class BookLogService implements IBookLogService {
	private Logger logger = Logger.getLogger(BookLogService.class);

	@Resource
	private IBookLogDao bookLogDao;

	@Override
	public void save(BookLog t) {
		bookLogDao.add(t);
		logger.debug("Add BookLog:" + t);
	}

	@Override
	public void update(BookLog t) {
		bookLogDao.update(t);
		logger.debug("Update BookLog:" + t);
	}

	@Override
	public void delete(BookLog t) {
		bookLogDao.deleteById(t);
		logger.debug("Delete BookLog:" + t);
	}

	@Override
	public BookLog getObject(String id) {
		BookLog t = new BookLog();
		t.setBookId(id);
		t = bookLogDao.findById(t);
		logger.debug("Load BookLog[id=" + id + "],result=" + t);
		return t;
	}

	@Override
	public List<BookLog> queryForPage(BookLog t, PageInfo page) {
		String sql = "select * from mpi_book_log where 1=1 ";
		String countSql = page.buildCountSql(sql);
		logger.debug("Execute sql[" + countSql + "]");
		page.setTotal(bookLogDao.getCount(countSql, new Object[] {}));
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql[" + querySql + "]");
		return bookLogDao.find(querySql, new Object[] {});
	}

	@Override
	public BookLog save(String personId, String indexid, String eventType) {

		return bookLogDao.autoFillAdd(personId, indexid, eventType);
	}

	@Override
	public List<BookLog> save(String personId) {
		return bookLogDao.autoFillAdd(personId);
	}

	@Override
	public List<BookLog> queryDatas(IdentifierDomain domain) {
		// XXX ben 此处可能需要更加详细的现在
		// 比如发送几次后就不再发送 产生通知
		// 暂按最简实现
		String sql = "select * from mpi_book_log t where t.domain_id = ? and t.op_result != '1' ";
		PageInfo page = new PageInfo(0, 100);

		return bookLogDao.find(page.buildPageSql(sql), new Object[] { domain.getDOMAIN_ID() });
	}

	@Override
	public List<BookLog> queryDatasByDomain(String domainUniqueSign) {
		// XXX ben 此处可能需要更详细修改 暂按最简实现
		String sql = "select * from mpi_book_log t where t.op_result != '1' and t.unique_sign = ?";
		// 每次查询出100条返回
		PageInfo page = new PageInfo(0, 100);
		String querySql = page.buildPageSql(sql);

		return bookLogDao.find(querySql, new Object[] { domainUniqueSign });
	}

	public void setBookLogDao(IBookLogDao bookLogDao) {
		this.bookLogDao = bookLogDao;
	}
}
