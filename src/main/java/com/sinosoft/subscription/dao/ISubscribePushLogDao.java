package com.sinosoft.subscription.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.dao.IBaseDao;
import com.sinosoft.subscription.model.SubscribePushLog;

public interface ISubscribePushLogDao extends IBaseDao<SubscribePushLog> {
	public List<Map<String , String>> findAll(String sql);
}
