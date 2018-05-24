<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/bizlog/js/view.js"></script>
<title>索引日志明细</title>
</head>
<body>

	<!-- 顶头表格 所有类型均有 -->
	<table class="myTable">
		<caption>主索引日志处理详情</caption>
		<tr>
			<td colspan="2"><b>概要信息</b></td>
		</tr>
		<tr>
			<td colspan="2"><b>描述：</b>${log.blDesc }</td>
		</tr>
		<tr>
			<td><b>信息来源：</b>${log.blInfoSour }</td>
			<td><b>处理时间：</b>
				<fmt:formatDate value="${log.blTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><b>详细信息</b></td>
		</tr>
		<tr>
			<td><b>就诊流水号：</b>${log.blSerialId }</td>
			<td><b>综合匹配度：</b>${log.blMatched }</td>
		</tr>
		<tr>
			<td><b>原始业务标识：</b>${log.blBizId }</td>
			<td></td>
		</tr>
	</table>
	
</body>
</html>