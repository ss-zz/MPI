package com.sinosoft.mpi.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.sinosoft.mpi.context.QueryConditionType;

/**   
*    
* @Description  查询语句拼接常用方法
* 
* 
*
* 
* @Package com.sinosoft.mpi.util 
* @author Bysun
* @version v1.0,2012-4-13
* @see	
* @since	（可选）	
*   
*/ 
public class SqlUtils {
	
	/**
	 * 添加查询条件
	 * @param value 查询参数值
	 * @param field 字段名
	 * @param sql 源sql语句
	 * @param args 查询参数
	 * @param type 比较类型
	 */
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	public static void appendCondition(Object value, String field, final StringBuilder sql,
			final List<Object> args, QueryConditionType type) {
		if (value == null || (value instanceof String && StringUtils.isBlank((String)value)))
			return;

        String val = null;
        if(value instanceof Date){
            val = "TO_DATE('"+sdf.format((Date)value)+"','yyyymmdd hh24:mi:ss')";
        }else{
            val = (String) value;
        }

		
		sql.append(" and ").append(field).append(type.getType());
		switch (type) {
		case LIKE:
			args.add(buildLikeStr(val));
			break;
		default:
			args.add(val);
			break;
		}
	}
	
	/**
	 * 生成like需要的字串
	 * @param arg
	 * @return
	 */
	public static String buildLikeStr(String arg) {
		return "%" + StringUtils.trim(arg) + "%";
	}
}
