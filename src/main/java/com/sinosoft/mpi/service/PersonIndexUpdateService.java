package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.mpi.IdentifierDomainDao;
import com.sinosoft.mpi.dao.mpi.MpiCombineRecDao;
import com.sinosoft.mpi.dao.mpi.PersonIndexDao;
import com.sinosoft.mpi.dics.LogOpStyle;
import com.sinosoft.mpi.dics.LogOpType;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.MpiCombineRec;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.UpdateStrategy;
import com.sinosoft.mpi.util.NumberUtils;

/**
 * 人员主索引更新服务
 */
@Service
public class PersonIndexUpdateService {

	@Resource
	PersonIndexDao personIndexDao;
	@Resource
	IdentifierDomainDao identifierDomainDao;
	@Resource
	IndexIdentifierRelService indexIdentifierRelService;
	@Resource
	MpiCombineRecDao mpiCombineRecDao;
	@Resource
	MpiCombineLevelService mpiCombineLevelService;
	@Resource
	DomainSrcLevelService domainSrcLevelService;
	@Resource
	JdbcTemplate jdbcTemplate;

	// 更新策略
	@Value("${index.update.policy}")
	UpdateStrategy policy;

	// 缓存数据
	List<Map<String, Object>> orgincollevellist = null;

	/**
	 * 更新索引
	 * 
	 * @param person
	 * @param mpiPk
	 */
	public void updateIndex(PersonInfo person, String mpiPk) {
		// 直接更新
		// updateIndexDirect(person, mpiPk);
		// 根据级别更新
		updateIndexByLevel(person, mpiPk);
	}

	/**
	 * 更新索引-直接
	 */
	private void updateIndexDirect(PersonInfo personInfo, String mpiPk) {
		PersonIndex personIndex = personInfo.toPersonIndex();
		personIndex.setMpiPk(mpiPk);
		personIndexDao.save(personIndex);
	}

	/**
	 * 更新索引-根据数据源级别
	 */
	private void updateIndexByLevel(PersonInfo personInfo, String mpiPk) {
		// 取得 索引
		PersonIndex index = personIndexDao.findOne(mpiPk);
		// 取得居民数据级别
		IdentifierDomain domain = identifierDomainDao.getFirstByPersonId(personInfo.getFieldPk());
		int srcLevel = 0;
		if (NumberUtils.isNumber(index.getDomainLevel())) {
			srcLevel = Integer.parseInt(index.getDomainLevel());
		}
		int domainLevel = 0;
		if (NumberUtils.isNumber(domain.getDomainLevel())) {
			domainLevel = Integer.parseInt(domain.getDomainLevel());
		}
		// 对比级别
		if (srcLevel <= domainLevel) {
			index = personInfo.toPersonIndex();
			index.setMpiPk(mpiPk);
			index.setDomainLevel(domain.getDomainLevel());
			personIndexDao.save(index);
		}
	}

	/**
	 * 获取 合记录字段级别-对象缓存-20171020-lizy
	 *
	 * @return
	 */
	private List<Map<String, Object>> getOrgincollevellist() {
		if (this.orgincollevellist == null) {
			this.orgincollevellist = jdbcTemplate.queryForList("select * from MPI_COMBINE_LEVEL");
		}
		return this.orgincollevellist;
	}

	/**
	 * 更新索引-将人员合并到目标索引上<br/>
	 * 获取索引字段的级别权限,与当前居民信息级别做比较,判别应替换字段信息，进行字段级别合并
	 * 
	 * @param personindex
	 * @param person
	 * @return
	 */
	public PersonIndex updateIndex(PersonIndex personIndex, PersonInfo personInfo, LogOpType opType, LogOpStyle opStyle,
			String info) {
		String mpiPk = personIndex.getMpiPk();
		if (mpiPk != null) {
			// 最新一次的合并关联关系
			IndexIdentifierRel iirlatest = indexIdentifierRelService.findFirstByMpiPkOrderByCombineRecDesc(mpiPk);

			// 保存新的关联关系
			IndexIdentifierRel iir = indexIdentifierRelService.add(personInfo.getFieldPk(), personIndex.getMpiPk(),
					iirlatest.getDomainId(), personInfo.getIdentifier(), opType, opStyle, info,
					iirlatest.getCombineNo());

			// 合并记录入库
			long comboNO = iir.getCombineNo();// 本次合并记录号
			// 取合并前的字段级别
			List<Map<String, Object>> orgincollevellist = getOrgincollevellist();
			// 合并记录字段级别入库
			mpiCombineLevelService.compareBatchAdd(personIndex, personInfo, comboNO, personInfo.getSrcLevel(),
					orgincollevellist, null);

			// 更新主索引信息
			personIndexDao.save(personIndex);

			// 保存合并记录
			MpiCombineRec mpiCombineRec = personIndex.indexToMpiCombineRec();
			mpiCombineRec.setCombineNo(comboNO);
			mpiCombineRecDao.save(mpiCombineRec);

		}
		return personIndex;
	}

}
