package com.sinosoft.mpi.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


/**
 * 自定义配置文件加载类
 * 
 * @author  wmc
 * @date 2015-12-17
 */
public class EncryptablePropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		try {
			String password = props.getProperty("jdbc.password");
			if (password != null) {
				props.setProperty("jdbc.password", DesEncrypter.Decrypt(password));
			}
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}

}
