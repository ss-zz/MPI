package com.sinosoft.subscription.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.subscription.dao.ISubThemeDao;
import com.sinosoft.subscription.model.SubscribeTheme;
import com.sinosoft.subscription.service.ISubThemeService;

@Service("subThemeService")
public class SubThemeService implements ISubThemeService {
	
	private Logger logger = Logger.getLogger(SubThemeService.class);
	
	@Resource
	private ISubThemeDao subThemeDao;

	//新增
	@Override
	public void save(SubscribeTheme t) {
		subThemeDao.add(t);
		logger.debug("Add SubscribeTheme:"+t);
	}
	
	//更新
	@Override
	public void update(SubscribeTheme t) {
		subThemeDao.update(t);
		logger.debug("Update SubscribeTheme:"+t);
	}

	//删除
	@Override
	public void delete(SubscribeTheme t) {
		subThemeDao.deleteById(t);
		logger.debug("Del SubscribeTheme:configId="+t.getTopicId());
	}

	@Override
	public SubscribeTheme getObject(String id) {
		return null;
	}

	//查询
	@Override
	public List<SubscribeTheme> queryForPage(SubscribeTheme t, PageInfo page) {
		String themeName = t.getTopicName();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT T.TOPIC_ID TOPICID, 				"); 
		sb.append("    T.TOPIC_TYPE_CD TOPICTYPECD, 			"); 
		sb.append("    T.TOPIC_NAME TOPICNAME, 				"); 
		sb.append("    T.TOPIC_DESC TOPICDESC, 				"); 
		sb.append("    DECODE(T.FLAG,'0','有效','1','无效') FLAG 	"); 
		sb.append(" FROM PS_topic_type T 					"); 
		sb.append(" WHERE  1=1									");
		if (themeName != null && !themeName.isEmpty()) {
			sb.append(" AND T.TOPIC_NAME LIKE '%" + themeName + "%' ");
		}
		String sql = sb.toString();
		String countSql = page.buildCountSql(sql);
		page.setTotal(subThemeDao.getCount(countSql, new Object[]{}));
		logger.debug("Execute sql:["+countSql+"],params[]");
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:["+querySql+"],params[]");
		return subThemeDao.find(querySql, new Object[]{});		
	}

	public void setSubscribeThemeDao(ISubThemeDao subThemeDao) {
		this.subThemeDao = subThemeDao;
	}

}
