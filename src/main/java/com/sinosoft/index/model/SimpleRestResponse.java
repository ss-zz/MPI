package com.sinosoft.index.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单请求返回对象
 * 
 * @author sinosoft
 *
 */
public class SimpleRestResponse {

	public static Map<String, Object> create(String key, Object value) {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put(key, value);
		return ret;
	}

}
