package com.sinosoft.mpi.util;

import java.io.Serializable;

/**
 * 简单通过当前页 还有页条数，算出开始条数到结束条数
 */
@SuppressWarnings("serial")
public class PageInfo implements Serializable{

	private int currentPage;// 从0开始
	private int limit;
	private int page; // 前台传递来的分页参数 需要显示地几页
	private int rows; // 前台传递来的参数 每页显示多少条
	private int total; // 总数

	public PageInfo() {
		super();
	}

	public PageInfo(int currentPage, int limit) {
		this.currentPage = currentPage;
		this.limit = limit;
	}

	public int getStartRowNum() {
		return currentPage * limit + 1;
	}

	public int getEndRowNum() {
		return currentPage * limit + limit;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		this.currentPage = (page - 1) < 0 ? 0 : (page - 1);
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
		this.limit = rows;
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
		int curr = start / lim - (lim == 1 ? 1 : 0);
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
}
