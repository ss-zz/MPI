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

import com.sinosoft.index.entity.MpiConfigField;
import com.sinosoft.index.model.SimpleRestResponse;
import com.sinosoft.index.service.MpiConfigFieldService;

/**
 * 主索引字段配置控制器
 */
@Controller
@RequestMapping("/mgr/fieldconfig")
public class MpiConfigFieldController {

	@Autowired
	MpiConfigFieldService mpiConfigFieldService;

	/**
	 * 获取所有列表数据
	 * 
	 * @return
	 */
	@GetMapping("/all")
	@ResponseBody
	public List<MpiConfigField> getAll() {
		return mpiConfigFieldService.getAll();
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
		mav.setViewName("/cfg/page/field_add");
		if (id != null) {
			mav.addObject("item", mpiConfigFieldService.getById(id));
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
	public Map<String, Object> save(MpiConfigField mpiConfigField) {
		return SimpleRestResponse.create("id", mpiConfigFieldService.save(mpiConfigField));
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@PostMapping("/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable String id) {
		mpiConfigFieldService.del(id);
		return SimpleRestResponse.create("id", id);
	}

}
