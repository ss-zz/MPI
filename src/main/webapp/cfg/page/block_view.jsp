<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>初筛设置详情</title>
</head>
<body>
	<table class="myTable">
		<caption>初筛[<span>${cfg.blockDesc }</span>]详情</caption>
		<tbody>
			<tr>
				<td>创建时间:&nbsp;${cfg.createDate }</td>
				<td>是否生效:&nbsp;<c:if test="${cfg.state=='1' }">已生效</c:if><c:if test="${cfg.state!='1' }">未生效</c:if></td>
			</tr>
		</tbody>
	</table>
	<br/>
	<!-- loop -->
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