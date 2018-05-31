package com.sinosoft.block.fun;

import org.apache.commons.lang.StringUtils;

import com.sinosoft.block.BlockException;
import com.sinosoft.mpi.util.IdcardUtils;

/**
 * 转换为15位
 */
public class ID15Function implements IBlockFuntion {

	@Override
	public String fun(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		String result = null;
		if (value.length() == 15) {
			result = value;
		} else if (value.length() == 18) {
			result = IdcardUtils.conver18CardTo15(value);
		} else {
			throw new BlockException("身份证不符合标准。实际值为：" + value);
		}
		return result;
	}

}
