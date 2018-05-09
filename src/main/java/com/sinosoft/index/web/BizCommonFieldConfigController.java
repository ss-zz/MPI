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

import com.sinosoft.index.entity.BizCommonFieldConfig;
import com.sinosoft.index.exception.ServiceException;
import com.sinosoft.index.model.SimpleRestResponse;
import com.sinosoft.index.service.BizCommonFieldConfigService;

/**
 * 通用字段配置控制器
 * 
 * @author sinosoft
 *
 */
@Controller
@RequestMapping("/mgr/bizcommonfieldconfig")
public class BizCommonFieldConfigController {

	@Autowired
	BizCommonFieldConfigService bizCommonFieldConfigService;

	/**
	 * 获取所有列表数据
	 * 
	 * @return
	 */
	@GetMapping("/all")
	@ResponseBody
	public List<BizCommonFieldConfig> getAll() {
		return bizCommonFieldConfigService.getAll();
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
		mav.setViewName("/config/page/common_field_config_add");
		if (id != null) {
			mav.addObject("item", bizCommonFieldConfigService.getById(id));
		}
		return mav;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public Map<String, Object> save(BizCommonFieldConfig bizCommonFieldConfig) {
		try {
			return SimpleRestResponse.create("id", bizCommonFieldConfigService.save(bizCommonFieldConfig));
		} catch (ServiceException e) {
			return SimpleRestResponse.createErrorMsg(e.getMessage());
		}

	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@PostMapping("/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable String id) {
		bizCommonFieldConfigService.del(id);
		return SimpleRestResponse.create("id", id);
	}

}
