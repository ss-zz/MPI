<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/manual/js/add.js"></script>
</head>
<body>
<!-- 表格 -->
<table id="listTable"
		title="需手工合并居民信息"  
		idField="MAN_OP_ID" 
		url="${pageContext.request.contextPath}/manual/manual.ac?method=query" >
	<thead>
		<tr align="center">
			<th field="MAN_OP_STATUS"  width="50" formatter="buildTypeStr">操作状态</th>
			<th field="MAN_OP_TIME"  width="150">操作时间</th>
			<th field="DOMAIN_DESC"  width="100">数据来源</th>
			<th field="NAME_CN"  width="100" formatter="buildPersonView">居民姓名</th>
			<th field="GENDER_CD" width="100">居民性别</th>
			<th field="BIRTH_DATE" width="100">居民出生日期</th>
			<th field="ID_NO" width="200">居民身份证号</th>
			<th field="PERSON_TEL_NO"  width="100">居民电话号码</th>
			<th field="FIELD_PK"  width="100" formatter="buildMatchUrl">操作</th>
		</tr>
	</thead>
</table>

<!-- 搜索工具条 -->
<div id="listTable_tb" style="padding:5px;height:auto">
	<div>
		操作状态:<select id="search_opstatus" >
			<option value="">--请选择--</option>
			<option value="0">未操作</option>
			<option value="1">已操作</option>
		</select>
		居民姓名:<input type="text" class="combo-text" id="search_personName">
		居民性别:<input class="easyui-combobox" id="search_personSex" 
		url="${pageContext.request.contextPath}/manual/manual.ac?method=listCode&codeName=SexCode" 
		valueField="codeId" textField="codeName" panelHeight="auto" />
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchListTable();">搜索</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchReset();">重置</a>
	</div>
</div>
</body>
</html>
