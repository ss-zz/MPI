package com.sinosoft.block.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.block.BlockException;
import com.sinosoft.block.config.BlockConfig;
import com.sinosoft.block.fun.IBlockFuntion;
import com.sinosoft.block.model.BlockField;
import com.sinosoft.block.model.BlockRound;
import com.sinosoft.match.model.Record;
import com.sinosoft.mpi.dao.PersonIndexDao;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**
 * 过滤服务
 */
@Service("blockService")
public class BlockService implements IBlockService {
	private Logger logger = Logger.getLogger(BlockService.class);

	@Resource
	private PersonIndexDao personIndexDao;

	/**
	 * 获取初筛匹配的人员信息
	 *
	 * @param record
	 *            人员信息
	 * @return 初筛结果集
	 * @see com.sinosoft.block.service.IBlockService#findCandidates(com.sinosoft.match.model.Record)
	 */
	@Override
	public List<Record<PersonIndex>> findCandidates(Record<PersonInfo> record) {
		List<Record<PersonIndex>> result = new ArrayList<Record<PersonIndex>>();
		List<BlockRound> rounds = BlockConfig.getInstanse().getBlockRounds();
		List<Object> args = new ArrayList<Object>();

		StringBuilder sb = new StringBuilder();
		sb.append(" select *  from mpi_person_index a where ");
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
		// bysun 2012-5-9 13:38:53 增加一个排序 主动关联至 关联人员最多的索引上
		sb.append(
				" order by (select count(b.COMBINE_NO) from mpi_index_identifier_rel b where b.mpi_pk = a.mpi_pk ) desc ");
		logger.info("{K=" + k + "};{rounds.size()=" + rounds.size() + "}");
		if (k != rounds.size()) {
			logger.info("{sql=" + sb.toString() + "}");
			List<PersonIndex> indexes = personIndexDao.find(sb.toString(), args.toArray());
			for (PersonIndex index : indexes) {
				Record<PersonIndex> indexRecord = new Record<PersonIndex>(index);
				indexRecord.setRecordId(index.getMPI_PK());// 主键标志
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
	private Object foundArg(Record<PersonInfo> record, BlockField field) {
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
