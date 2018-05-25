package com.sinosoft.mpi.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;

import com.google.common.base.CaseFormat;
import com.sinosoft.mpi.dao.mpi.MpiCombineLevelDao;
import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.model.MpiCombineLevel;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**
 * 人员合并级别服务
 *
 */
@Service
public class MpiCombineLevelService {

	@Resource
	MpiCombineLevelDao mpiCombineLevelDao;

	/**
	 * 新数据来源字段级别
	 * 
	 * @param personindex
	 * @param combono
	 * @param domainLevel
	 * @param srcLevelcolmap
	 */
	public void batchAddLevel(PersonIndex personindex, Long combono, Short domainLevel,
			List<DomainSrcLevel> srcLevelcolmap) {
		if (srcLevelcolmap != null) {
			try {
				Map<String, String> map = BeanUtils.describe(personindex);
				Iterator<String> it = map.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					// 域数据源级别字段
					if (srcLevelcolmap != null && srcLevelcolmap.size() > 0) {
						for (int i = 0; i < srcLevelcolmap.size(); i++) {
							DomainSrcLevel fieldlevelMap = srcLevelcolmap.get(i);
							String field_name = fieldlevelMap.getFieldName();
							if (key.equals(field_name)) {
								break;
							}
						}
					}
				}
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 合并字段级别信息
	 * 
	 * @param personindex
	 * @param personinfo
	 * @param combono
	 * @param domainLevel
	 * @param orgincolLevellist
	 * @param srcLevelcollist
	 * @return
	 */
	public PersonIndex compareBatchAdd(PersonIndex personindex, PersonInfo personinfo, Long combono, Short domainLevel,
			List<Map<String, Object>> orgincolLevellist, List<Map<String, Object>> srcLevelcollist) {
		Iterator<Map<String, Object>> it = orgincolLevellist.iterator();
		while (it.hasNext()) {
			Map<String, Object> map = it.next();
			Short level = ((BigDecimal) map.get("SRC_LEVEL")).shortValue();
			String combofield = (String) map.get("COMBINE_FIELD");
			
			// 数据库中combofield为大写，转为驼峰小写
			if (combofield != null && Pattern.matches("[A-Z_]+", combofield)) {
				combofield = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, combofield.toLowerCase());
			}
			
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

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<MpiCombineLevel> findAll() {
		return mpiCombineLevelDao.findAll();
	}

}
