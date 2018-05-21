<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script>
var domainId='${domainId}';
var uniqueSign='${uniqueSign}';
var SELECT_JSON = ${selectJson};
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/srclevel/js/srclevel_add.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/srclevel/js/srclevel.js"></script>

</head>
<body>
<div class="easyui-panel" id="table_add" onkeydown="if(event.keyCode==13){editData();}"
	style="padding: 10px;">
	<h1>【${domain.domainDesc }】字段数据源级别管理</h1>
	<select id="add_domainSelect" disabled style="display: none;"></select>
	<label for="add_fieldSelect">字段:</label>
	<select id="add_fieldSelect">
	</select>
	&nbsp; &nbsp; &nbsp;<label>字段数据源级别：</label>
	<select id="add_fieldlevel" length="50">
		<option value="">--请选择--</option>
		<option value="0">0级</option>
		<option value="1">1级</option>
		<option value="2">2级</option>
		<option value="3">3级</option>
		<option value="4">4级</option>
		<option value="5">5级</option>
	</select>
	<a onclick="saveMatchCfg()" class="easyui-linkbutton" iconCls="icon-save">添加</a>
	<a onclick="resetAllData()" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
</div>
<br/>
<!-- 表格 -->
<table id="listTable"
		title="已配置的字段数据源级别"
		idField="id" 
		url="${pageContext.request.contextPath}/domainsrclevel/srclevel.ac?method=queryByID" >
	<thead>
		<tr align="center">
			<th field="ck" width="20" checkbox="true" width="20"></th>
			<th field="fieldName"  width="100">字段名称</th>
			<th field="fieldDesc"  width="100">字段描述</th>
			<th field="fieldLevel"  width="100">字段数据源级别</th>
		</tr>
	</thead>
</table>

</body>
</html>
