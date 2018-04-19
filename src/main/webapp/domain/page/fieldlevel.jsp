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
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/domain/js/fieldlevel.js"></script> --%>
</head>

<body>
<!-- 表格 -->
<%-- <table 	id="listTable"
		title="域字段数据源级别"  
		border="0"
		cellspacing="0"
		cellpadding="0"
		iconCls="icon-edit" 
		width="98%" 
		idField="ID" 
		remoteSort="false" 
		singleSelect="false" 
		showFooter="false"
		striped="true"
		url="${pageContext.request.contextPath}/domainsrclevel/srclevel.ac?method=toView&domainid=" > --%>
<table id="tt" class="easyui-datagrid" style="width:650px;height:400px;">  
	<thead>
		<tr align="center">
		    <th field="DOMAIN_ID"  width="100">域唯一标示符</th>
			<th field="DOMAIN_DESC"  width="100">域描述</th>
			<th field="FIELD_NAME"  width="100">字段名称</th>
			<th field="FIELD_DESC"  width="100">字段描述</th>
			<th field="FIELD_LEVEL"  width="100">字段数据源级别</th>
			<th field="CREATE_DATE"  width="100">创建时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.DOMAIN_ID }</td>		
				<td>${list.DOMAIN_DESC }</td>
				<td>${list.FIELD_NAME }</td>
				<td>${list.FIELD_DESC }</td>
				<td>${list.FIELD_LEVEL }</td>
				<td>${list.CREATE_DATE }</td>
				<td>${list.DOMAIN_DESC }</td>
			</tr>		
		</c:forEach>
   </tbody>
</table>

</body>
</html>
