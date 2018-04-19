package com.sinosoft.mpi.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.model.PersonInfo;

@Service("personInfoVerifier")
public class PersonInfoVerifier implements IVerifier<PersonInfo> {

	private Logger logger = Logger.getLogger(PersonInfoVerifier.class);

	@Override
	public VerifyResult verify(final PersonInfo t) {
		boolean result = true;
		StringBuilder sb = new StringBuilder();
		// 验证自身属性必填值
		result = result && verifyIdNoValue(t);
		if (!result) {
			sb.append("身份证长度不等于15位或18位；");
		}
		// WHN 2017-05-01 居民健康卡号验证及非标准字符过滤
		result = result && verifyCardNoValue(t);
		if (!result) {
			sb.append("健康卡号不等于15位或18位；");
		}
		result = result && verifyBlankValue(t.getNAME_CN());
		if (!result) {
			sb.append("中文名称信息为空；");
		}
		result = result && verifyBlankValue(t.getGENDER_CD());
		if (!result) {
			sb.append("性别信息为空；");
		}
		// 验证域信息
		result = result && verifyBlankValue(t.getUNIQUE_SIGN());
		if (!result) {
			sb.append("身份域信息为空；");
		}
		VerifyResult ret = new VerifyResult();
		ret.setSuccess(result);
		ret.setMsg(sb.toString());
		return ret;
	}

	private boolean verifyBlankValue(String value) {
		return !StringUtils.isBlank(value);
	}
	// lpk update 2013年5月9日 验证身份证
	private boolean verifyIdNoValue(PersonInfo person) {
		String value = person.getID_NO();
		boolean result = true;
		Pattern reg = Pattern.compile("^(\\d{15}|\\d{18}|\\d{17}[A-Za-z])$");
		if (value != null) {
			Matcher m = reg.matcher(value);
			if (m.matches()) {

			} else {
				person.setID_NO(null);
			}
		}
		return result;
	}

	// WHN update 2017年5月1日 验证居民健康卡号
	private boolean verifyCardNoValue(PersonInfo person) {
		String value = person.getCARD_NO();
		boolean result = true;
		// 正则表达式:数字加字母不大于40个字符
		Pattern reg = Pattern.compile("^[0-9A-Za-z]{1,40}$");
		if (value != null) {
			Matcher m = reg.matcher(value);
			if (m.matches()) {

			} else {
				person.setCARD_NO(null);
			}
		}
		return result;
	}

	// update 2013年12月9日 验证新农合卡
	private boolean verifyNhCard(PersonInfo person) {
		String value = person.getNH_CARD();
		boolean result = true;
		// Pattern reg = Pattern.compile("^\\d{16}$");
		if (StringUtils.isBlank(value)) {// 为空
			person.setNH_CARD(null);
		}
		return result;
	}
	// lpk update 2013年12月9日 居民健康档案编号为17位
	private boolean verifyHRID(PersonInfo person) {
		String value = person.getHR_ID();
		boolean result = true;
		if (StringUtils.isBlank(value)) {// 为空
			person.setHR_ID(null);
		}
		return result;
	}
	// lpk update 2013年12月9日 社会保障卡号
	private boolean verifySSCID(PersonInfo person) {
		String value = person.getSSCID();
		boolean result = true;
		if (StringUtils.isBlank(value)) {// 为空
			person.setSSCID(null);
		}
		return result;
	}
}
