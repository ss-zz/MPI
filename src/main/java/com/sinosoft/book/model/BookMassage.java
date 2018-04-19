package com.sinosoft.book.model;

import java.io.Serializable;

/**   
*    
* @Description 推送消息
* 
* 
*
* 
* @Package com.sinosoft.book.model 
* @author Bysun
* @version v1.0,2012-4-17
* @see	
* @since	（可选）	
*   
*/ 
public class BookMassage implements Serializable {

	private static final long serialVersionUID = -9212114558039674630L;
	private String indexId; // 索引id
	private String domainUniqueSign; // 域唯一标志
	private String personIdentifier; // 居民主键
	private String type; // 操作类型 
	/**
	 * @param indexId 索引id
	 * @param domainUniqueSign 域唯一标志
	 * @param personIdentifier 域内居民主键
	 * @param type 操作类型  0-建立关联    1-解除关联
	 */
	public BookMassage(String indexId, String domainUniqueSign, String personIdentifier,
			String type) {
		super();
		this.indexId = indexId;
		this.domainUniqueSign = domainUniqueSign;
		this.personIdentifier = personIdentifier;
		this.type = type;
	}
	
	public BookMassage() {
		super();
	}
	/**
	 * 索引id
	 */
	public String getIndexId() {
		return indexId;
	}
	/**
	 * 索引id
	 */
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	/**
	 * 域唯一标志
	 */
	public String getDomainUniqueSign() {
		return domainUniqueSign;
	}
	/**
	 * 域唯一标志
	 */
	public void setDomainUniqueSign(String domainUniqueSign) {
		this.domainUniqueSign = domainUniqueSign;
	}
	/**
	 * 所在域内居民主键
	 */
	public String getPersonIdentifier() {
		return personIdentifier;
	}
	/**
	 * 所在域内居民主键
	 */
	public void setPersonIdentifier(String personIdentifier) {
		this.personIdentifier = personIdentifier;
	}
	/**
	 * 操作类型  0-建立关联    1-解除关联
	 */
	public String getType() {
		return type;
	}
	/**
	 * 操作类型  0-建立关联    1-解除关联
	 */
	public void setType(String type) {
		this.type = type;
	}
}
