package com.sinosoft.mpi.context;

public class Constant {

	public final static String PAGE_TOTAL = "total";
	public final static String PAGE_ROWS = "rows";
	public final static String ENCODING_UTF8 = "utf-8";

	public final static String TOTAL_DEGREE = "totalDegree";

	/**
	 * 索引操作日志 类型 匹配
	 */
	public final static String IDX_LOG_TYPE_MATCH = "1";
	/**
	 * 索引操作日志 类型 修订
	 */
	public final static String IDX_LOG_TYPE_MODIFY = "2";

	/**
	 * 索引操作日志 方式 自动合并
	 */
	public final static String IDX_LOG_STYLE_AUTO_MERGE = "1";
	/**
	 * 索引操作日志 方式 自动新增
	 */
	public final static String IDX_LOG_STYLE_AUTO_NEW = "2";
	/**
	 * 索引操作日志 方式 自动拆分
	 */
	public final static String IDX_LOG_STYLE_AUTO_SPLIT = "3";
	/**
	 * 索引操作日志 方式 手工合并
	 */
	public final static String IDX_LOG_STYLE_MAN_MERGE = "4";
	/**
	 * 索引操作日志 方式 手工新增
	 */
	public final static String IDX_LOG_STYLE_MAN_NEW = "5";
	/**
	 * 索引操作日志 方式 手工拆分
	 */
	public final static String IDX_LOG_STYLE_MAN_SPLIT = "6";
	/**
	 * 索引日志 方式 解除绑定
	 */
	public final static String IDX_LOG_STYLE_MAN_REMOVE = "7";

	/**
	 * 订阅信息日志 绑定到索引
	 */
	public final static String BOOK_LOG_TYPE_ADD = "0";
	/**
	 * 订阅信息日志 与索引解除绑定
	 */
	public final static String BOOK_LOG_TYPE_RELEASE = "1";
}
