package com.sinosoft.block.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.block.BlockException;
import com.sinosoft.block.config.BlockConfig;
import com.sinosoft.block.fun.IBlockFuntion;
import com.sinosoft.block.model.BlockField;
import com.sinosoft.block.model.BlockRound;
import com.sinosoft.match.model.Record;
import com.sinosoft.mpi.dao.mpi.PersonIndexDao;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**
 * 人员初筛服务
 */
@Service
public class BlockService {

	@Resource
	private PersonIndexDao personIndexDao;
	@Resource
	JdbcTemplate jdbcTemplate;

	/**
	 * 获取初筛匹配的人员信息
	 *
	 * @param record
	 *            人员信息
	 * @return 初筛结果集
	 */
	public List<Record<PersonIndex>> findCandidates(Record<PersonInfo> record) {
		List<Record<PersonIndex>> result = new ArrayList<Record<PersonIndex>>();
		List<BlockRound> rounds = BlockConfig.getInstanse().getBlockRounds();
		List<Object> args = new ArrayList<Object>();

		StringBuilder sb = new StringBuilder();
		sb.append(" select *  from mpi_person_index a where ");
		// k代表验证不通过字段数量
		int k = 0;
		// 循环组
		for (int i = 0; i < rounds.size(); i++) {
			BlockRound round = rounds.get(i);

			List<BlockField> fields = round.getBlockFields();
			// 循环字段
			for (int j = 0; j < fields.size(); j++) {
				BlockField field = fields.get(j);
				String fieldvalue = getFieldValue(record.get(field.getDbField()));
				boolean isdate = getObjType(record.get(field.getDbField()));

				if (i != 0 && j == 0) {
					sb.append(" or ");
				}
				if (j == 0) {
					sb.append(" (");
				} else {
					sb.append(" and ");
				}

				if (verifyPerson(field.getDbField(), fieldvalue)) {
					args.add(foundArg(record, field));

					if (isdate) {
						sb.append(field.getDbField()).append("=TO_DATE(?, 'YYYY-mm-dd HH24:MI:SS')  ");
					} else {
						sb.append(field.getDbField()).append("=? ");
					}

				} else {
					// 不合法的字段直接不能初筛查询
					sb.append(" 1=2 ");
					k++;
				}

				if (j == fields.size() - 1) {
					sb.append(")");
				}

			}
		}
		// 增加一个排序 主动关联至 关联人员最多的索引上
		sb.append(
				" order by (select count(b.COMBINE_NO) from mpi_index_identifier_rel b where b.mpi_pk = a.mpi_pk ) desc ");
		if (k != rounds.size()) {
			List<PersonIndex> indexes = jdbcTemplate.query(sb.toString(), args.toArray(),
					new BeanPropertyRowMapper<PersonIndex>(PersonIndex.class));
			for (PersonIndex index : indexes) {
				Record<PersonIndex> indexRecord = new Record<PersonIndex>(index);
				indexRecord.setRecordId(index.getMpiPk());// 主键标志
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
			return true;
		}
		return result;
	}

}
