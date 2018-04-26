<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<title><c:if test="${type == 'person' }">居民</c:if><c:if test="${type == 'index' }">索引</c:if>[${data.NAME_CN }]信息详情
</title>
<style>
.myTable {
	border-collapse: collapse;
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc; 
	color: #333;
}

.myTable caption {
	font-size: 1.1em;
	font-weight: bold;
	letter-spacing: -1px;
	margin-bottom: 10px;
	padding: 5px;
	text-align: left;
}

.myTable a {
	text-decoration: none;
	border-bottom: 1px dotted #f60;
	color: #f60;
	font-weight: bold;
}

.myTable a:hover {
	text-decoration: none;
	color: #fff;
	background: #f60;
}

.myTable tr th a {
	color: #369;
	border-bottom: 1px dotted #369;
}

.myTable tr th a:hover {
	color: #fff;
	background: #369;
}

.myTable thead tr th {
	text-transform: uppercase;
	background: #e2e2e2;
}

.myTable td, table th {
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding: 5px;
	line-height: 1.8em;
	font-size: 0.8em;
	vertical-align: top;
}

.myTable tr.odd th, table tr.odd td {
	background: #efefef;
}
</style>
</head>
<body>
	<!-- 对比情况 所有均显示 -->
	<table class="myTable">
		<caption><c:if test="${type == 'person' }">居民[${data.NAME_CN }]信息详情<br/>(数据源:[${data.DOMAIN_DESC }])</c:if><c:if test="${type == 'index' }">索引[${data.NAME_CN }]信息详情</c:if></caption>
		<thead>
			<tr>
				<th></th>
				<th>信息</th>
				<th>信息值</th>
			</tr>
		</thead>
		<tbody>
			<!-- loop -->
			<c:forEach items="${fields }" var="field" varStatus="s">
			<tr>
				<td>${s.count}</td>
				<td>${field.fieldCnName}</td>
				<td>${field.personValue}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/><br/>	
</body>
</html>