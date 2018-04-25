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
		title="系统用户列表"
		border="0"
		cellspacing="0"
		cellpadding="0"
		idField="USER_ID"
		remoteSort="false"
		singleSelect="false"
		showFooter="true"
		striped="true"
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
<div id="add" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="add_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_add" onkeydown="if(event.keyCode==13){addData();}">
        <ul>
			<li>
				<label>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
				<input type="text" class="easyui-validatebox" id="add_name" maxlength="50" required="true"></input>
			</li>
			<li>
				<label>用&nbsp;户&nbsp;名：</label>
				<input type="text" class="easyui-validatebox" id="add_userName" maxlength="50" required="true" validType="loginName['${pageContext.request.contextPath}/sysuser/su.ac?method=test']"></input>
			</li>
			<li>
				<label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
				<input type=password class="easyui-validatebox" id="add_password" maxlength="20" required="true"></input>
			</li>
			<li>
				<label>重复密码：</label>
				<input type="password" class="easyui-validatebox" id="readd_password" maxlength="20" required="true" validType="reapet['#add_password','两次输入不一致']"></input>
			</li>
			<li>
				<label>电子邮件：</label>
				<input type="text" class="easyui-validatebox" id="add_email" maxlength="50" required="true" validType="email"></input>
			</li>
			<li>
				<label>系统角色：</label>
				<select class="easyui-validatebox" id="add_role" required="true">
					<option value="">请选择</option>
				<c:forEach items="${roles }" var="role">
					<option value="${role.sysRoleId }">${role.roleName }</option>
				</c:forEach>
				</select>
			</li>
			<li>
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="addData();">提交</a>
			</li>
		</ul>
	</div>
</div>

<!-- 编辑 -->
<div id="edit" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="edit_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_edit" onkeydown="if(event.keyCode==13){editData();}">
        <ul>
			<li>
				<label>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
				<input type="text" class="easyui-validatebox" id="edit_name"  maxlength="50" required="true"></input>
				<input type="hidden" id="edit_userId" >
			</li>
			<li>
				<label>用&nbsp;户&nbsp;名：</label>
				<span id="edit_userName_show"></span>
				<input type="hidden" id="edit_userName" >
			</li>
			<li>
				<label>电子邮件：</label>
				<input type="text" class="easyui-validatebox" id="edit_email" maxlength="50" required="true" validType="email"></input>
			</li>
			<li>
				<label>系统角色：</label>
				<select class="easyui-validatebox" id="edit_role" required="true">
					<option value="">请选择</option>
				<c:forEach items="${roles}" var="role">
					<option value="${role.sysRoleId}">${role.roleName }</option>
				</c:forEach>
				</select>
			</li>
			<li>
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="editData();">提交</a>
			</li>
		</ul>
	</div>
</div>
</body>
</html>
