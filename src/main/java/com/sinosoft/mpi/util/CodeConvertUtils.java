package com.sinosoft.mpi.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.model.CertCode;
import com.sinosoft.mpi.model.EduCode;
import com.sinosoft.mpi.model.IBaseCode;
import com.sinosoft.mpi.model.MarriageCode;
import com.sinosoft.mpi.model.NationCode;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.ProfessionCode;
import com.sinosoft.mpi.model.SexCode;

/**   
*    
* @Description  转换索引或居民 中的编码为描述
* 
* @Package com.sinosoft.mpi.util 
* @author Bysun
* @version v1.0,2012-5-7
* @see	
* @since	（可选）	
*   
*/ 
public class CodeConvertUtils {
	public final static Map<String,Class<? extends IBaseCode>> fieldMap ;
	static {
		fieldMap = new HashMap<String,Class<? extends IBaseCode>>();
		fieldMap.put("certType", CertCode.class);
		fieldMap.put("eduLevelCode", EduCode.class);
		fieldMap.put("maritalStatusCode", MarriageCode.class);
		fieldMap.put("nationCode", NationCode.class);
		fieldMap.put("jobCode", ProfessionCode.class);
		fieldMap.put("sex", SexCode.class);
	}
	/**
	 * 将居民信息 中的编码转换为编码描述
	 */
	public static void convert(final PersonInfo person){
		convertValue(person);
	}
	
	/**
	 * 将索引信息 中的编码转换为编码描述
	 */
	public static void convert(final PersonIndex index){
		convertValue(index);
	}
	
	private static void convertValue(final Object obj){
		if(obj == null)// 空值直接返回
			return ;
		// 迭代需要转码的字段
		Set<Entry<String, Class<? extends IBaseCode>>> set = fieldMap.entrySet();
		for (Entry<String, Class<? extends IBaseCode>> entry : set) {
			// 取得字段名称
			String key = entry.getKey();
			
			// 取得字段值
			String val;
			try {
				val =(String)PropertyUtils.getProperty(obj, key);
			} catch (Exception e) {
				// 出现异常继续下一个字段
				continue;
			}
			// 字段值为 空或空字串 不处理
			if(StringUtils.isNotBlank(val)){
				// 取得编码对应名称
				String codeName = CacheManager.getCodeName(entry.getValue(), val);
				// 对应名称为空字串 不处理
				if(StringUtils.isNotBlank(codeName)){
					// 设置名称
					try {
						PropertyUtils.setProperty(obj, key, codeName);
					} catch (Exception e) {
						// 出现异常继续下一个字段
						continue;
					}
				}
			}
		}
	}
	/*
	 * 处理日期 
	 * @auther lpk
	 * @date 2012年11月28日
	 * @list
	 * */
	public static void converDateByList(final List<Map<String, Object>> list) {
		// 转换编码数据
		for(Map<String, Object> map : list){
			for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if (map.get(key)!=null&&map.get(key) instanceof Date) {
						if (map.get(key) != null) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
							String result = sdf.format((Date) map.get(key));
							//map.remove(key);
							map.put(key, result);
						} 
					}
			}
		}
	}
	/*
	 * 处理日期 
	 * @auther lpk
	 * @date 2012年11月28日
	 * @map
	 * */
	public static void converDateByMap(final Map<String, Object> map) {
		// 转换日期时间
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if (map.get(key)!=null&&map.get(key) instanceof Date) {
						if (map.get(key) != null) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
							String result = sdf.format((Date) map.get(key));
							//map.remove(key);
							map.put(key, result);
						} 
					}
			}
	}
	
	/**
	 * @auther chenzhongzheng
	 * @Input parameters:编码过的中文参数
	 * @Return parameters:解码
	 * @Method Description: 解码，解决了jquery的ajax中文参数在GBK编码状况下的乱码情况，
	 * 前台的jquery传入中文参数时需要经过encodeURI()进行编码，后台通过此方法进行解码
	 */
	public static String decodeURI2UTF8(String srcString) {
		String targetStr = "";
		try {
			targetStr = URLDecoder.decode(srcString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("解码失败！中文！");
		}
		return targetStr;
	}
	
}