<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/domain/js/fieldlevel.js"></script>
</head>

<body>
<!-- 表格 -->
<table id="tt" class="easyui-datagrid">
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
