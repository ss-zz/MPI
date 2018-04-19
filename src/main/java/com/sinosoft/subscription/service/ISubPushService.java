package com.sinosoft.subscription.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.service.IService;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.subscription.model.SubscribePush;


/**
 * 订阅发布推送内容service
 */
public interface ISubPushService extends IService<SubscribePush> {
	public List<Map<String, String>> queryForPageMap(SubscribePush t, PageInfo page);
}
