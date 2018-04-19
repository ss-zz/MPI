package com.sinosoft.mpi.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class AppliationContextUtils implements ApplicationContextAware {
	private static ApplicationContext context;//声明一个静态变量保存  
	@Override 
	public void setApplicationContext(ApplicationContext contex)  
	  throws BeansException {  
		this.context=contex;  
	}  
	public static ApplicationContext getContext(){  
		return context;  
	}  
}
