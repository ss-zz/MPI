package com.sinosoft.mpi.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.model.PersonInfo;

/**
 * 人员信息验证
 */
@Service
public class PersonInfoVerifier {

	/**
	 * 验证人员信息
	 * 
	 * @param t
	 * @return
	 */
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
		result = result && verifyBlankValue(t.getNameCn());
		if (!result) {
			sb.append("中文名称信息为空；");
		}
		result = result && verifyBlankValue(t.getGenderCd());
		if (!result) {
			sb.append("性别信息为空；");
		}
		// 验证域信息
		result = result && verifyBlankValue(t.getUniqueSign());
		if (!result) {
			sb.append("身份域信息为空；");
		}
		VerifyResult ret = new VerifyResult();
		ret.setSuccess(result);
		ret.setMsg(sb.toString());
		return ret;
	}

	/**
	 * 验证空值
	 * 
	 * @param value
	 * @return
	 */
	private boolean verifyBlankValue(String value) {
		return !StringUtils.isBlank(value);
	}

	/**
	 * 验证身份证
	 * 
	 * @param person
	 * @return
	 */
	private boolean verifyIdNoValue(PersonInfo person) {
		String value = person.getIdNo();
		boolean result = true;
		Pattern reg = Pattern.compile("^(\\d{15}|\\d{18}|\\d{17}[A-Za-z])$");
		if (value != null) {
			Matcher m = reg.matcher(value);
			if (m.matches()) {

			} else {
				person.setIdNo(null);
			}
		}
		return result;
	}

	/**
	 * 验证居民健康卡号
	 * 
	 * @param person
	 * @return
	 */
	private boolean verifyCardNoValue(PersonInfo person) {
		String value = person.getCardNo();
		boolean result = true;
		// 正则表达式:数字加字母不大于40个字符
		Pattern reg = Pattern.compile("^[0-9A-Za-z]{1,40}$");
		if (value != null) {
			Matcher m = reg.matcher(value);
			if (m.matches()) {

			} else {
				person.setCardNo(null);
			}
		}
		return result;
	}

	/**
	 * 验证新农合卡
	 * 
	 * @param person
	 * @return
	 */
	public boolean verifyNhCard(PersonInfo person) {
		String value = person.getNhCard();
		boolean result = true;
		if (StringUtils.isBlank(value)) {// 为空
			person.setNhCard(null);
		}
		return result;
	}

	/**
	 * 验证居民健康档案编号
	 * 
	 * @param person
	 * @return
	 */
	public boolean verifyHRID(PersonInfo person) {
		String value = person.getHrId();
		boolean result = true;
		if (StringUtils.isBlank(value)) {// 为空
			person.setHrId(null);
		}
		return result;
	}

	/**
	 * 社会保障卡号
	 * 
	 * @param person
	 * @return
	 */
	public boolean verifySSCID(PersonInfo person) {
		String value = person.getSscid();
		boolean result = true;
		if (StringUtils.isBlank(value)) {// 为空
			person.setSscid(null);
		}
		return result;
	}

}
