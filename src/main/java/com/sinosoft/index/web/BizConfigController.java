package com.sinosoft.index.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.index.entity.BizMatchConfig;
import com.sinosoft.index.model.SimpleRestResponse;
import com.sinosoft.index.service.BizConfigService;

/**
 * 业务配置控制器
 * 
 * @author sinosoft
 *
 */
@Controller
@RequestMapping("/mgr/bizconfig")
public class BizConfigController {

	@Autowired
	BizConfigService bizConfigService;

	/**
	 * 获取所有列表数据
	 * 
	 * @return
	 */
	@GetMapping("/all")
	@ResponseBody
	public List<BizMatchConfig> getAll() {
		return bizConfigService.getAll();
	}

	/**
	 * 跳转编辑、新增页面
	 * 
	 * @param id
	 * @param mav
	 * @return
	 */
	@GetMapping("/toEditPage")
	public ModelAndView toEditPage(String id, ModelAndView mav) {
		mav.setViewName("/config/page/biz_config_add");
		if (id != null) {
			mav.addObject("item", bizConfigService.getById(id));
		}
		return mav;
	}

	/**
	 * 跳转业务字段配置页面
	 * 
	 * @param id
	 * @param mav
	 * @return
	 */
	@GetMapping("/toBizFieldConfigPage/{id}")
	public ModelAndView toBizFieldConfigPage(@PathVariable String id, ModelAndView mav) {
		mav.setViewName("/config/page/biz_field_config");
		mav.addObject("itemBiz", bizConfigService.getById(id));
		return mav;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public Map<String, Object> save(BizMatchConfig bizConfig) {
		return SimpleRestResponse.create("id", bizConfigService.save(bizConfig));
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@PostMapping("/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable String id) {
		bizConfigService.del(id);
		return SimpleRestResponse.create("id", id);
	}

}
