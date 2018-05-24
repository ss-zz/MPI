package com.sinosoft.mpi.ws;

/**
 * 注册业务测试
 */
public class RegisterBizTest extends BaseTest {

	public static void main(String[] args) {
		post("api/pub/index/register/biz", DemoDataFactory.getBizRegister());
	}

}
