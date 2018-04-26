package com.sinosoft.index.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.index.model.BizFieldConfigModel;
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
	public List<BizFieldConfigModel> getAll() {
		return bizFieldConfigService.getAll();
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@PostMapping("/save")
	public void save(BizFieldConfigModel bizFieldConfig) {
		bizFieldConfigService.save(bizFieldConfig);
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@PostMapping("/del/{id}")
	public void del(@PathVariable String id) {
		bizFieldConfigService.del(id);
	}

}
