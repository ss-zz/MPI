<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/page/master.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/config/js/common_field_config.js"></script>
</head>
<body>
	<!-- 表格 -->
	<table id="listTable" 
		title="通用字段列表" 
		url="${pageContext.request.contextPath}/mgr/bizcommonfieldconfig/all">
		<thead>
			<tr align="center">
				<th field="key" width=100>唯一标识</th>
				<th field="name" width=100>字段名</th>
				<th field="comment" width=100>备注</th>
				<th field="algorithm" width=100>默认算法</th>
				<th field="rank" width=100>顺序</th>
				<th field="weight" width=100>默认权重</th>
				<th field="id" width="150" formatter="buildOptLink">操作</th>
			</tr>
		</thead>
	</table>
</body>
</html>
