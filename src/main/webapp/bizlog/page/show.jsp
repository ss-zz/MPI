<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
</head>
<body>
	<!-- 对比情况 所有均显示 -->
	<table class="myTable">
		<caption>主索引详情【${data.nameCn }】</caption>
		<thead>
			<tr>
				<th></th>
				<th>字段</th>
				<th>值</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${fields }" var="field" varStatus="s">
				<tr>
					<td>${s.count}</td>
					<td>${field.fieldCnName}</td>
					<td>${field.personValue}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
	<br />
</body>
</html>