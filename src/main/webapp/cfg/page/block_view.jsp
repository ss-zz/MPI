<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>初筛设置详情</title>
<style>
.myTable {
	border-collapse: collapse;
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc; 
	color: #333;
	min-width: 50%;
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
	<table class="myTable">
		<caption>初筛[<span style="color:#ff0000;font-weight:bold;">${cfg.blockDesc }</span>]详情显示</caption>
		<tbody>
			<tr>
				<td>创建时间:&nbsp;${cfg.createDate }</td>		
				<td>是否生效:&nbsp;<c:if test="${cfg.state=='1' }">已生效</c:if><c:if test="${cfg.state!='1' }">未生效</c:if></td>
			</tr>		
		</tbody>
	</table>
	<!-- loop -->
	<c:forEach items="${cfg.groups }" var="fields">
	<table class="myTable">
		<caption>分组[<span style="color:#ff0000;font-weight:bold;">${fields.key+1 }</span>]</caption>
		<tbody>
		<c:forEach items="${fields.value }" var="field">
			<tr>
				<td>属性描述:&nbsp;${field.propertyCnName }</td>		
				<td>转换函数:&nbsp;${field.funName }<c:if test="${field.funName==null }">无</c:if></td>
			</tr>		
		</c:forEach>
		</tbody>
	</table>
	<br/>		
	</c:forEach>
</body>
</html>