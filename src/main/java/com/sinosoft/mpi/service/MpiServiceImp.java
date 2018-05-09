package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IdentifierDomainDao;
import com.sinosoft.mpi.dao.IndexIdentifierRelDao;
import com.sinosoft.mpi.dao.PersonIdxLogDao;
import com.sinosoft.mpi.dao.PersonIndexDao;
import com.sinosoft.mpi.dao.PersonInfoDao;
import com.sinosoft.mpi.exception.NotificationExcpetion;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**
 * MPI注册服务类
 */
@Service
public class MpiServiceImp {

	@Resource
	private PersonIndexDao personIndexDao;
	@Resource
	private PersonInfoDao personInfoDao;
	@Resource
	private PersonIdxLogDao personIdxLogDao;
	@Resource
	private IdentifierDomainDao identifierDomainDao;
	@Resource
	private IndexIdentifierRelDao indexIdentifierRelDao;

	private Logger logger = Logger.getLogger(MpiServiceImp.class);

	public void save(PersonInfo t) {

	}

	public void handleMpi(PersonInfo personinfo) {
		// 校验系统中是否有对应注册域
		IdentifierDomain domain = identifierDomainDao.getByUniqueSign(personinfo.getUNIQUE_SIGN());
		if (domain == null) {
			logger.debug("无法找到域,没有找到域标识为:" + personinfo.getUNIQUE_SIGN() + "的身份域.");
			throw new ValidationException("无法找到域,没有找到域标识为:" + personinfo.getUNIQUE_SIGN() + "的身份域.");
		} else {

			// 判断该居民信息state状态 0 新增 1 修改 2作废
			if (personinfo.getSTATE().equals(0)) {
				regMpi(personinfo);
			}
			// 修改居民信息
			else if (personinfo.getSTATE().equals(1)) {

			}
			// 修改居民信息标识,并拆分主索引信息
			else if (personinfo.getSTATE().equals(2)) {

			}

		}

	}

	public void regMpi(PersonInfo personinfo) {
		// 校验该居民信息是否已注册
		PersonInfo p = getByPersonIdentifier(personinfo);
		if (p != null) {
			logger.debug(
					"居民信息已注册,域标识为:" + personinfo.getUNIQUE_SIGN() + ",该域主键为:" + personinfo.getFIELD_PK() + "的用户已注册.");
			// 如果居民信息表中有相同记录，新增不入库
			throw new NotificationExcpetion("居民信息已注册");
		} else {
			// 保存居民信息
			personInfoDao.add(personinfo);
			logger.debug("Add PersonInfo:" + personinfo);
		}
	}

	public void MergeMpi(PersonInfo personinfo, PersonIndex personindex) {

	}

	public void MergeMpi(PersonInfo personinfo) {

	}

	private PersonInfo getByPersonIdentifier(PersonInfo personinfo) {
		String sql = "select * from mpi_person_info t where exists"
				+ "( select 1 from MPI_INDEX_IDENTIFIER_REL r where t.field_pk=r.field_pk and field_pk = ? and DOMAIN_ID = ? )";
		List<PersonInfo> list = personInfoDao.find(sql,
				new Object[] { personinfo.getFIELD_PK(), personinfo.getDOMAIN_ID() });
		PersonInfo result = null;
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

}
