<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/bizlog/js/view.js"></script>
<title>索引日志明细</title>
</head>
<body>

	<!-- 顶头表格 所有类型均有 -->
	<table class="myTable">
		<caption>主索引日志处理详情</caption>
		<tr>
			<td>日志类型:<c:if test="${log.blType != null }"><c:if test="${log.blType == 1 }">匹配</c:if></c:if></td>
			<td>患者就诊流水号:${log.blSerialId }</td>
			<td>综合匹配度:${log.blMatched }</td>
		</tr>
		<tr>
			<td>处理时间:${log.blTime }${fn:substring(log.blTime, 0, ${fn:length(log.blTime)-2} )}  </td>
			<td>信息来源:${log.blInfoSour }</td>
			<td>处理人:${log.blUserId }</td>
		</tr>
		<tr>
			<td>原始业务标识:${log.blBizId }</td>
			<td>日志描述:${log.blDesc }</td>
			<td></td>
		</tr>
	</table>
	
</body>
</html>