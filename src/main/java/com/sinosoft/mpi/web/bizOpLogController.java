package com.sinosoft.mpi.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpRequest;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.biz.MpiBizIdxLog;
import com.sinosoft.mpi.service.biz.BizIdxLogService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 主索引操作日志
 */
@Controller
@RequestMapping("/bizLog")
public class bizOpLogController {

	@Resource
	private BizIdxLogService bizIdxLogService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		datetimeFormat.setLenient(false);
		// 自动转换日期类型的字段格式
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 显示操作日志列表
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> list(PageInfo page, MpiBizIdxLog t) {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> list = bizIdxLogService.queryForMapPage(t, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}
	
	/**
	 * 查看索引日志详情
	 */
	@RequestMapping("/view")
	public String view(String logId, ModelMap modelMap) {
		//MpiBizIdxLog map1 = bizIdxLogService.findLogById(logId);
		MpiBizIdxLog map = bizIdxLogService.getObject(logId);
		modelMap.put("log", map);
		return "bizlog/page/view";
	}

	

}
