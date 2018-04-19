<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/page/master.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/merge/js/comp.js"></script>
<title>
</title>
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
<script type="text/javascript">
	var BASE_DATAS = ${datas};
</script>
</head>
<body>	
<div>
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="合并" id="merge_person_btn" onclick="mergePerson();" />&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="返回" onclick="goBackClose();" />
</div>
<br/>
<table class="myTable">
	<caption>数据来源:[<span id="dataFromSpan"></span>]</caption>
	<thead>
	<tr>
		<th>字段名</th>
		<th>目标居民</th>
		<th>被合并居民</th>
	</tr>
	</thead>
	<tbody id="data_show_table">
	</tbody>
</table>
</body>
</html>