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
<title>索引业务明细</title>
</head>
<body>

	<!-- 顶头表格 所有类型均有 -->
	<table class="myTable">
		<caption>主索引业务处理详情</caption>
		<tr>
			<td>原始业务Id:${log.bizId }</td>
			<td>患者id:${log.bizPatientId }</td>
			<td>患者就诊流水号:${log.bizSerialId }</td>
		</tr>
		<tr>
			<td>住院就诊流水号:${log.bizInpatientSerialno }</td>
			<td>门诊就诊流水号:${log.bizClinicSerialno }</td>
			<td>业务来源:${log.bizSystemId }</td>
		</tr>
		<tr>
			<td>数据注册类型：<c:if test="${log.state != null }"><c:if test="${log.state == '0' }">新增</c:if><c:if test="${log.state == '1' }">更新</c:if><c:if test="${log.state == '2' }">拆分</c:if></c:if></td>
			<td>创建日期:${log.create_Date }</td>
			<td>备注:${log.remark }</td>
		</tr>
	</table>
	
</body>
</html>