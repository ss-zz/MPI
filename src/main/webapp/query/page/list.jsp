<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/query/js/list.js"></script>
</head>
<body>
<!-- 列出选定居民信息 -->
<table class="myTable">
	<caption>居民信息摘要</caption>
	<thead>
		<tr>
			<th>姓名</th>
			<th>性别</th>
			<th>出生日期</th>
			<th>身份证号</th>
			<th>电话号码</th>
			<th>数据来源</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${person.name }</td>
			<td>${person.sex }</td>
			<td>${person.birthDate }</td>
			<td>${person.idCardNum }</td>
			<td>${person.phoneOne }</td>
			<td>${person.domainDesc }</td>
		</tr>
	</tbody>
</table>
<div>
	<input type="hidden" id="personId" value="${person.personId }" />
	<input type="hidden" id="fromIndexId" value="${indexId }" />
</div>
<!-- 表格 -->
<table 	id="listTable"
		title="索引列表"
		iconCls="icon-edit"
		idField="INDEX_ID"
		url="${pageContext.request.contextPath}/query/query.ac?method=queryIdx&fromIndexId=${indexId }" >
	<thead>
		<tr align="center">
			<th field="ck" width="20" checkbox="true" width="20"></th>
			<th field="NAME" width="100">索引姓名</th>
			<th field="sexName" width="100">索引性别</th>
			<th field="BIRTH_DATE" width="100">索引出生日期</th>
			<th field="ID_CARD_NUM" width="200">索引身份证号</th>
			<th field="PHONE_ONE" width="100">索引电话号码</th>
			<th field="PERSON_COUNT" width="100">关联居民数量</th>
		</tr>
	</thead>
</table>
<!-- 搜索工具条 -->
<div id="listTable_tb" style="padding:5px;height:auto">
	<div>
		索引姓名:<input type="text" class="combo-text" id="search_personName">
		<%-- 索引性别:<input class="easyui-combobox" id="search_personSex" url="${pageContext.request.contextPath}/manual/manual.ac?method=listCode&codeName=SexCode" valueField="codeId" textField="codeName" panelHeight="auto" /> --%>
		索引生日:<input class="easyui-datetimebox" id="search_personBirthdate">
		索引身份证:<input type="text" class="combo-text" id="search_idCardNum">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchListTable();">搜索</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchReset();">重置</a>
	</div>
	<div>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="splitToSelectIndex();">拆分至选定索引</a>
	</div>
</div>
</body>
</html>
