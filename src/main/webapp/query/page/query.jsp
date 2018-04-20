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
<script type="text/javascript" src="${pageContext.request.contextPath}/query/js/query.js"></script>
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
<table class="easyui-treegrid"
		id="listTable"
		border="0"
		fitColumns="true"
		cellspacing="0"
		cellpadding="0"
		></table>

<!-- 搜索工具条 -->
<div id="listTable_tb" style="padding:5px;height:auto">
	<div>
	  <tr>
		<td>索引姓名:</td>
		<td><input type="text" class="combo-text" id="search_personName"></td>
		<td>身份证号码:</td>
		<td><input type="text" class="combo-text" id="search_personIdno"></td>
	 </tr>
	 <tr>
	 <td>出生日期:</td>
		<td><input class="easyui-datebox" id="search_personBirthdate" readonly="true"   style="width:150px"></td>
		</tr>
	<tr>
	 <td>合并状态:</td>
		<td>	<select id="search_mergeStatus" class="easyui-combobox" name="dept" style="width:166px;" editable="false">
					<option value="-1">请选择</option>
					<option value="0">未合并</option>
					<option value="1">已合并</option>
				</select>
		</td>
	 
		</tr>
	</div>
			<div>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="mergeIndex()">合并主索引</a> 
						<a href="#" class="easyui-linkbutton" style="float: right; margin-right: 40px;" iconCls="icon-search" onclick="searchReset();">重置</a>
						<a href="#" class="easyui-linkbutton" style="float: right; margin-right: 10px;" iconCls="icon-search" onclick="searchListTable();">搜索</a>
						
			</div>
</div>
	<div id="dialog" class="easyui-dialog" closed="true"></div>
	<div id="dialog_split" class="easyui-dialog" closed="true"></div>
</body>
</html>
