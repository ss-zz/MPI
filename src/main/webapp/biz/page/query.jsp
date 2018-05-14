<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/biz/js/query.js"></script>
<style type="text/css">
.tree-folder{
	background:url('${pageContext.request.contextPath}/images/index_0.png') no-repeat !important;
}
.tree-folder-open{
	background:url('${pageContext.request.contextPath}/images/index_1.png') no-repeat !important;
}
.tree-file{
	background:url('${pageContext.request.contextPath}/images/person.png') no-repeat !important;
}
</style>
</head>
<body>

	<!-- 表格 -->
	<table id="listTable"></table>
	
	<!-- 搜索工具条 -->
	<div id="listTable_tb">
		<table style="width: 100%;">
			<tr>
				<td>业务来源: <input class="combo-text" id="search_system_id" style="width:150px"></td>
				<td>创建日期: <input class="easyui-datebox" id="search_create_date" style="width:150px"></td>
				<td>住院流水号: <input class="combo-text" id="search_inpatient_no" style="width:150px"></td>
				<td>门诊流水号: <input class="combo-text" id="search_clinic_serial_no" style="width:150px"></td>
			<tr>
				<td>
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchListTable();">搜索</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchReset();">重置</a>
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
		</table>
	</div>
	<div id="dialog" class="easyui-dialog" closed="true"></div>
	<div id="dialog_split" class="easyui-dialog" closed="true"></div>
</body>
</html>
