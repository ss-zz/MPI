package com.sinosoft.mpi.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.biz.MpiBizIndex;
import com.sinosoft.mpi.service.biz.BizIndexService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 主索引业务控制器
 * @author admin
 *
 */
@Controller
@RequestMapping("/indexBIZ")
public class IndexBIZController {
	
	@Resource
	BizIndexService BizIndexService;
	
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
	 * 获取主索引列表数据
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> listIndex(PageInfo page,MpiBizIndex  bizIndex) {
		Map<String, Object> datas = new HashMap<>();
		page.setPage(page.getPage()-1);
		List<MpiBizIndex> list = BizIndexService.queryForPage(bizIndex, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}
	
}
