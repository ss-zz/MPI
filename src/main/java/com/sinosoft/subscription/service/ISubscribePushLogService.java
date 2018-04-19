package com.sinosoft.subscription.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.service.IService;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.subscription.model.SubscribePushLog;

public interface ISubscribePushLogService extends IService<SubscribePushLog> {
	public List<Map<String, String>> queryForPageMap(SubscribePushLog t, PageInfo page);
}
