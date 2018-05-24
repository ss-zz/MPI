<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>初筛设置详情</title>
</head>
<body>
	<c:forEach items="${cfg.groups }" var="fields">
	<table class="myTable">
		<caption>分组-${fields.key+1 }</caption>
		<tbody>
		<tr>
			<th>属性描述</th>
			<th>转换函数</th>
		</tr>
		<c:forEach items="${fields.value }" var="field">
			<tr>
				<td>${field.propertyCnName }</td>
				<td>${field.funName }<c:if test="${field.funName==null }">无</c:if></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br/>
	</c:forEach>
</body>
</html>