package com.sinosoft.mpi.service;

/**
 * 信息验证器
 */
public interface IVerifier<T> {

	VerifyResult verify(T t);

}
