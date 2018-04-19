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
<script type="text/javascript" src="${pageContext.request.contextPath}/merge/js/merge.js"></script>
</head>
<body>
<div id="showSummary">
<table>
	<tr>
		<th></th>
		<th>姓名</th>
		<th>身份证号</th>
		<th>数据来源</th>
		<th></th>
		<th></th>
	</tr>
	<tr id="surviveSummary">
		<th>目标居民</th>
		<td></td>
		<td></td>
		<td></td>
		<td>
			<input type="hidden" id="survivePersonId" />
			<input type="hidden" id="surviveDomainId" />
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="reSelectSurvivePerson();">重选</a>
		</td>
		<td rowspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="toMergePage();">确定合并</a></td>
	</tr>
	<tr id="retiredSummary">
		<th>被合并居民</th>
		<td></td>
		<td></td>
		<td></td>
		<td>
			<input type="hidden" id="retiredPersonId" />
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="reSelectRetiredPerson();">重选</a>
		</td>
	</tr>
</table>
<hr />
</div>
<div id="table_accordion" class="easyui-accordion" style="width:auto;height:auto;">
	<div id="survivePanel" selected="true" style="overflow:auto;padding:0px;" title="选择目标居民" iconCls="icon-save" >
		<!-- 表格 -->
		<table 	id="surviveTable"
				border="0"
				cellspacing="0"
				cellpadding="0"
				iconCls="icon-save" 
				width="98%" 
				idField="PERSON_ID" 
				remoteSort="false" 
				singleSelect="false" 
				showFooter="false"
				striped="true"
				url="${pageContext.request.contextPath}/merge/merge.ac" >
			<thead>
				<tr align="center">
					<th field="ck" width="20" checkbox="true" width="20"></th>
					<th field="NAME_CN" width="100" formatter="buildPersonView">居民姓名</th>
					<th field="ID_NO" width="150">身份证号</th>
					<th field="DOMAIN_DESC" width="200">数据来源</th>
				</tr>
			</thead>
		</table>
		
		<!-- 搜索工具条 -->
		<div id="surviveTable_tb" style="height: auto;text-align: left;">
			<div>
				&nbsp;&nbsp;数据来源:<input class="easyui-combobox" style="width:150px" id="search_domain" url="${pageContext.request.contextPath}/indexlog/il.ac?method=listDomain" valueField="DOMAIN_ID" textField="DOMAIN_DESC" panelHeight="auto" />
				&nbsp;&nbsp;姓名:<input type="text" class="combo-text" id="search_personName" style="width:150px">
				&nbsp;&nbsp;身份证号:<input type="text" class="combo-text" id="search_personIdcard" style="width:150px">
				&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchSurviveTable();">搜索</a>
				&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetSurviveTable();">重置</a>
			</div>
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="selectSurvivePerson();">选定目标用户</a>
			</div>
		</div>
	</div>	
	<div id="retiredPanel" style="overflow:auto;padding:0px;" title="选择被合并居民" iconCls="icon-save">
		<!-- 表格 -->
		<table 	id="retiredTable"
				border="0"
				cellspacing="0"
				cellpadding="0"
				iconCls="icon-save" 
				width="98%" 
				idField="PERSON_ID" 
				remoteSort="false" 
				singleSelect="true" 
				showFooter="false"
				striped="true"
				url="${pageContext.request.contextPath}/merge/merge.ac" >
			<thead>
				<tr align="center">
					<th field="ck" width="20" checkbox="true" width="20"></th>
					<th field="NAME_CN" width="100" formatter="buildPersonView">居民姓名</th>
					<th field="ID_NO" width="150">身份证号</th>
					<th field="DOMAIN_DESC" width="80">数据来源</th>
				</tr>
			</thead>
		</table>
		<!-- 搜索工具条 -->
		<div id="retiredTable_tb" style="height: auto;text-align: left;">
			<div>
				&nbsp;&nbsp;姓名:<input type="text" class="combo-text" id="search_retired_personName" style="width:150px">
				&nbsp;&nbsp;身份证号:<input type="text" class="combo-text" id="search_retired_personIdcard" style="width:150px">
				&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchRetiredTable();">搜索</a>
				&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetRetiredTable();">重置</a>
			</div>
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="selectRetiredPerson();">选择被合并居民</a>
			</div>
		</div>
	</div>
</div>  
</body>
</html>
