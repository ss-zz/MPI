<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/page/master.jsp"%>
<title>匹配设置详情</title>
</head>
<body>
	<!-- 对比情况 所有均显示 -->
	<table class="myTable">
		<caption>匹配配置[<span style="color:#ff0000;font-weight:bold;">${cfg.configDesc }</span>]详情显示</caption>
		<tbody>
			<tr>
				<td>完全匹配值:&nbsp;${cfg.agreeThreshold }</td>
				<td>可能匹配值:&nbsp;${cfg.matchThreshold }</td>
				<td>创建时间:&nbsp;${cfg.createDate }</td>
				<td>状态:&nbsp;<c:if test="${cfg.state=='1' }">已生效</c:if><c:if test="${cfg.state!='1' }">未生效</c:if></td>
			</tr>
		</tbody>
	</table>
	<br/>
	<!-- loop -->
	<c:forEach items="${cfg.matchFieldCfgs }" var="field">
	<table class="myTable">
		<caption>字段[<span style="color:#ff0000;font-weight:bold;">${field.cfgDesc }</span>]配置</caption>
		<tbody>
			<tr>
				<%-- <td>完全匹配值:&nbsp;${field.agreeProb }</td>
				<td>不匹配值:&nbsp;${field.disAgree }</td>
				<td>匹配阀值:&nbsp;${field.matchThreshold }</td> --%>
				<td>权重:&nbsp;${field.weight }</td>
				<td>比较函数:&nbsp;${field.matchFunction }</td>
			</tr>
		</tbody>
	</table>
	<br/>		
	</c:forEach>
</body>
</html>