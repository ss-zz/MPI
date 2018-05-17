package com.sinosoft.mpi.cache;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.sinosoft.mpi.annotation.PropertyDesc;
import com.sinosoft.mpi.dao.IBaseDao;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.biz.MpiBizBPropertiesDesc;
import com.sinosoft.mpi.model.biz.MpiBizIndex;
import com.sinosoft.mpi.model.code.IBaseCode;
import com.sinosoft.mpi.model.code.PerInfoPropertiesDesc;
import com.sinosoft.mpi.model.code.PersonPropertiesDesc;
import com.sinosoft.mpi.util.AppliationContextUtils;

/**
 * 缓存初始化
 */
public class CacheGenerator implements InitializingBean {

	private Logger logger = Logger.getLogger(CacheGenerator.class);
	private String[] clzNames;

	@SuppressWarnings("rawtypes")
	@Override
	public void afterPropertiesSet() throws Exception {
		for (String clzName : clzNames) {
			Class clz = Class.forName(clzName);
			List<IBaseCode> baseCodes = getdatas(clz);
			buildCache(clz, baseCodes);
		}

		// 初始化PersonIndex表所有属性对应中文名到缓存
		buildCache(PersonPropertiesDesc.class, buildPropertyDesc());
		buildCache(PerInfoPropertiesDesc.class, buildPerInfoPropertyDesc());
		buildCache(MpiBizBPropertiesDesc.class, buildMpiBizPropertyDesc());
	}

	/**
	 * 获得指定类数据库所有数据
	 * 
	 * @param clz
	 *            需缓存的类
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<IBaseCode> getdatas(Class clz) {
		StringBuilder beanName = new StringBuilder();
		beanName.append(clz.getSimpleName().substring(0, 1).toLowerCase()).append(clz.getSimpleName().substring(1))
				.append("Dao");
		IBaseDao dao = (IBaseDao) AppliationContextUtils.getContext().getBean(beanName.toString());
		if (dao == null) {
			logger.error("未找到名为" + beanName.toString() + "的类实例");
			throw new NoSuchBeanDefinitionException("未找到名为" + beanName.toString() + "的类实例");
		}
		return dao.findAll();
	}

	/**
	 * 构建缓存
	 * 
	 * @param clz
	 *            所缓存的类
	 * @param baseCodes
	 *            所要缓存的数据
	 */
	@SuppressWarnings("rawtypes")
	private void buildCache(Class clz, List<IBaseCode> baseCodes) {
		CacheManager.putAll(clz, baseCodes);
	}

	private List<IBaseCode> buildPropertyDesc() {
		Class<PersonIndex> clz = PersonIndex.class;
		Field[] fields = clz.getDeclaredFields();
		List<IBaseCode> codes = new ArrayList<IBaseCode>(fields.length);
		for (Field field : fields) {
			if (field.isAnnotationPresent(PropertyDesc.class)) {
				PropertyDesc ann = field.getAnnotation(PropertyDesc.class);
				String name = field.getName();
				String desc = ann.name();
				String column = ann.column();
				PersonPropertiesDesc ppd = new PersonPropertiesDesc(name, desc, column);
				codes.add(ppd);
			}
		}
		return codes;
	}

	/**
	 * 缓存personinfo对象
	 */
	private List<IBaseCode> buildPerInfoPropertyDesc() {
		Class<PersonInfo> clz = PersonInfo.class;
		Field[] fields = clz.getDeclaredFields();
		List<IBaseCode> codes = new ArrayList<IBaseCode>(fields.length);
		for (Field field : fields) {
			if (field.isAnnotationPresent(PropertyDesc.class)) {
				PropertyDesc ann = field.getAnnotation(PropertyDesc.class);
				String name = field.getName();
				String desc = ann.name();
				String column = ann.column();
				PerInfoPropertiesDesc ppd = new PerInfoPropertiesDesc(name, desc, column);
				codes.add(ppd);
			}
		}
		return codes;
	}

	/**
	 * 缓存主索引业务对象
	 * 
	 * @return
	 */
	private List<IBaseCode> buildMpiBizPropertyDesc() {
		Class<MpiBizIndex> clz = MpiBizIndex.class;
		Field[] fields = clz.getDeclaredFields();
		List<IBaseCode> codes = new ArrayList<IBaseCode>(fields.length);
		for (Field field : fields) {
			if (field.isAnnotationPresent(PropertyDesc.class)) {
				PropertyDesc ann = field.getAnnotation(PropertyDesc.class);
				String name = field.getName();
				String desc = ann.name();
				String column = ann.column();
				PerInfoPropertiesDesc ppd = new PerInfoPropertiesDesc(name, desc, column);
				codes.add(ppd);
			}
		}
		return codes;
	}

	public String[] getClzNames() {
		return clzNames;
	}

	public void setClzNames(String[] clzNames) {
		this.clzNames = clzNames;
	}
}
