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
<script type="text/javascript" src="${pageContext.request.contextPath}/manual/js/remove.js"></script>
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

</style>
</head>
<body>
<!-- 表格 -->
<table 	id="listTable"
		title="索引列表"  
		border="0"
		cellspacing="0"
		cellpadding="0"
		iconCls="icon-edit" 
		width="98%" 
		idField="INDEX_ID" 
		remoteSort="false" 
		singleSelect="false" 
		showFooter="false"
		striped="true"
		url="${pageContext.request.contextPath}/manual/manual.ac?method=list" >
	<thead>
		<tr align="center">
			<th field="NAME" width="100">索引姓名</th>
			<th field="sexName" width="100">索引性别</th>
			<th field="BIRTH_DATE" width="100">索引出生日期</th>
			<th field="ID_CARD_NUM" width="200">索引身份证号</th>
			<th field="PHONE_ONE" width="100">索引电话号码</th>
			<th field="PERSON_COUNT" width="100">关联居民数量</th>
			<th field="INDEX_ID" width="100" formatter="buildMatchUrl">操作</th>
		</tr>
	</thead>
</table>
<!-- 搜索工具条 -->
<div id="listTable_tb" style="padding:5px;height:auto">
	<div>
		居民姓名:<input type="text" class="combo-text" id="search_personName">
		居民性别:<input class="easyui-combobox" id="search_personSex" url="${pageContext.request.contextPath}/manual/manual.ac?method=listCode&codeName=SexCode" valueField="codeId" textField="codeName" panelHeight="auto" />
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchListTable();">搜索</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchReset();">重置</a>
	</div>
</div>
<!-- 索引匹配列表 --> 

<div id="window_view" title="匹配情况" iconCls="icon-detail">
	<div class="easyui-layout" fit="true">
		<div id="match_detail_view" region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">		
			<input type="hidden" id="selected_indexId" />
			  <table class="mytable">
			  <tr>
				<td width="100">索引姓名</td>
				<td width="100">索引性别</td>
				<td width="100">索引出生日期</td>
				<td width="200">索引身份证号</td>
				<td width="100">索引电话</td>
			  </tr>
			  <tr>
				<td id="index_name_show"></td>
				<td id="index_sex_show"></td>
				<td id="index_birthday_show"></td>
				<td id="index_idcard_show"></td>
				<td id="index_phone_show"></td>
			  </tr>
			  </table>			
			<table 	id="detailTable"
					title="关联居民列表"  
					border="0"
					cellspacing="0"
					cellpadding="0"
					iconCls="icon-edit" 
					width="98%" 
					idField="IDENTIFIER_ID" 
					remoteSort="false" 
					singleSelect="false" 
					showFooter="false"
					striped="true"
					url="${pageContext.request.contextPath}/manual/manual.ac?method=listPerson" >
				<thead>
					<tr align="center">
						<th field="ck" width="20" checkbox="true" width="20"></th>
						<th field="NAME"  width="100">居民姓名</th>
						<th field="sexName" width="100">居民性别</th>
						<th field="BIRTH_DATE" width="100">居民出生日期</th>
						<th field="ID_CARD_NUM" width="200">居民身份证号</th>
						<th field="PHONE_ONE"  width="100">居民电话号码</th>				
					</tr>
				</thead>
			</table>	
			<!-- 定义toobar -->
			<div id="detailTable_toolbar">  
			    <a href="#" class="easyui-linkbutton" id="toobar_split_btn" iconCls="icon-remove" plain="true" onclick="splitPerson();">拆分</a>  
			</div>  		
		</div>
		<div region="south" border="false" style="text-align: right; padding: 5px 0;">
			<a class="easyui-linkbutton" id="window_view_close_btn" iconCls="icon-ok" href="javascript:void(0)" onclick="closeWindow_view();">关闭</a> 
		</div>
	</div>
</div>
</body>
</html>
