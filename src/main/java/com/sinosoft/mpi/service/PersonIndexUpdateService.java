package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.mpi.IdentifierDomainDao;
import com.sinosoft.mpi.dao.mpi.IndexIdentifierRelDao;
import com.sinosoft.mpi.dao.mpi.MpiCombineRecDao;
import com.sinosoft.mpi.dao.mpi.PersonIndexDao;
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
	IndexIdentifierRelDao indexIdentifierRelDao;
	@Resource
	MpiCombineRecDao mpiCombineRecDao;
	@Resource
	MpiCombineLevelService mpiCombineLevelService;
	// 更新策略
	@Value("${index.update.policy}")
	UpdateStrategy policy;
	@Resource
	DomainSrcLevelService domainSrcLevelService;
	@Resource
	JdbcTemplate jdbcTemplate;

	List<Map<String, Object>> orgincollevellist = null;

	/**
	 * 更新索引信息
	 * 
	 * @param person
	 * @param indexId
	 */
	public void updateIndex(PersonInfo person, String indexId) {

		switch (policy) {
		case UNUPDATE:
			// 不更新 无操作
			break;
		case UPDATE:
			updateIndexDirect(person, indexId);
			break;
		case UNUPDATE_MAN:
			break;
		case UPDATE_LEVEL:
			updateIndexByLevel(person, indexId);
			break;
		default:
			break;
		}
	}

	/**
	 * 直接更新 索引信息
	 */
	private void updateIndexDirect(PersonInfo person, String mpiPk) {
		PersonIndex idx = person.toPersonIndex();
		idx.setMpiPk(mpiPk);
		personIndexDao.save(idx);
	}

	/**
	 * 根据数据源级别进行更新
	 *
	 * @param person
	 * @param indexId
	 */
	private void updateIndexByLevel(PersonInfo person, String indexId) {
		// 取得 索引
		PersonIndex index = personIndexDao.findOne(indexId);
		// 取得居民数据级别
		IdentifierDomain domain = identifierDomainDao.getFirstByPersonId(person.getFieldPk());
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
			index = person.toPersonIndex();
			index.setMpiPk(indexId);
			index.setDomainLevel(domain.getDomainLevel());
			personIndexDao.save(index);
		}
	}

	// 合并索引
	private PersonIndex mergeIndex(PersonIndex index, PersonInfo personinfo) {
		PersonIndex newIndex = new PersonIndex();
		// 添加索引
		if (index.getMpiPk() != null) {
			// 获取索引字段的级别权限,与当前居民信息级别做比较,判别应替换字段信息，进行字段级别合并
			// 合并信息入库（第一次入库也记录一次合并信息）
			// 查询最新一次的合并记录
			IndexIdentifierRel iirlatest = indexIdentifierRelDao
					.findFirstByMpiPkOrderByCombineRecDesc(index.getMpiPk());
			IndexIdentifierRel iir = new IndexIdentifierRel();
			iir.setCombineRec(iirlatest.getCombineNo());// 指代上一条的替换记录combine_rec
			iir.setDomainId(personinfo.getDomainId());
			iir.setFieldPk(personinfo.getFieldPk());
			iir.setDomainId(iirlatest.getDomainId());
			iir.setMpiPk(index.getMpiPk());
			if (personinfo.getType() == "0") {
				iir.setPersonIdentifier(personinfo.getHrId());
			} else if (personinfo.getType() == "1") {
				iir.setPersonIdentifier(personinfo.getMedicalserviceNo());
			} else if (personinfo.getType() == "3") {
				iir.setPersonIdentifier(personinfo.getPatientId());
			}

			indexIdentifierRelDao.save(iir);
			// 合并记录入库
			long comboNO = iir.getCombineNo();// 本次合并记录号
			// 取合并前的字段级别
			/* updated-whn-2016-11-01 */
			List<Map<String, Object>> orgincollevellist = getOrgincollevellist();
			// 合并记录字段级别入库
			newIndex = mpiCombineLevelService.compareBatchAdd(index, personinfo, comboNO, personinfo.getSrcLevel(),
					orgincollevellist, null);
			personIndexDao.save(newIndex);
			MpiCombineRec mpiCombineRec = newIndex.indexToMpiCombineRec();
			mpiCombineRec.setCombineNo(comboNO);
			mpiCombineRecDao.save(mpiCombineRec);
		}
		return newIndex;
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
	 * 更新索引
	 * 
	 * @param personindex
	 * @param person
	 * @return
	 */
	public PersonIndex updateIndex(PersonIndex personindex, PersonInfo person) {

		switch (policy) {
		case UPDATE_LEVEL_NEW:
			personindex = mergeIndex(personindex, person);
			break;
		default:
			break;
		}
		return personindex;
	}

}
