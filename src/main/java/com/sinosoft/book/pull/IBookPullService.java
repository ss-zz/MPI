package com.sinosoft.book.pull;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.sinosoft.book.model.BookMassage;

/**
 * 索引信息订阅拖拽接口
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IBookPullService {

	/**
	 * 取得指定域下的索引操作信息
	 * 
	 * @param domainUniqueSign
	 *            域唯一标志
	 * @return
	 */
	BookMassage[] getMassages(@WebParam(name = "domainUniqueSign") String domainUniqueSign);

}
