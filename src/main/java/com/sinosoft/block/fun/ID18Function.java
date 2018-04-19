package com.sinosoft.block.fun;

import com.sinosoft.block.BlockException;
import com.sinosoft.mpi.util.IdcardUtils;

/**
 * 转换为18位
 */
public class ID18Function implements IBlockFuntion {

	@Override
	public String fun(String value) {
		String result = null;
		if (value.length() == 18) {
			result = value;
		} else if (value.length() == 15) {
			result = IdcardUtils.conver15CardTo18(value);
		} else {
			throw new BlockException("身份证不符合标准。实际值为：" + value);
		}
		return result;
	}

}
