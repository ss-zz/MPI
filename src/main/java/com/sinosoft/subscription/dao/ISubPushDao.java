package com.sinosoft.subscription.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.dao.IBaseDao;
import com.sinosoft.subscription.model.SubscribePush;


/** 
* @Description: TODO 订阅发布主题DAO
* @author sinosoft_SunWG
* @date 2013-11-19 下午3:45:16  
*/
public interface ISubPushDao extends IBaseDao<SubscribePush> {
	public List<Map<String , String>> findAll(String sql);
}
