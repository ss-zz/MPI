package com.sinosoft.mpi.ws;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.PersonInfoSimple;

/**
 * 索引信息查询接口
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IPersonQueryWS {

	/**
	 * 根据域和域居民主键 取得该居民索引所关联的索引
	 * 
	 * @param p
	 *            简单居民信息对象
	 * @return 结果集仅含PersonIndex信息 nullable
	 */
	QueryResult queryIndexByIdentifier(@WebParam(name = "simplePerson") PersonInfoSimple simplePerson);

	/**
	 * 根据居民属性查询 取得符合条件的居民
	 * 
	 * @param p
	 *            居民信息
	 * @return nullable
	 */
	QueryResult queryPersonByAttributes(@WebParam(name = "person") PersonInfo person);

	/**
	 * 根据索引id查询 取得关联的所有居民信息
	 * 
	 * @param indexId
	 *            索引id
	 * @return 结果集仅含PersonInfo[]信息 nullable
	 */
	QueryResult queryPersonByIndex(@WebParam(name = "indexId") String indexId);

	/**
	 * 根据索引id和域唯一标识查询居民信息
	 * 
	 * @param indexId
	 *            索引id
	 * @param domainUniqueSign
	 *            域唯一标识
	 * @return 结果集仅含PersonInfo[]信息 nullable
	 */
	QueryResult queryPersonByIndexAndUniqueSign(@WebParam(name = "indexId") String indexId,
			@WebParam(name = "domainUniqueSign") String domainUniqueSign);

}
