package com.sinosoft.mpi.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 简单通过当前页 还有页条数，算出开始条数到结束条数
 */
public class PageInfo implements Pageable {

	public static final int DEFAULT_FIRST_PAGE = 0;
	public static final int DEFAULT_ROWS = 10;

	private int page; // 前台传递来的分页参数 需要显示地几页
	private int rows; // 前台传递来的参数 每页显示多少条
	private int total; // 总数

	public PageInfo() {
		super();
		this.page = DEFAULT_FIRST_PAGE;
		this.rows = DEFAULT_ROWS;
	}

	public PageInfo(int page, int rows) {
		this.page = page;
		this.rows = rows;
	}

	public int getStartRowNum() {
		return page * rows + 1;
	}

	public int getEndRowNum() {
		return page * rows + rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page <= DEFAULT_FIRST_PAGE ? DEFAULT_FIRST_PAGE : page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 根据 起始 结束页数 取得 分页对象
	 * 
	 * @param start
	 *            起始页数 从1开始
	 * @param end
	 *            结束页数
	 * @return 分页信息
	 */
	public static PageInfo getByStartAndEnd(int start, int end) {
		int lim;
		if (end < start) {
			lim = start - end + 1;
		} else {
			lim = end - start + 1;
		}
		int curr = start / lim - (lim == 1 ? 1 : DEFAULT_FIRST_PAGE);
		return new PageInfo(curr, lim);
	}

	/**
	 * 根据原sql语句包装成分页查询语句
	 * 
	 * @param sql
	 * @return
	 */
	public String buildPageSql(CharSequence sql) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT * FROM ( SELECT QUERY_TABLE.*, ROWNUM AS ROWNO FROM ( ").append(sql)
				.append(" ) QUERY_TABLE WHERE ROWNUM <= ").append(getEndRowNum())
				.append(" ) TABLE_ALIAS where TABLE_ALIAS.rowno >= ").append(getStartRowNum());
		return sb.toString();
	}

	/**
	 * 根据原sql语句包装成总数查询语句
	 * 
	 * @param sql
	 * @return
	 */
	public String buildCountSql(CharSequence sql) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(1) from ( ").append(sql).append(" ) ");
		return sb.toString();
	}

	@Override
	public int getPageNumber() {
		return this.page;
	}

	@Override
	public int getPageSize() {
		return this.rows;
	}

	@Override
	public int getOffset() {
		return this.page * this.rows;
	}

	@Override
	public Sort getSort() {
		return null;
	}

	@Override
	public Pageable next() {
		return new PageInfo(page + 1, rows);
	}

	@Override
	public Pageable previousOrFirst() {
		if (page <= DEFAULT_FIRST_PAGE) {
			return new PageInfo(DEFAULT_FIRST_PAGE, rows);
		} else {
			return new PageInfo(page - 1, rows);
		}
	}

	@Override
	public Pageable first() {
		return new PageInfo(DEFAULT_FIRST_PAGE, rows);
	}

	@Override
	public boolean hasPrevious() {
		return page <= DEFAULT_FIRST_PAGE ? false : true;
	}
}
