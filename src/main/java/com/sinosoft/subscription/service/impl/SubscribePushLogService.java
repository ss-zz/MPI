package com.sinosoft.subscription.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.sshl.service.impl.DataUploadService;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.subscription.dao.ISubscribePushLogDao;
import com.sinosoft.subscription.model.SubscribePushLog;
import com.sinosoft.subscription.service.ISubscribePushLogService;
/**
 * 
 * @author chenzhongzheng
 * @deprecated 订阅发布推送日志Service
 */
@Service("subscribePushLogService")
public class SubscribePushLogService implements ISubscribePushLogService {
	private Logger logger = Logger.getLogger(DataUploadService.class);
	@Resource
	private ISubscribePushLogDao subscribePushLogDao;
	@Override
	public void save(SubscribePushLog t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SubscribePushLog t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(SubscribePushLog t) {
		// TODO Auto-generated method stub

	}

	@Override
	public SubscribePushLog getObject(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubscribePushLog> queryForPage(SubscribePushLog t, PageInfo page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> queryForPageMap(SubscribePushLog t,
			PageInfo page) {
		StringBuffer sb  = new StringBuffer();
		sb.append(" SELECT T.PUSH_ID ID,			");
		sb.append("    T.SUB_ORGCODE ORGCODE,		");
		sb.append("    T.SUB_ORGNAME ORGNAME,		");
		sb.append("    T.PUSH_TOPIC_CD TOPICCD,		");
		sb.append("    T.PUSH_TOPIC TOPIC,			");
		sb.append("    TO_CHAR(T.PUSH_DATE,'YYYY-MM-DD')  DT,			");
		sb.append("    DECODE(T.PUSH_TYPE,'0','定时','1','即时')  PSTYPE	");
		sb.append(" FROM PS_SEND_INFO T			");
		sb.append("WHERE 1=1	");
		if (t.getOrgname() != null && !t.getOrgname().isEmpty()) {
			sb.append(" AND T.SUB_ORGNAME LIKE '%" + t.getOrgname() + "%' ");
		}
		if (t.getTopic() != null && !t.getTopic().isEmpty()) {
			sb.append(" AND T.PUSH_TOPIC LIKE '%" + t.getTopic()+ "%' ");
		}
		String sql = sb.toString();
		String countSql = page.buildCountSql(sql);
		page.setTotal(subscribePushLogDao.getCount(countSql, new Object[]{}));
		logger.debug("Execute sql:["+countSql+"],params[]");
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:["+querySql+"],params[]");
		return subscribePushLogDao.findAll(querySql);	
	}

}
