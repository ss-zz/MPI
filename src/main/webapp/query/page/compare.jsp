<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/query/js/compare.js"></script>
<title>拆分至索引明细显示</title>
<style>
.myTable {
	border-collapse: collapse;
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc;
	color: #333;
	min-width: 98%;
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

.myTable td,table th {
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding: 5px;
	line-height: 1.8em;
	font-size: 0.8em;
	vertical-align: middle;
}

.myTable tbody tr:nth-of-type(even) td {
	background: #efefef !important;
}

.pageSpan {
	font-weight: normal;
}
</style>
</head>
<body>	
<div>
	<input type="hidden" value="${personId }" id="selectPersonId" />
	<input type="hidden" value="${indexId }" id="selectIndexId" />
	<input type="hidden" value="${fromIndexId }" id="fromIndexId" />
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="拆分至本索引" id="split_person_btn" onclick="splitToIndex();" />&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="返回" onclick="goBackClose();" />
</div>
<br/>
<table class="myTable">
	<thead>
	<tr>
		<th>字段名</th>
		<th>居民信息</th>
		<th>索引信息</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${comp }" var="detail">
		<tr>
			<td>${detail.fieldCnName }</td>
			<td>${detail.personValue }</td>
			<td>${detail.indexValue }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>