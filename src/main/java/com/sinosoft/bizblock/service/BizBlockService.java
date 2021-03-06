package com.sinosoft.bizblock.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.bizblock.config.BizBlockConfig;
import com.sinosoft.block.BlockException;
import com.sinosoft.block.fun.IBlockFuntion;
import com.sinosoft.block.model.BlockField;
import com.sinosoft.block.model.BlockRound;
import com.sinosoft.match.model.Record;
import com.sinosoft.mpi.model.biz.MpiBizIndex;
import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;
import com.sinosoft.mpi.service.biz.BizIndexService;

/**
 * 业务初筛服务
 */
@Service
public class BizBlockService {

	@Autowired
	BizIndexService bizIndexService;

	/**
	 * 获取初筛匹配的业务信息
	 *
	 * @param record
	 *            业务信息
	 * @return 初筛结果集
	 */
	public List<Record<MpiBizIndex>> findCandidates(Record<MpiBizInfoRegister> record) {
		List<Record<MpiBizIndex>> result = new ArrayList<Record<MpiBizIndex>>();
		List<BlockRound> rounds = BizBlockConfig.getInstanse().getBlockRounds();
		List<Object> args = new ArrayList<Object>();

		StringBuilder sb = new StringBuilder();
		sb.append(" select *  from mpi_biz_index a where ");
		int k = 0;
		for (int i = 0; i < rounds.size(); i++) {
			BlockRound round = rounds.get(i);

			List<BlockField> fields = round.getBlockFields();
			for (int j = 0; j < fields.size(); j++) {
				BlockField field = fields.get(j);
				String fieldvalue = getFieldValue(record.get(field.getDbField()));
				boolean isdate = getObjType(record.get(field.getDbField()));
				if (verifyPerson(field.getDbField(), fieldvalue)) {
					args.add(foundArg(record, field));
					if (i != 0) {
						if (i > k) {
							if (j == 0) {
								sb.append(" or ");
							}
						}
					}
					if (isdate) {
						if (j == 0) {
							sb.append(" (").append(field.getDbField()).append("=TO_DATE(?, 'YYYY-mm-dd HH24:MI:SS') ");
						}
						if (j != 0) {
							sb.append(" and ").append(field.getDbField())
									.append("=TO_DATE(?, 'YYYY-mm-dd HH24:MI:SS')  ");
						}
					} else {
						if (j == 0) {
							sb.append(" (").append(field.getDbField()).append("=? ");
						}
						if (j != 0) {
							sb.append(" and ").append(field.getDbField()).append("=? ");
						}
					}

					if (j == fields.size() - 1) {
						sb.append(")");
					}
				} else {
					k++;
				}

			}
		}
		if (k != rounds.size()) {
			List<MpiBizIndex> indexes = bizIndexService.find(sb.toString(), args.toArray());
			for (MpiBizIndex index : indexes) {
				Record<MpiBizIndex> indexRecord = new Record<MpiBizIndex>(index);
				indexRecord.setRecordId(index.getId());// 主键标志
				result.add(indexRecord);
			}

		}
		return result;
	}

	/**
	 * 根据字段配置获取字段的值
	 *
	 * @param record
	 *            人员信息
	 * @param field
	 *            字段配置
	 * @return 指定字段的值
	 */
	private Object foundArg(Record<MpiBizInfoRegister> record, BlockField field) {
		Object result = null;
		if (field.getFunName() == null || "".equals(field.getFunName().trim())) {
			result = record.getAsString(field.getField());
		} else {
			IBlockFuntion fun = null;
			try {
				fun = IBlockFuntion.class.cast(Class.forName(field.getFunName()).newInstance());
				result = fun.fun(record.getAsString(field.getField()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new BlockException("函数处理出错", e);
			}
		}
		return result;
	}

	/**
	 * 获取字段值-字符串格式
	 *
	 * @param obj
	 *            字段内容
	 * @return 字符串格式字段内容
	 */
	public static String getFieldValue(Object obj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fieldvalue = "";
		if (obj instanceof java.util.Date) {
			fieldvalue = sdf.format(obj);
		} else if (obj instanceof java.sql.Date) {
			java.sql.Date sdt = (java.sql.Date) obj;
			java.util.Date udt = new Date(sdt.getTime());
			fieldvalue = sdf.format(udt);
		} else {
			fieldvalue = (String) obj;
		}
		return fieldvalue;
	}

	/**
	 * 获取字段值是否为日期类型
	 *
	 * @param obj
	 *            字段内容
	 * @return 是否为日期
	 */
	public static boolean getObjType(Object obj) {
		boolean res = false;
		if (obj instanceof java.util.Date) {
			res = true;
		} else if (obj instanceof java.sql.Date) {
			res = true;
		} else {

		}
		return res;
	}

	/**
	 * 验证身份证号
	 *
	 * @param value
	 *            身份证号
	 * @return
	 */
	private boolean verifyIdNo(String value) {
		boolean result = true;
		Pattern reg = Pattern.compile("^(\\d{15}|\\d{18}|\\d{17}[A-Za-z])$");
		Matcher m = reg.matcher(value);
		if (!m.matches()) {
			return false;
		}
		return result;
	}

	/**
	 * 验证新农合卡号
	 *
	 * @param value
	 *            新农合卡号
	 * @return
	 */
	private boolean verifyNhCard(String value) {
		boolean result = true;
		Pattern reg = Pattern.compile("^\\d{16}$");
		Matcher m = reg.matcher(value);
		if (!m.matches()) {
			return false;
		}
		return result;
	}

	/**
	 * 验证居民健康档案号
	 *
	 * @param value
	 *            居民健康档案号
	 * @return
	 */
	private boolean verifyHRID(String value) {
		boolean result = true;
		Pattern reg = Pattern.compile("^\\d{17}$");
		Matcher m = reg.matcher(value);
		if (!m.matches()) {
			result = false;
		}
		return result;
	}

	/**
	 * 验证社会保障卡号
	 *
	 * @param value
	 *            社会保障卡号
	 * @return
	 */
	private boolean verifySSCID(String value) {
		boolean result = true;
		Pattern reg = Pattern.compile("^(\\d{15}|\\d{18}|\\d{17}[A-Za-z])$");
		Matcher m = reg.matcher(value);
		if (!m.matches()) {
			result = false;
		}
		return result;
	}

	/**
	 * 验证人员字段值是否合法
	 *
	 * @param field
	 *            字段名
	 * @param fieldvalue
	 *            字段值
	 * @return 是否合法
	 */
	private boolean verifyPerson(String field, String fieldvalue) {
		boolean result = true;
		if (StringUtils.isBlank(fieldvalue)) {// 为空
			return false;
		}
		if ("ID_NO".equals(field)) {// 身份证号
			result = verifyIdNo(fieldvalue);
		} else if ("NH_CARD".equals(field)) {
			result = verifyNhCard(fieldvalue);
		} else if ("HR_ID".equals(field)) {
			result = verifyHRID(fieldvalue);
		} else if ("SSCID".equals(field)) {
			result = verifySSCID(fieldvalue);
		}
		return result;
	}

}
