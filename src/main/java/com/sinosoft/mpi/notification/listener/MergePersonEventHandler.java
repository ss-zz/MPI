package com.sinosoft.mpi.notification.listener;

import org.apache.log4j.Logger;

import com.sinosoft.mpi.notification.event.IEvent;

/**
 * 处理居民合并操作
 */
public class MergePersonEventHandler implements IEventHandler {
	private static Logger logger = Logger.getLogger(MergePersonEventHandler.class);

	@Override
	public void processEvent(IEvent event) {
		Object obj = event.getValue();
		if (obj instanceof String[]) {
			String[] ids = (String[]) obj;
			if (ids.length != 3) {
				logger.error("错误的事件参数,事件参数String[]长度应为3，但实际为" + ids.length);
				throw new RuntimeException("错误的事件参数,事件参数String[]长度应为3，但实际为" + ids.length);
			}
			proccess(ids);
		} else {
			logger.error("错误的事件参数,参数应为PersonIdentifier[]对象,但实际为" + obj);
			throw new RuntimeException("错误的事件参数,参数应为PersonIdentifier[]对象,但实际为" + obj);
		}
	}

	private void proccess(String[] ids) {
		String retiredPersonId = ids[0];
		String fromIndexId = ids[1];
		String toIndexId = ids[2];
		// TODO ben 合并后 暂未实现方法 处理订阅等信息???

		// 得到那个对象合并到那个对象下。

		// 配合并对象和合并到的对象的对应索引是否一致

	}
}
