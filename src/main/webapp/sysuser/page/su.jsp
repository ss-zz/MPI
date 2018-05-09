<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/sysuser/js/su.js"></script>
</head>
<body>
<!-- 表格 -->
<table id="listTable"
		title="用户列表"
		idField="USER_ID"
		url="${pageContext.request.contextPath}/sysuser/su.ac?method=query" >
	<thead>
		<tr align="center">
			<th field="ck" width="20" checkbox="true" width="20"></th>
			<th field="NAME"  width="100">姓名</th>
			<th field="USER_NAME"  width="100">用户名</th>
			<th field="EMAIL"  width="200">电子邮件</th>
			<th field="ROLE_NAME"  width="100">系统角色</th>
		</tr>
	</thead>
</table>

<!-- 添加 -->
<div id="add" icon="icon-save">
	<span id="add_message" style="color: red;"></span>
	<div class="my-form" id="table_add" onkeydown="if(event.keyCode==13){addData();}">
		<div>
			<label>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
			<input type="text" class="easyui-validatebox" id="add_name" maxlength="50" required="true"></input>
		</div>
		<div>
			<label>用&nbsp;户&nbsp;名：</label>
			<input type="text" class="easyui-validatebox" id="add_userName" maxlength="50" required="true" validType="loginName['${pageContext.request.contextPath}/sysuser/su.ac?method=test']"></input>
		</div>
		<div>
			<label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
			<input type=password class="easyui-validatebox" id="add_password" maxlength="20" required="true"></input>
		</div>
		<div>
			<label>重复密码：</label>
			<input type="password" class="easyui-validatebox" id="readd_password" maxlength="20" required="true" validType="reapet['#add_password','两次输入不一致']"></input>
		</div>
		<div>
			<label>电子邮件：</label>
			<input type="text" class="easyui-validatebox" id="add_email" maxlength="50" required="true" validType="email"></input>
		</div>
		<div>
			<label>角色：</label>
			<select class="easyui-validatebox" id="add_role" required="true">
				<option value="">请选择</option>
			<c:forEach items="${roles }" var="role">
				<option value="${role.sysRoleId }">${role.roleName }</option>
			</c:forEach>
			</select>
		</div>
		<div>
			<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="addData();">提交</a>
		</div>
	</div>
</div>

<!-- 编辑 -->
<div id="edit" icon="icon-save">
	<span id="edit_message" style="color: red;"></span>
	<div class="my-form" id="table_edit" onkeydown="if(event.keyCode==13){editData();}">
		<div>
			<label>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
			<input type="text" class="easyui-validatebox" id="edit_name"  maxlength="50" required="true"></input>
			<input type="hidden" id="edit_userId" >
		</div>
		<div>
			<label>用&nbsp;户&nbsp;名：</label>
			<span id="edit_userName_show"></span>
			<input type="hidden" id="edit_userName" >
		</div>
		<div>
			<label>电子邮件：</label>
			<input type="text" class="easyui-validatebox" id="edit_email" maxlength="50" required="true" validType="email"></input>
		</div>
		<div>
			<label>角色：</label>
			<select class="easyui-validatebox" id="edit_role" required="true">
				<option value="">请选择</option>
				<c:forEach items="${roles}" var="role">
					<option value="${role.sysRoleId}">${role.roleName }</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="editData();">提交</a>
		</div>
	</div>
</div>
</body>
</html>
