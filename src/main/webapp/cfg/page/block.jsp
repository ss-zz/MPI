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
<script type="text/javascript" src="${pageContext.request.contextPath}/cfg/js/block.js"></script>
</head>
<body>
<!-- 表格 -->
<table 	id="listTable"
		title="初筛规则配置列表"  
		border="0"
		cellspacing="0"
		cellpadding="0"
		iconCls="icon-edit" 
		width="98%" 
		idField="bolckId" 
		remoteSort="false" 
		singleSelect="false" 
		showFooter="false"
		striped="true"
		url="${pageContext.request.contextPath}/cfg/block.ac" >
	<thead>
		<tr align="center">
			<th field="blockDesc"  width="400">描述</th>
			<th field="createDate"  width="100">创建日期</th>
			<th field="state"  width="50" formatter="buildState">状态</th>
			<th field="bolckId"  width="150" formatter="buildOptLink">操作</th>
		</tr>
	</thead>
</table>
</body>
</html>
