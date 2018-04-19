<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/page/master.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sysrole/js/role.js"></script>
</head>
<body>
<!-- 表格 -->
<table 	id="listTable"
		title="系统角色列表"  
		border="0"
		cellspacing="0"
		cellpadding="0"
		iconCls="icon-edit" 
		width="98%" 
		idField="sysRoleId" 
		remoteSort="false" 
		singleSelect="false" 
		showFooter="false"
		striped="true"
		url="${pageContext.request.contextPath}/role/role.ac?method=query" >
	<thead>
		<tr align="center">
			<th field="ck" width="20" checkbox="true" width="20"></th>
			<th field="roleName" width="100">姓名</th>
			<th field="sysRoleId" width="100" formatter="buildViewUserLink">用户管理</th>
		</tr>
	</thead>
</table>
<!-- 添加角色 -->
<!-- 添加 -->
<div id="add" icon="icon-save" style="padding: 5px; width: 400px; height: 150px;">
	<h5 id="add_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_add" onkeydown="if(event.keyCode==13){addData();}">
        <ul>
			<li>
				<label>角色名称：</label>
				<input type="text" class="easyui-validatebox" id="add_roleName" maxlength="50" required="true" validType="roleName['${pageContext.request.contextPath}/role/role.ac?method=test']" />
			</li>
			<li>
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="addData();">提交</a>
			</li>
		</ul>
	</div>
</div>
<!-- 修改角色 -->
<div id="edit" icon="icon-save"
	style="padding: 5px; width: 400px; height: 150px;">
	<h5 id="edit_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_edit" onkeydown="if(event.keyCode==13){editData();}">
        <ul>
			<li>
				<label>角色名称：</label>
				<input type="text" class="easyui-validatebox" id="edit_roleName"  maxlength="50" required="true"></input>
				<input type="hidden" id="edit_roleId" >
			</li>
			<li>
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="editData();">提交</a>
			</li>
		</ul>
	</div>
</div>


<!-- 角色用户显示窗口 -->
<div id="window_view_user" title="角色用户" iconCls="icon-detail">
	<div class="easyui-layout" fit="true">
		<div id="role_user_view" region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
			<input type="hidden" id="current_sysrole_id">				
			<table 	id="userListTable"
					title="角色用户列表"  
					border="0"
					cellspacing="0"
					cellpadding="0"
					iconCls="icon-detail" 
					width="98%" 
					idField="USER_ID" 
					remoteSort="false" 
					singleSelect="false" 
					showFooter="false"
					striped="true"
					url="${pageContext.request.contextPath}/role/role.ac?method=listUser" >
				<thead>
					<tr align="center">
						<th field="NAME"  width="100">姓名</th>
						<th field="USER_NAME"  width="100">用户名</th>
						<th field="EMAIL"  width="100">电子邮件</th>
						<th field="ROLE_NAME"  width="100">系统角色</th>
					</tr>
				</thead>
			</table>
		</div>
		<div region="south" border="false" style="text-align: right; padding: 5px 0;">
			<a class="easyui-linkbutton" id="window_view_close_btn" iconCls="icon-ok" href="#" onclick="closeWindow_view();">关闭</a> 
		</div>
	</div>
</div>
</body>
</html>
