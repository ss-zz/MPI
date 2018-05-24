package com.sinosoft.mpi.ws;

/**
 * 注册人员测试
 */
public class RegisterPersonTest extends BaseTest {

	public static void main(String[] args) {
		post("api/pub/index/register/person", DemoDataFactory.getPersonRegister());
	}

}
