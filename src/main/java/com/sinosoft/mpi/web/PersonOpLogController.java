package com.sinosoft.mpi.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.service.PersonIdxLogService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 索引操作日志(人员角度)
 */
@Controller
@RequestMapping("/personlog/pl.ac")
public class PersonOpLogController {

	@Resource
	private PersonIdxLogService personIdxLogService;

	/**
	 * 显示人员列表
	 */
	@RequestMapping
	@ResponseBody
	public Map<String, Object> list(PageInfo page, PersonInfo t) {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> list = personIdxLogService.queryPersonForMapPage(t, page);
		converDateCode(list);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
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
	 */
	@RequestMapping(params = "method=listOp")
	@ResponseBody
	public List<Map<String, Object>> listOp(String personId) {
		return personIdxLogService.queryOpLogByPersonId(personId);
	}

}
