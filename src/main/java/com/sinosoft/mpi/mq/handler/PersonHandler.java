package com.sinosoft.mpi.mq.handler;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.model.PersonInfo;

@Service
public class PersonHandler {

	private static Logger logger = Logger.getLogger(PersonHandler.class);
	@Resource
	AddPersonHandler addPersonHandler;
	@Resource
	UpdatePersonHandler updatePersonHandler;
	@Resource
	SplitPersonHandler splitPersonandler;

	public void handleMessage(Object obj) {
		try {
			logger.debug("处理人员对象=>" + obj.toString());
			if (obj instanceof PersonInfo) {
				PersonInfo personinfo = (PersonInfo) obj;
				int state = personinfo.getSTATE();
				if (state == 0) {
					addPersonHandler.handleMessage(personinfo);
				} else if (state == 1) {
					updatePersonHandler.handleMessage(personinfo);
				} else if (state == 2) {
					splitPersonandler.handleMessage(personinfo);
				}
			} else {
				logger.error("错误的事件参数,参数应为Personinfo对象,但实际为" + obj);
				throw new RuntimeException("错误的事件参数,参数应为Personinfo对象,但实际为" + obj);
			}
		} catch (Exception e) {
			String fieldPk = null;
			if (obj != null && obj instanceof PersonInfo) {
				fieldPk = ((PersonInfo) obj).getFIELD_PK();
			}
			logger.error("处理人员发生未知异常[" + fieldPk + "]:" + obj);
			throw new RuntimeException("处理人员发生未知异常[" + fieldPk + "]:" + obj, e);
		}
	}

}
