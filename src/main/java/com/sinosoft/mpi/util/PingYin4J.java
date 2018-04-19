package com.sinosoft.mpi.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 将汉字转化成拼音
 */
public class PingYin4J {

	public static String getPingYin(String src) {
		char[] t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 大写
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// WITH_TONE_NUMBER//第几声
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		try {
			for (int i = 0; i < t1.length; i++) {
				// 判断是否为汉字字符函数
				if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0] + " ";
				} else {
					t4 += java.lang.Character.toString(t1[i]) + " ";
				}
			}
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			System.out.println("转码错误");
		}
		return t4;
	}

	// 返回中文的首字母
	public static String getPinYinHeadChar(String str) {
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray;
			try {
				pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word, t3);
				if (pinyinArray != null) {
					convert += pinyinArray[0].charAt(0);
				} else {
					convert += word;
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}

		}
		return convert;
	}

	/*******************************************
	 * 网上例子 *********************************************************\ /**
	 * 汉字转换位汉语全拼，英文字符不变，特殊字符丢失
	 * 支持多音字，生成方式如（重当参:zhongdangcen,zhongdangcan,chongdangcen
	 * ,chongdangshen,zhongdangshen,chongdangcan）
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToSpell1(String chines) {
		StringBuffer pinyinName = new StringBuffer();
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					// 取得当前汉字的所有全拼
					String[] strs = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
					if (strs != null) {
						for (int j = 0; j < strs.length; j++) {
							pinyinName.append(strs[j]);
							if (j != strs.length - 1) {
								pinyinName.append(",");
							}
						}
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName.append(nameChar[i]);
			}
			pinyinName.append(" ");
		}
		return pinyinName.toString();
	}

	/**
	 * 解析并组合拼音，对象合并方案(推荐使用)
	 * 
	 * @return
	 */
	private static String parseTheChineseByObject(List<Map<String, Integer>> list) {
		Map<String, Integer> first = null; // 用于统计每一次,集合组合数据
		// 遍历每一组集合
		for (int i = 0; i < list.size(); i++) {
			// 每一组集合与上一次组合的Map
			Map<String, Integer> temp = new Hashtable<String, Integer>();
			// 第一次循环，first为空
			if (first != null) {
				// 取出上次组合与此次集合的字符，并保存
				for (String s : first.keySet()) {
					for (String s1 : list.get(i).keySet()) {
						String str = s + s1;
						temp.put(str, 1);
					}
				}
				// 清理上一次组合数据
				if (temp != null && temp.size() > 0) {
					first.clear();
				}
			} else {
				for (String s : list.get(i).keySet()) {
					String str = s;
					temp.put(str, 1);
				}
			}
			// 保存组合数据以便下次循环使用
			if (temp != null && temp.size() > 0) {
				first = temp;
			}
		}
		String returnStr = "";
		if (first != null) {
			// 遍历取出组合字符串
			for (String str : first.keySet()) {
				returnStr += (str + ",");
			}
		}
		if (returnStr.length() > 0) {
			returnStr = returnStr.substring(0, returnStr.length() - 1);
		}
		return returnStr;
	}

	/**
	 * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（重当参:cdc,zds,cds,zdc）
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToFirstSpell(String chines) {
		StringBuffer pinyinName = new StringBuffer();
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					// 取得当前汉字的所有全拼
					String[] strs = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
					if (strs != null) {
						for (int j = 0; j < strs.length; j++) {
							// 取首字母
							pinyinName.append(strs[j].charAt(0));
							if (j != strs.length - 1) {
								pinyinName.append(",");
							}
						}
					}
					// else {
					// pinyinName.append(nameChar[i]);
					// }
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName.append(nameChar[i]);
			}
			pinyinName.append(" ");
		}
		// return pinyinName.toString();
		return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
	}

	/**
	 * 汉字转换位汉语全拼，英文字符不变，特殊字符丢失
	 * 支持多音字，生成方式如（重当参:zhongdangcen,zhongdangcan,chongdangcen
	 * ,chongdangshen,zhongdangshen,chongdangcan）
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToSpell(String chines) {
		StringBuffer pinyinName = new StringBuffer();
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					// 取得当前汉字的所有全拼
					String[] strs = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
					if (strs != null) {
						for (int j = 0; j < strs.length; j++) {
							pinyinName.append(strs[j]);
							if (j != strs.length - 1) {
								pinyinName.append(",");
							}
						}
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName.append(nameChar[i]);
			}
			pinyinName.append(" ");
		}
		// return pinyinName.toString();
		return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
	}

	/**
	 * 去除多音字重复数据
	 * 
	 * @param theStr
	 * @return
	 */
	private static List<Map<String, Integer>> discountTheChinese(String theStr) {
		// 去除重复拼音后的拼音列表
		List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();
		// 用于处理每个字的多音字，去掉重复
		Map<String, Integer> onlyOne = null;

		String[] firsts = theStr.split(" ");
		// 读出每个汉字的拼音
		for (String str : firsts) {
			onlyOne = new Hashtable<String, Integer>();
			String[] china = str.split(",");
			// 多音字处理
			for (String s : china) {
				Integer count = onlyOne.get(s);
				if (count == null) {
					onlyOne.put(s, new Integer(1));
				} else {
					onlyOne.remove(s);
					count++;
					onlyOne.put(s, count);
				}
			}
			mapList.add(onlyOne);
		}
		return mapList;
	}

	public static void main(String[] args) {
		// 人名(刘云飞),全拼(LIUYUNFEI) ,缩写(LYF), 医保号,身份证号, 人员ID
		System.out.println(converterToSpell("牛犇"));
		System.out.println(converterToFirstSpell("牛犇"));
	}
}
