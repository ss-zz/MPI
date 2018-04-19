
package com.sinosoft.match.model;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConvertingWrapDynaBean extends org.apache.commons.beanutils.ConvertingWrapDynaBean {
	private static final long serialVersionUID = -370897935825063946L;

	protected final Log log = LogFactory.getLog(getClass());

	public ConvertingWrapDynaBean(Object instance) {
		super(instance);
	}

	public Object get(String name) {
		Object value = null;
		try {
			value = PropertyUtils.getNestedProperty(instance, name);
		} catch (InvocationTargetException ite) {
			Throwable cause = ite.getTargetException();
			throw new IllegalArgumentException("Error reading property '" + name + "' nested exception - " + cause);
		} catch (Throwable t) {
			throw new IllegalArgumentException("Error reading property '" + name + "', exception - " + t);
		}
		return (value);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<String> getPropertyNames() {
		Set<String> properties = new HashSet<String>();
		try {
			Map map = BeanUtils.describe(getInstance());
			for (Iterator<String> iter = (Iterator<String>) map.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				if (!name.equals("class")) {
					properties.add(name);
				}
			}
		} catch (Exception e) {
			log.warn("Failed while extracting bean properties: " + e, e);
		}
		return properties;
	}
}
