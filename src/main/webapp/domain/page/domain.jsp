<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/domain/js/domain.js"></script>
</head>
<body>
<!-- 表格 -->
<table id="listTable"
		title="业务系统列表"
		idField="domainId"
		url="${pageContext.request.contextPath}/domain/domain.ac?method=query" >
	<thead>
		<tr align="center">
			<th field="uniqueSign" width="20">唯一标识</th>
			<th field="domainDesc" width="50">描述</th>	
			<th field="domainLevel" width="20">数据源级别</th>
			<th field="HD1" width="50" formatter="buildOptLink">操作</th>	
		</tr>
	</thead>
</table>

<!-- 添加 -->
<div id="add" icon="icon-save">
	<span id="add_message" style="color: red;"></span>
	<div class="my-form" id="table_add" onkeydown="if(event.keyCode==13){addData();}">
		<div>
			<label>唯一标识：</label>
			<input type="text" class="easyui-validatebox" id="add_uniqueSign" maxlength="50" required="true" validType="uniqueSign['${pageContext.request.contextPath}/domain/domain.ac?method=test']"></input>
		</div>
		<div>
			<label>描述：</label>
			<input type="text" class="easyui-validatebox" id="add_domainDesc" maxlength="50" required="true"></input>
		</div>
		<div>
			<label>数据源级别：</label>
			<input type="text" class="easyui-validatebox" id="add_domainLevel" required="true" maxlength="2" validType="isInteger"></input>
		</div>
		<div class="my-form-btns">
			<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="addData();">提交</a>
		</div>
	</div>
</div>

<!-- 编辑 -->
<div id="edit" icon="icon-save">
	<span id="edit_message" style="color: red;"></span>
	<div class="my-form" id="table_edit" onkeydown="if(event.keyCode==13){editData();}">
		<div>
			<label>唯一标识：</label>
			<input type="hidden" id="edit_domainId" />
			<input type="text" class="easyui-validatebox" id="edit_uniqueSign" maxlength="50" required="true" validType="uniqueSign['${pageContext.request.contextPath}/domain/domain.ac?method=test','#edit_domainId']"></input>
		</div>
		<div>
			<label>描述：</label>
			<input type="text" class="easyui-validatebox" id="edit_domainDesc" maxlength="50" required="true"></input>
		</div>
		<div>
			<label>数据源级别：</label>
			<input type="text" class="easyui-validatebox" id="edit_domainLevel" required="true" maxlength="2" validType="isInteger"></input>
		</div>
		<div class="my-form-btns">
			<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="editData();">提交</a>
		</div>
	</div>
</div>
</body>
</html>
