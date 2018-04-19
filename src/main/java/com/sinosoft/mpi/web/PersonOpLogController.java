package com.sinosoft.mpi.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.SexCode;
import com.sinosoft.mpi.service.IPersonIdxLogService;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 索引操作日志(人员角度)
 */
@Controller
@RequestMapping("/personlog/pl.ac")
public class PersonOpLogController {
	private Logger logger = Logger.getLogger(PersonOpLogController.class);
	@Resource
	private IPersonIdxLogService personIdxLogService;

	/**
	 * 显示人员列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping
	public String list(PageInfo page, PersonInfo t, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = null;
		try {
			list = personIdxLogService.queryPersonForMapPage(t, page);
			converDateCode(list);
		} catch (Throwable e) {
			logger.error("查询索引日志时出错", e);
		}
		JSONObject datas = new JSONObject();
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
		return null;
	}

	/*
	 * 处理日期
	 */
	private void converDateCode(final List<Map<String, Object>> list) {
		// 转换编码数据
		for (Map<String, Object> map : list) {
			for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (map.get(key) != null && map.get(key) instanceof Date) {
					if (map.get(key) != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
						String result = sdf.format((Date) map.get(key));
						map.put(key, result);
					}
				}
			}
		}
	}

	/**
	 * 查看详细匹配信息
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=listOp")
	public String listOp(String personId, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		List<Map<String, Object>> list = null;
		try {
			list = personIdxLogService.queryOpLogByPersonId(personId);
			converCode(list);
		} catch (Exception e) {
			logger.error("查询索引日志时出错", e);
		}
		JSONArray datas = JSONArray.fromObject(list);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
		return null;
	}

	private void converCode(final List<Map<String, Object>> list) {
		// 转换编码数据
		for (Map<String, Object> map : list) {
			String sexCode = (String) map.get("GENDER_CD");
			if (StringUtils.isNotBlank(sexCode)) {
				String sexName = CacheManager.get(SexCode.class, sexCode).getCodeName();
				map.put("sexName", sexName);
			}
			java.sql.Timestamp BIRTH_DATE = (java.sql.Timestamp) map.get("BIRTH_DATE");
			if (BIRTH_DATE != null) {
				// 将TIMESTAMP.DATE 转成UTIL.DATE
				java.util.Date date = new java.util.Date(BIRTH_DATE.getTime());
				map.put("BIRTH_DATE", DateUtil.getDate(date));
			}
		}
	}

	public void setPersonIdxLogService(IPersonIdxLogService personIdxLogService) {
		this.personIdxLogService = personIdxLogService;
	}
}
