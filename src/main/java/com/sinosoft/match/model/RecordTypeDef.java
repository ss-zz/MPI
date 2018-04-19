
package com.sinosoft.match.model;

import java.util.ArrayList;
import java.util.List;

import com.sinosoft.match.util.ConvertUtil;

public class RecordTypeDef
{
	private List<String> fieldNames = new ArrayList<String>();
	
	public RecordTypeDef(Object object) {
		fieldNames = ConvertUtil.extractProperties(object);
	}
	
	public List<String> getFieldNames() {
		return fieldNames;
	}
	
	public void addFieldName(String fieldName) {
		fieldNames.add(fieldName);
	}
	
	public int fieldCount() {
		return fieldNames.size();
	}

}
