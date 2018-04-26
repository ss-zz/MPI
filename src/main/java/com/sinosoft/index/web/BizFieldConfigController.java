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

import com.sinosoft.index.model.BizFieldConfigModel;
import com.sinosoft.index.model.SimpleRestResponse;
import com.sinosoft.index.service.BizFieldConfigService;

/**
 * 业务字段配置控制器
 * 
 * @author sinosoft
 *
 */
@Controller
@RequestMapping("/mgr/bizfieldconfig")
public class BizFieldConfigController {

	@Autowired
	BizFieldConfigService bizFieldConfigService;

	/**
	 * 获取所有列表数据
	 * 
	 * @return
	 */
	@GetMapping("/all")
	@ResponseBody
	public List<BizFieldConfigModel> getAll() {
		return bizFieldConfigService.getAll();
	}

	/**
	 * 获取列表数据-根据业务配置id
	 * 
	 * @return
	 */
	@GetMapping("/findByBizConfigId/{bizConfigId}")
	@ResponseBody
	public List<BizFieldConfigModel> getByBizConfigId(@PathVariable String bizConfigId) {
		return bizFieldConfigService.getByBizConfigId(bizConfigId);
	}

	/**
	 * 跳转编辑、新增页面
	 * 
	 * @param bizConfigId
	 *            业务配置id
	 * @param id
	 *            业务字段配置id
	 * @param mav
	 * @return
	 */
	@GetMapping("/toEditPage")
	public ModelAndView toEditPage(String bizConfigId, String id, ModelAndView mav) {
		mav.setViewName("/config/page/biz_field_config_add");
		mav.addObject("bizConfigId", bizConfigId);
		if (id != null) {
			mav.addObject("item", bizFieldConfigService.getById(id));
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
	public Map<String, Object> save(BizFieldConfigModel bizFieldConfig) {
		return SimpleRestResponse.create("id", bizFieldConfigService.save(bizFieldConfig));
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@PostMapping("/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable String id) {
		bizFieldConfigService.del(id);
		return SimpleRestResponse.create("id", id);
	}

}
