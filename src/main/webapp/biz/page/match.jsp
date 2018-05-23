<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/biz/js/match.js"></script>
</head>
<body>

<a href="#" onclick="openCurrentPage();" class="easyui-linkbutton" data-options="selected:true">查看当前匹配规则</a>
<br/>
<br/>
<!-- 表格 -->
<table 	id="listTable"
		title="匹配规则配置列表"
		idField="configId"
		url="${pageContext.request.contextPath}/matchCfgbiz/match" >
	<thead>
		<tr align="center">
			<th field="configDesc"  width="400">描述</th>
			<th field="createDate"  width="100" formatter="initDate">创建日期</th>
			<th field="state"  width="50" formatter="buildState">状态</th>
			<th field="configId"  width="150" formatter="buildOptLink">操作</th>
		</tr>
	</thead>
</table>
</body>
</html>
