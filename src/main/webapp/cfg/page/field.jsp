<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/cfg/js/field.js"></script>
</head>
<body>
	<!-- 表格 -->
	<table id="listTable" 
		title="主索引字段列表" 
		url="${pageContext.request.contextPath}/mgr/fieldconfig/all">
		<thead>
			<tr align="center">
				<th field="propertyName" width=100>字段名</th>
				<th field="propertyDesc" width=100>字段描述</th>
				<th field="id" width="150" formatter="buildOptLink">操作</th>
			</tr>
		</thead>
	</table>
</body>
</html>
