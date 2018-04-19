package com.sinosoft.config.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ListFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 合并配置
 */
@Configuration
public class MergConfig {

	/**
	 * 过滤字段
	 */
	@Bean("filterColumns")
	public ListFactoryBean filterColumns() {
		ListFactoryBean bean = new ListFactoryBean();
		bean.setTargetListClass(ArrayList.class);
		List<String> list = new ArrayList<String>();
		list.add("DOMAIN_LEVEL");
		list.add("MPI_PK");
		list.add("STATE");
		bean.setSourceList(list);
		return bean;
	}

	/**
	 * 关键字段
	 */
	@Bean("compareColumns")
	public ListFactoryBean compareColumns() {
		ListFactoryBean bean = new ListFactoryBean();
		bean.setTargetListClass(ArrayList.class);
		List<String> list = new ArrayList<String>();
		list.add("ID_NO");
		// list.add("MEDICAL_INSURANCE_NO");
		// updated WHN 20170303
		list.add("CARD_NO");
		bean.setSourceList(list);
		return bean;
	}

}
