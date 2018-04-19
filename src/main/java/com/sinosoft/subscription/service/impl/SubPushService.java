package com.sinosoft.subscription.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.subscription.dao.ISubPushDao;
import com.sinosoft.subscription.model.SubscribePush;
import com.sinosoft.subscription.service.ISubPushService;

@Service("subPushService")
public class SubPushService implements ISubPushService {
	
	private Logger logger = Logger.getLogger(SubPushService.class);
	
	@Resource
	private ISubPushDao subPushDao;

	//新增
	@Override
	public void save(SubscribePush t) {
		subPushDao.add(t);
		logger.debug("Add SubscribePush:"+t);
	}
	
	//更新
	@Override
	public void update(SubscribePush t) {
		subPushDao.update(t);
		logger.debug("Update SubscribePush:"+t);
	}

	//删除
	@Override
	public void delete(SubscribePush t) {
		subPushDao.deleteById(t);
		logger.debug("Del SubscribePush:configId="+t.getId());
	}

	@Override
	public SubscribePush getObject(String id) {
		return null;
	}

	//查询
	public List<Map<String, String>> queryForPageMap(SubscribePush t, PageInfo page) {
		String pushName = t.getPushName();
		StringBuffer sb = new StringBuffer();
		sb.append("  SELECT T.PUSH_ID ID, ");
		sb.append("      T.PUSH_NO PUSHNO, ");
		sb.append("      T.PUSH_NAME PUSHNAME, ");
		sb.append("      T.TOPIC_TYPE_CD TOPICTYPE,  ");
		sb.append("      P.TOPIC_NAME TOPICNAME, ");
		sb.append("      T.DOMAIN_CODE ORGCODE, ");
		sb.append("      O.ORGNAME ORGNAME,  ");
		sb.append("      TO_CHAR(T.PUSH_DATE,'YYYY-MM-DD') PUSHDATE,");
		sb.append("      TO_CHAR(T.DISABLE_DATE,'YYYY-MM-DD') DISABLEDATE,");
		sb.append("      DECODE(T.FLAG,'0','有效','1','无效') FLAG, ");
		sb.append("      T.PUSH_DESC PUSHDESC  ");
		sb.append("  FROM PS_PUSH_CONFIG T ");
		sb.append("       LEFT JOIN PS_TOPIC_TYPE P ON T.TOPIC_TYPE_CD=P.TOPIC_TYPE_CD ");
		sb.append("       LEFT JOIN V_JR_ORGANIZATION O ON T.DOMAIN_CODE=O.ORGCODE ");
		sb.append("  WHERE 1=1	");
		if (pushName != null && !pushName.isEmpty()) {
			sb.append(" AND T.PUSH_NAME LIKE '%" + pushName + "%' ");
		}
		String sql = sb.toString();
		String countSql = page.buildCountSql(sql);
		page.setTotal(subPushDao.getCount(countSql, new Object[]{}));
		logger.debug("Execute sql:["+countSql+"],params[]");
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:["+querySql+"],params[]");
		return subPushDao.findAll(querySql);		
	}

	public void setSubscribePushDao(ISubPushDao subPushDao) {
		this.subPushDao = subPushDao;
	}

	@Override
	public List<SubscribePush> queryForPage(SubscribePush t, PageInfo page) {
		// TODO Auto-generated method stub
		return null;
	}

}
