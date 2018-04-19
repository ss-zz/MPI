package com.sinosoft.pixpdqv3.parser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * com.sinosoft.pixpdqv3.parser.ParserConfigReader
 */
public class ParserConfigReader {
	private final static Map<String, ParserConfig> cfg = new TreeMap<String, ParserConfig>();

	public static ParserConfig read(String file) {
		synchronized (cfg) {
			ParserConfig result = cfg.get(file);
			if (result == null) {
				result = parse(file);
				cfg.put(file, result);
			}
			return result;
		}
	}

	private static ParserConfig parse(String file) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(ParserConfigReader.class.getClassLoader().getResourceAsStream("hl7v3/cfg/" + file));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		ParserConfig result = new ParserConfig(root.attributeValue("basepath"));
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		for (Element element : elements) {
			Map<String, String> field = new TreeMap<String, String>();
			field.put("FIELD", element.attributeValue("attribute"));
			field.put("PATH", result.getBasepath() + element.attributeValue("attributePath"));
			field.put("TYPE", element.attributeValue("class"));
			result.putField(field);
		}
		return result;
	}
}
