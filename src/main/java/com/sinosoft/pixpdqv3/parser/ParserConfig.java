package com.sinosoft.pixpdqv3.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * com.sinosoft.pixpdqv3.parser.ParserConfig
 */
public class ParserConfig {
	private String basepath;
	private List<Map<String, String>> fields;

	public ParserConfig(String basepath) {
		this.basepath = basepath;
		this.fields = new ArrayList<Map<String, String>>();
	}

	public String getBasepath() {
		return basepath;
	}

	public List<Map<String, String>> getFields() {
		return fields;
	}

	public void putField(Map<String, String> field) {
		this.fields.add(field);
	}
}
