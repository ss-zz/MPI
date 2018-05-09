package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IdentifierDomainDao;
import com.sinosoft.mpi.dao.IndexIdentifierRelDao;
import com.sinosoft.mpi.dao.MpiCombineLevelDao;
import com.sinosoft.mpi.dao.MpiCombineRecDao;
import com.sinosoft.mpi.dao.PersonIndexDao;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.MpiCombineRec;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.notification.event.UpdateStrategy;
import com.sinosoft.mpi.util.NumberUtils;

@Service
public class PersonIndexUpdateService {
	private static Logger logger = Logger.getLogger(PersonIndexUpdateService.class);

	@Resource
	private PersonIndexDao personIndexDao;
	@Resource
	private IdentifierDomainDao identifierDomainDao;
	@Resource
	IndexIdentifierRelDao indexIdentifierRelDao;
	@Resource
	MpiCombineRecDao mpiCombineRecDao;
	@Resource
	MpiCombineLevelDao mpiCombineLevelDao;
	@Value("${index.update.policy}")
	private UpdateStrategy policy;

	@Resource
	private DomainSrcLevelService domainSrcLevelService;

	private List<Map<String, Object>> orgincollevellist = null;

	public void updateIndex(PersonInfo person, String indexId) {

		switch (policy) {
		case UNUPDATE:
			// 不更新 无操作
			break;
		case UPDATE:
			updateIndexDirect(person, indexId);
			break;
		case UNUPDATE_MAN:
			// TODO 人工更新 暂无实现
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
	private void updateIndexDirect(PersonInfo person, String mpi_pk) {
		PersonIndex idx = person.personInfoToPersonIndex();
		idx.setMPI_PK(mpi_pk);
		personIndexDao.update(idx);
		logger.debug("执行更新索引信息:PersonInfo[" + person + "],mpi_pk[" + mpi_pk + "]");
	}

	/**
	 * 根据数据源级别进行更新
	 *
	 * @param person
	 * @param indexId
	 */
	private void updateIndexByLevel(PersonInfo person, String indexId) {
		// 取得 索引
		PersonIndex index = new PersonIndex();
		index.setMPI_PK(indexId);
		index = personIndexDao.findById(index);
		// 取得居民数据级别
		IdentifierDomain domain = identifierDomainDao.getByPersonId(person.getFIELD_PK());
		int srcLevel = 0;
		if (NumberUtils.isNumber(index.getDOMAIN_LEVEL())) {
			srcLevel = Integer.parseInt(index.getDOMAIN_LEVEL());
		}
		int domainLevel = 0;
		if (NumberUtils.isNumber(domain.getDOMAIN_LEVEL())) {
			domainLevel = Integer.parseInt(domain.getDOMAIN_LEVEL());
		}
		// 对比级别
		if (srcLevel <= domainLevel) {
			index = person.personInfoToPersonIndex();
			index.setMPI_PK(indexId);
			index.setDOMAIN_LEVEL(domain.getDOMAIN_LEVEL());
			personIndexDao.update(index);
		}
	}

	// 合并索引
	private PersonIndex mergeIndex(PersonIndex index, PersonInfo personinfo) {
		PersonIndex newIndex = new PersonIndex();
		// 添加索引
		if (index.getMPI_PK() != null) {
			// 获取索引字段的级别权限,与当前居民信息级别做比较,判别应替换字段信息，进行字段级别合并
			// 合并信息入库（第一次入库也记录一次合并信息）
			// 查询最新一次的合并记录
			IndexIdentifierRel iirlatest = indexIdentifierRelDao.findByMpiPKByLatest(index.getMPI_PK());
			IndexIdentifierRel iir = new IndexIdentifierRel();
			iir.setCOMBINE_REC(iirlatest.getCOMBINE_NO());// 指代上一条的替换记录combine_rec
			iir.setDOMAIN_ID(personinfo.getDOMAIN_ID());
			iir.setFIELD_PK(personinfo.getFIELD_PK());
			iir.setDOMAIN_ID(iirlatest.getDOMAIN_ID());
			iir.setMPI_PK(index.getMPI_PK());
			if (personinfo.getTYPE() == "0") {
				iir.setPERSON_IDENTIFIER(personinfo.getHR_ID());
			} else if (personinfo.getTYPE() == "1") {
				iir.setPERSON_IDENTIFIER(personinfo.getMEDICALSERVICE_NO());
			} else if (personinfo.getTYPE() == "3") {
				iir.setPERSON_IDENTIFIER(personinfo.getPATIENT_ID());
			}

			indexIdentifierRelDao.add(iir);
			// 合并记录入库
			long comboNO = iir.getCOMBINE_NO();// 本次合并记录号
			// 取合并前的字段级别
			/* updated-whn-2016-11-01 */
			List<Map<String, Object>> orgincollevellist = getOrgincollevellist();
			// 合并记录字段级别入库
			newIndex = mpiCombineLevelDao.compareBatchAdd(index, personinfo, comboNO, personinfo.getSRC_LEVEL(),
					orgincollevellist, null);
			personIndexDao.update(newIndex);
			MpiCombineRec mpiCombineRec = newIndex.indexToMpiCombineRec();
			mpiCombineRec.setCOMBINE_NO(comboNO);
			mpiCombineRecDao.add(mpiCombineRec);
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
			this.orgincollevellist = mpiCombineLevelDao.findForMap("select * from MPI_COMBINE_LEVEL");
		}
		return this.orgincollevellist;
	}

	public void setPolicy(UpdateStrategy policy) {
		this.policy = policy;
	}

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
