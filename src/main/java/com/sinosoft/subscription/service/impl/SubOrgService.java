package com.sinosoft.subscription.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.subscription.dao.ISubOrgDao;
import com.sinosoft.subscription.model.SubscribeOrg;
import com.sinosoft.subscription.service.ISubOrgService;

@Service("subOrgService")
public class SubOrgService implements ISubOrgService {
	
	private Logger logger = Logger.getLogger(SubOrgService.class);
	
	@Resource
	private ISubOrgDao subOrgDao;

	@Override
	public void save(SubscribeOrg t) {
		// 保存
		subOrgDao.add(t);
		logger.debug("Add SubscribeOrg:"+t);
	}

	@Override
	public void update(SubscribeOrg t) {
		subOrgDao.update(t);
		logger.debug("Update SubscribeOrg:"+t);
	}

	@Override
	public void delete(SubscribeOrg t) {
		subOrgDao.deleteById(t);
		logger.debug("Del SubscribeOrg:configId="+t.getId());
	}

	@Override
	public SubscribeOrg getObject(String id) {
		return null;
	}


	@Override
	public List<SubscribeOrg> queryForPage(SubscribeOrg t, PageInfo page) {
		String orgname = t.getSbOrgname();
		StringBuffer sb = new StringBuffer();
		 sb.append(" SELECT	Z.DD_ID ZONECODE, 	"); 
		 sb.append("   Z.DD_NAME ZONENAME, 		"); 
		 sb.append("   T.ID ID, 				"); 
		 sb.append("   T.SB_ORGCODE ORGCODE, 	"); 
		 sb.append("   T.SB_ORGNAME ORGNAME, 	"); 
		 sb.append("   T.SYS_NAME SYSNAME, 		"); 
		 sb.append("   T.SYS_CODE SYSCODE, 		"); 
		 sb.append("   T.SYS_IP SYSIP, 			"); 
	     sb.append("   T.SYS_PORT SYSPORT, 		"); 
	     sb.append("   T.SYS_DEPLY SYSDEPLY,	"); 
	     sb.append("   T.SYS_ADMIN SYSADMIN, 	"); 
	     sb.append("   T.SYS_TEL SYSTEL, 		"); 
	     sb.append("   T.SYS_FLAG SYSFLAG, 		"); 
	     sb.append("   T.DOMAIN_ID DOMAINID, 	"); 
	     sb.append("   TO_CHAR(T.ZC_DT,'YYYY-MM-DD') ZCDT, 	"); 
	     sb.append("   T.WS_URL WSURL, 			"); 
	     sb.append("   T.WS_METHOD METHOD 		"); 
	     sb.append(" FROM PS_SUB_ORG_CONFIG T 		"); 
	     sb.append("  LEFT JOIN V_JR_ORGANIZATION O ON T.SB_ORGCODE = O.ORGCODE 	"); 
		 sb.append(" 		 LEFT JOIN V_ZONECODE Z ON O.APANAGECODE = Z.DD_ID  "); 
		 sb.append(" WHERE  1=1					");
		if (orgname != null && !orgname.isEmpty()) {
				sb.append(" AND T.SB_ORGNAME LIKE '%" + orgname + "%' ");
		}
		sb.append(" ORDER BY Z.DD_ID,T.SB_ORGNAME  ");
		String sql = sb.toString();
		String countSql = page.buildCountSql(sql);
		page.setTotal(subOrgDao.getCount(countSql, new Object[]{}));
		logger.debug("Execute sql:["+countSql+"],params[]");
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:["+querySql+"],params[]");
		return subOrgDao.find(querySql, new Object[]{});		
	}

	public void setSubscribeOrgDao(ISubOrgDao subOrgDao) {
		this.subOrgDao = subOrgDao;
	}

}
