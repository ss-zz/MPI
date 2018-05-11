
package com.sinosoft.match.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.WrapDynaClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 转换工具类
 */
public final class ConvertUtil {
	private static final Log log = LogFactory.getLog(ConvertUtil.class);

	private ConvertUtil() {
	}

	/**
	 * 转换ResourceBundle为Map对象
	 * 
	 * @param rb
	 *            被转换对象
	 * @return map对象
	 */
	public static Map<String, String> convertBundleToMap(ResourceBundle rb) {
		Map<String, String> map = new HashMap<String, String>();

		Enumeration<String> keys = rb.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			map.put(key, rb.getString(key));
		}

		return map;
	}

	/**
	 * Method to convert a ResourceBundle to a Properties object.
	 * 
	 * @param rb
	 *            a given resource bundle
	 * @return Properties a populated properties object
	 */
	public static Properties convertBundleToProperties(ResourceBundle rb) {
		Properties props = new Properties();

		for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
			String key = keys.nextElement();
			props.put(key, rb.getString(key));
		}

		return props;
	}

	/**
	 * Convenience method used by tests to populate an object from a ResourceBundle
	 * 
	 * @param obj
	 *            an initialized object
	 * @param rb
	 *            a resource bundle
	 * @return a populated object
	 */
	public static Object populateObject(Object obj, ResourceBundle rb) {
		try {
			Map<String, String> map = convertBundleToMap(rb);
			BeanUtils.copyProperties(obj, map);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred populating object: " + e.getMessage());
		}

		return obj;
	}

	public static List<String> extractProperties(Object obj) {
		List<String> properties = new ArrayList<String>();
		Map<String, String> visitMap = new HashMap<String, String>();
		visitMap.put(obj.getClass().getName(), obj.getClass().getName());
		WrapDynaClass topClass = WrapDynaClass.createDynaClass(obj.getClass());
		extractClassProperties(topClass, visitMap, properties, "");
		return properties;
	}

	private static void extractClassProperties(WrapDynaClass theClass, Map<String, String> visitMap,
			List<String> properties, String parent) {
		if (theClass == null) {
			return;
		}
		for (DynaProperty property : theClass.getDynaProperties()) {
			boolean visitedAlready = (visitMap.get(property.getType().getName()) != null);
			log.debug("Checking to see if type " + property.getType().getName() + " has been visited already returns "
					+ visitedAlready);
			if (!property.getType().getName().startsWith("java") && !visitedAlready) {
				WrapDynaClass dynaClass = WrapDynaClass.createDynaClass(property.getType());
				extractClassProperties(dynaClass, visitMap, properties, parent + property.getName() + ".");
			} else {
				if (!property.getType().getName().equalsIgnoreCase("java.lang.Class")) {
					log.debug("Adding type " + property.getType().getName() + " to the list of types visited already.");
					visitMap.put(property.getType().getName(), property.getType().getName());
					properties.add(parent + property.getName());
				}
			}
		}
	}

	public static String getModifiedFieldName(String fieldName, String prefix) {
		StringBuilder modifiedName = new StringBuilder(fieldName);
		modifiedName.setCharAt(0, Character.toUpperCase(modifiedName.charAt(0)));
		modifiedName.insert(0, prefix);
		return modifiedName.toString();
	}

	public static String getSetMethodName(String fieldName) {
		return getModifiedFieldName(fieldName, "set");
	}

	public static String getSerializedFieldName(String fieldName) {
		return getModifiedFieldName(fieldName, "serialized");
	}

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	public static void serializeObject(String configDirectory, String fileName, Object o) {
		String fullFilename = configDirectory + "/" + fileName;
		log.debug("Attempting to serialize object into file: " + fullFilename);
		try {
			ObjectOutputStream ois = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(fullFilename)));
			ois.writeObject(o);
			ois.flush();
			ois.close();
		} catch (Exception e) {
			log.error("Failed while serializing object (into the file" + fullFilename + " ): " + e.getMessage(), e);
			throw new RuntimeException(
					"Failed while serializing object (into the file" + fullFilename + " ): " + e.getMessage());
		}
	}

	public static Object deserializeObject(String configDirectory, String fileName) {
		Object obj;
		String fullFilename = configDirectory + "/" + fileName;
		log.debug("Attempting to deserialize object from file: " + fullFilename);
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fullFilename)));
			obj = ois.readObject();
			ois.close();
		} catch (Exception e) {
			log.error("Failed while deserializing object (from file " + fullFilename + "): " + e.getMessage(), e);
			throw new RuntimeException(
					"Failed while deserializing object (from file " + fullFilename + "): " + e.getMessage());
		}
		return obj;
	}

}
