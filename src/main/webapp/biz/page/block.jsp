<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/biz/js/block.js"></script>
</head>
<body>

<a href="#" onclick="openCurrentPage();" class="easyui-linkbutton" data-options="selected:true">查看当前初筛配置</a>
<br/>
<br/>
<!-- 表格 -->
<table 	id="listTable"
		title="配置列表"
		idField="bolckId" 
		url="${pageContext.request.contextPath}/blockCfgbiz/query" >
	<thead>
		<tr align="center">
			<th field="blockDesc"  width="400">配置描述</th>
			<th field="createDate"  width="100">创建日期</th>
			<th field="state"  width="50" formatter="buildState">状态</th>
			<th field="bolckId"  width="150" formatter="buildOptLink">操作</th>
		</tr>
	</thead>
</table>
</body>
</html>
