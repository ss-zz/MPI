package com.sinosoft.book.push;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.sinosoft.book.model.BookMassage;

/**
 * 将订阅的主索引变更信息推送到终端-终端方实现本接口
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IBookPushHandler {

	/**
	 * 接收推送消息 (建议收到后异步处理)
	 */
	void receiveMessage(@WebParam(name = "msg") BookMassage[] msg);
}
