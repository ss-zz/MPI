package com.sinosoft.mpi.ws;

/**
 * 注册人员测试
 */
public class RegisterPersonTest extends BaseTest {

	public static void main(String[] args) {
		int count = 10;
		for(int i = 0; i < count; i++) {
			post("api/pub/index/register/person", DemoDataFactory.getPersonRegister());
		}
	}

}
