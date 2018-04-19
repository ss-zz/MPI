package com.sinosoft.match.model;

import java.util.Set;

import com.sinosoft.mpi.util.DateUtil;




/**
 * 
*    
* @Description  比较的单元    
* 
* 
*
* 
* @Package com.sinosoft.match.model 
* @author <a href="mailto:qinshouxin@sinosoft.com.cn">Qin Shouxin </a> 
* @version v1.0,2012-3-20
* @see	
* @since	（可选）	
*
 */
public class Record<T> {
	private String recordId;
	private T object;
	
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	private ConvertingWrapDynaBean dynaBean;
	private RecordTypeDef recordTypeDefinition;
	
	public Record(T object) {
		this.object = object;
		this.dynaBean = new ConvertingWrapDynaBean(object); 
	}
	
	public synchronized RecordTypeDef getRecordDef() {
		if (recordTypeDefinition == null) {
			recordTypeDefinition = new RecordTypeDef(object);
		}
		return recordTypeDefinition;
	}

	public String getAsString(String fieldName) {
		Object obj = dynaBean.get(fieldName);
		if (obj == null) {
			return "";
		}
		if (obj instanceof java.util.Date) {
			//return "to_date('"+DateUtil.getDate((java.util.Date) obj)+"','yyyy-mm-dd')";
			return DateUtil.getDate((java.util.Date) obj);
		}
		return obj.toString();
	}

	public Object get(String fieldName) {
		return dynaBean.get(fieldName);
	}
	
	public void set(String fieldName, Object value) {
		dynaBean.set(fieldName, value);
	}
	


	
	public Set<String> getPropertyNames() {
		return dynaBean.getPropertyNames();
	}
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	
}
