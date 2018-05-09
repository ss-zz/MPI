<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/query/js/query.js"></script>
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
				<td>索引姓名: <input type="text" class="combo-text" id="search_personName"></td>
				<td>身份证号码: <input type="text" class="combo-text" id="search_personIdno"></td>
				<td>出生日期: <input class="easyui-datebox" id="search_personBirthdate" style="width:150px"></td>
			</tr>
			<tr>
				<td>合并状态:
					<select id="search_mergeStatus" class="easyui-combobox" name="dept" style="width:166px;" editable="false">
						<option value="-1">请选择</option>
						<option value="0">未合并</option>
						<option value="1">已合并</option>
					</select>
				</td>
				<td>
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchListTable();">搜索</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchReset();">重置</a>
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="mergeIndex()">合并主索引</a> 
				</td>
			</tr>
		</table>
	</div>
	<div id="dialog" class="easyui-dialog" closed="true"></div>
	<div id="dialog_split" class="easyui-dialog" closed="true"></div>
</body>
</html>
