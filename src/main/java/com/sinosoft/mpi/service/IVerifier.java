package com.sinosoft.mpi.service;

/**
 * @Description 信息验证器
 *
 * @Package com.sinosoft.mpi.service
 * @author Bysun
 * @version v1.0,2012-3-22
 * @see
 * @since （可选）
 *
 */
public interface IVerifier<T> {

	VerifyResult verify(T t);

}
