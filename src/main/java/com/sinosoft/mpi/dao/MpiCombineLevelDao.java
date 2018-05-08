package com.sinosoft.mpi.dao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.MpiCombineLevel;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

@Repository
public class MpiCombineLevelDao extends IBaseDao<MpiCombineLevel> {

	@Resource
	List<String> filterColumns;
	@Resource
	List<String> compareColumns;

	@Override
	public void add(MpiCombineLevel entity) {

	}

	@Override
	public void deleteById(MpiCombineLevel entity) {

	}

	@Override
	public void update(MpiCombineLevel entity) {

	}

	@Override
	public MpiCombineLevel findById(MpiCombineLevel entity) {
		return null;
	}

	@Override
	public List<MpiCombineLevel> find(String sql) {
		return null;
	}

	@Override
	public List<MpiCombineLevel> find(String sql, Object[] args) {
		return null;
	}

	@Override
	public List<MpiCombineLevel> findAll() {
		return null;
	}

	/**
	 *
	 * 新数据来源字段级别，如果新数据来源查不到新字段级别,则应用数据来源级别比较
	 *
	 * @param personindex
	 * @param combono
	 * @param domainLevel
	 * @param srcLevelcolmap
	 * @see com.sinosoft.mpi.dao.IMpiCombineLevelDao#batchAddLevel(com.sinosoft.mpi.model.PersonIndex,
	 *      java.lang.Long, java.lang.Short, java.util.List)
	 */
	public void batchAddLevel(PersonIndex personindex, Long combono, Short domainLevel,
			List<Map<String, Object>> srcLevelcolmap) {

		try {
			if (srcLevelcolmap != null) {
				// 个人信息map lpk update
				@SuppressWarnings("unchecked")
				Map<String, Object> map = BeanUtils.describe(personindex);
				Iterator<String> it = map.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					// 域数据源级别字段
					if (srcLevelcolmap != null && srcLevelcolmap.size() > 0) {
						for (int i = 0; i < srcLevelcolmap.size(); i++) {
							Map<String, Object> fieldlevelMap = srcLevelcolmap.get(i);
							String field_name = (String) fieldlevelMap.get("FIELD_NAME");
							if (key.equals(field_name)) {
								break;
							}
						}
					}
				}
			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 合并字段级别信息 旧字段合并级别,新数据来源字段级别，如果新数据来源查不到新字段级别,则应用数据来源级别比较
	 */
	public PersonIndex compareBatchAdd(PersonIndex personindex, PersonInfo personinfo, Long combono, Short domainLevel,
			List<Map<String, Object>> orgincolLevellist, List<Map<String, Object>> srcLevelcollist) {
		Iterator<Map<String, Object>> it = orgincolLevellist.iterator();
		while (it.hasNext()) {
			Map<String, Object> map = it.next();
			Short level = ((BigDecimal) map.get("SRC_LEVEL")).shortValue();
			String combofield = (String) map.get("COMBINE_FIELD");
			// 2016年11月1日16:47:45 WHN update 所有字段进行合并，非空字段不进行替换
			if (srcLevelcollist == null) {
				// 如果索引字段优先级小于当前信息字段优先级，信息合并
				try {
					Object replaceval = PropertyUtils.getProperty(personinfo, combofield);
					if (replaceval != null && !"".equals(replaceval)) {
						PropertyUtils.setProperty(personindex, combofield, replaceval);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// lpk update 2013年4月27日15:38:46
				// 遍历域数据源级别字段配置
				Iterator<Map<String, Object>> srcit = srcLevelcollist.iterator();
				while (srcit.hasNext()) {
					// 最后一次合并级别
					Map<String, Object> srccolmap = srcit.next();
					String srckeyname = (String) srccolmap.get("FIELD_NAME");
					if (srckeyname.equals(combofield)) {
						Short srcLevel = new Short((String) srccolmap.get("FIELD_LEVEL"));
						if (level < srcLevel) {
							try {
								Object replaceval = PropertyUtils.getProperty(personinfo, combofield);
								if (replaceval != null && !"".equals(replaceval)) {
									PropertyUtils.setProperty(personindex, combofield, replaceval);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return personindex;
	}

}
