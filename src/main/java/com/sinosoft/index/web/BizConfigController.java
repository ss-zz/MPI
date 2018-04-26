package com.sinosoft.index.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.index.model.BizConfigModel;
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
	public List<BizConfigModel> getAll() {
		return bizConfigService.getAll();
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@PostMapping("/save")
	public void save(BizConfigModel bizConfig) {
		bizConfigService.save(bizConfig);
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@PostMapping("/del/{id}")
	public void del(@PathVariable String id) {
		bizConfigService.del(id);
	}

}
