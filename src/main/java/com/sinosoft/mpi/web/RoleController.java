package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.service.ISysRoleService;
import com.sinosoft.mpi.util.PageInfo;

/**   
*    
* @Description  系统角色控制   
* 
* 
*
* 
* @Package com.sinosoft.mpi.web 
* @author Bysun
* @version v1.0,2012-3-19
* @see	
* @since	（可选）	
*   
*/ 
@Controller
@RequestMapping("/role/role.ac")
public class RoleController {
	private Logger logger = Logger.getLogger(RoleController.class);	
	
	@Resource
	private ISysRoleService sysRoleService;
	
	/**
	 * 系统角色列表 
	 */
	@RequestMapping(params = "method=query")
	public String listRole(SysRole role,PageInfo page,HttpServletResponse response) throws IOException{
		List<SysRole> list = sysRoleService.queryForPage(role, page);
		JSONObject datas = new JSONObject();
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
		return null;
	}
	
	/**
	 * 列出角色下的用户
	 */
	@RequestMapping(params = "method=listUser")
	public String listRoleUser(SysRole role,PageInfo page,HttpServletResponse response) throws IOException{
		List<Map<String,Object>> list = sysRoleService.queryRoleUser(role,page);
		JSONObject datas = new JSONObject();
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());		
		return null;
	}
	
	/**
	 * 检查角色名是否可用
	 * @throws IOException 
	 */
	@RequestMapping(params = "method=test")
	public String testRoleName(SysRole role,HttpServletResponse response) throws IOException{
		boolean flag = sysRoleService.testRoleName(role);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		if(flag){
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
		return null;
	}
	
	/**
	 * 添加系统角色
	 * @throws IOException 
	 */
	@RequestMapping(params = "method=add")
	public String addRole(SysRole role,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			sysRoleService.save(role);
		} catch (ValidationException e) {
			response.getWriter().print(e.getMessage());
		} catch (Throwable e){
			logger.error("添加系统角色时出现错误!", e);
			response.getWriter().print("添加系统角色时出现错误!");
		}
		return null;
	}
	
	/**
	 * 根据角色id取得角色数据
	 */
	@RequestMapping(params = "method=load")
	public String loadRole(String sysRoleId,HttpServletResponse response) throws IOException{
		SysRole t = sysRoleService.getObject(sysRoleId);
		JSONObject datas = JSONObject.fromObject(t);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
		return null;
	}
	
	/**
	 * 修改系统角色
	 */
	@RequestMapping(params = "method=edit")
	public String editRole(SysRole role,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			sysRoleService.update(role);
		}  catch (ValidationException e) {
			response.getWriter().print(e.getMessage());
		} catch (Throwable e) {
			logger.error("修改系统用户时出现错误!", e);
			response.getWriter().print("修改系统用户时出现错误!");
		}
		return null;
	}
	
	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}
}
