package com.sinosoft.mpi.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sinosoft.mpi.model.IBaseCode;

/**
 * 基础数据缓存操作类
 */
public class CacheManager {
	
	private static Logger logger = Logger.getLogger(CacheManager.class);
	private static Map<String, Map<String, IBaseCode>> cacheMap = new HashMap<String, Map<String, IBaseCode>>();

	/**
	 * 取得缓存的对象
	 * 
	 * @param clz
	 *            缓存对象的类型
	 * @param pkid
	 *            对象主键
	 * @return nullable
	 */
	@SuppressWarnings("unchecked")
	public static <T extends IBaseCode> T get(Class<T> clz, String pkid) {
		// 取得对象缓存map
		Map<String, IBaseCode> all = cacheMap.get(clz.getName());
		// 取得对象 按泛型转型后返回
		return ((T) all.get(pkid));
	}

	/**
	 * 根据编码值取得编码名称
	 * 
	 * @param clz
	 *            类
	 * @param pkid
	 *            主键
	 * @return 编码名称
	 */
	public static String getCodeName(Class<? extends IBaseCode> clz, String pkid) {
		// 取得对象缓存map
		Map<String, IBaseCode> all = cacheMap.get(clz.getName());
		IBaseCode code = all.get(pkid);
		if (code != null)
			return code.getCodeName();
		else
			return null;
	}

	/**
	 * 取得某类所有缓存对象
	 * 
	 * @param clz
	 *            缓存对象类型
	 * @return not nullable
	 */
	@SuppressWarnings("unchecked")
	public static <T extends IBaseCode> List<T> getAll(Class<T> clz) {
		Map<String, IBaseCode> all = cacheMap.get(clz.getName());
		List<T> result = new ArrayList<T>(all.size());
		for (Iterator<IBaseCode> iterator = all.values().iterator(); iterator.hasNext();) {
			result.add((T) iterator.next());
		}
		return result;
	}

	/**
	 * 添加缓存对象
	 * 
	 * @param clz
	 *            缓存对象类型
	 * @param code
	 *            需要缓存的对象
	 */
	@SuppressWarnings("rawtypes")
	static void put(Class clz, IBaseCode code) {
		Map<String, IBaseCode> all = initTableMap(clz);
		putData(code, all);
	}

	/**
	 * 批量添加缓存对象
	 * 
	 * @param clz
	 *            缓存对象类型
	 * @param codes
	 *            需要缓存对象集合
	 */
	@SuppressWarnings("rawtypes")
	static void putAll(Class clz, List<IBaseCode> codes) {
		Map<String, IBaseCode> all = initTableMap(clz);
		for (IBaseCode code : codes) {
			putData(code, all);
		}
	}

	/**
	 * 向缓存map中添加对象
	 */
	private static void putData(IBaseCode code, Map<String, IBaseCode> all) {
		String pkid = code.getCodeId();
		if (pkid != null) {
			all.put(pkid, code);
		} else {
			logger.error("所缓存数据的主键为空值");
			throw new NullPointerException("所缓存数据的主键为空值");
		}
	}

	/**
	 * 初始化缓存map
	 */
	@SuppressWarnings("rawtypes")
	private static Map<String, IBaseCode> initTableMap(Class clz) {
		Map<String, IBaseCode> all = cacheMap.get(clz.getName());
		if (all == null) {
			all = new LinkedHashMap<String, IBaseCode>();
			cacheMap.put(clz.getName(), all);
		}

		return all;
	}
}
