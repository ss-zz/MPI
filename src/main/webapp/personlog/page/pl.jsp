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
<script type="text/javascript" src="${pageContext.request.contextPath}/personlog/js/pl.js"></script>
<style type="text/css">
.mytable {
	border-collapse:collapse;
	border: 1px #CCCCCC dotted ;
	margin: 0px;
}
.mytable tr td {
	border: 1px #CCCCCC dotted ;
	margin: 0px;
}
.tree-folder{
	background:url('${pageContext.request.contextPath}/images/person.png') no-repeat !important;
}
.tree-folder-open{
	background:url('${pageContext.request.contextPath}/images/person.png') no-repeat !important;
}
</style>
</head>
<body>
<!-- 表格 -->
<table 	class="easyui-treegrid"
		id="listTable" 		
		border="0"
		cellspacing="0"
		cellpadding="0"		
		></table>

<!-- 搜索工具条 -->
<div id="listTable_tb" style="padding:5px;height:auto">
	<div>
		居民姓名:<input type="text" class="combo-text" id="search_personName">
		居民性别:<input class="easyui-combobox" id="search_personSex" url="${pageContext.request.contextPath}/manual/manual.ac?method=listCode&codeName=SexCode" valueField="codeId" textField="codeName" panelHeight="auto" />
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchListTable();">搜索</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchReset();">重置</a>
	</div>
</div>
</body>
</html>
