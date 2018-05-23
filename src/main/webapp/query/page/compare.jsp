<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/query/js/compare.js"></script>
<title>拆分至索引明细显示</title>
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