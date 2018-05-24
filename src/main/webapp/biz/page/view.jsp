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
		<caption>主索引业务详情</caption>
		<tr>
			<td><b>原始业务唯一标识：</b>${log.bizId }</td>
			<td><b>患者唯一标识：</b>${log.bizPatientId }</td>
			<td><b>患者就诊流水号：</b>${log.bizSerialId }</td>
		</tr>
		<tr>
			<td><b>住院号：</b>${log.bizInpatientno }</td>
			<td><b>门诊号：</b>${log.bizClinicno }</td>
			<td><b>业务来源：</b>${log.bizSystemId }</td>
		</tr>
		<tr>
			<td><b>创建日期：</b>${log.createDate }</td>
			<td colspan="2"><b>备注：</b>${log.remark }</td>
		</tr>
	</table>
	
</body>
</html>