package com.sinosoft.mpi.ws;

/**
 * 注册业务测试
 */
public class RegisterBizTest extends BaseTest {

	public static void main(String[] args) {
		int count = 1;
		for(int i = 0; i < count; i++) {
			post("api/pub/index/register/biz", DemoDataFactory.getBizRegister());
		}
	}

}
