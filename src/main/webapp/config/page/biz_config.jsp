<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/config/js/biz_config.js"></script>
</head>
<body>
	<!-- 表格 -->
	<table id="listTable" 
		title="业务配置" 
		url="${pageContext.request.contextPath}/mgr/bizconfig/all">
		<thead>
			<tr align="center">
				<th field="key" width=100>唯一标识</th>
				<th field="name" width=100>业务名</th>
				<th field="comment" width=100>备注</th>
				<th field="threshold" width=100>阈值</th>
				<th field="id" width="150" formatter="buildOptLink">操作</th>
			</tr>
		</thead>
	</table>
</body>
</html>
