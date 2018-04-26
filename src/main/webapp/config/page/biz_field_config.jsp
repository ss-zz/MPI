<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/config/js/biz_field_config.js"></script>
</head>
<body>
	
	<input type="hidden" id="inputBizConfigId" value="${itemBiz.id }" />

	<div class="easyui-panel" title="当前业务" style="padding: 10px;">
		<h1>${itemBiz.name }【${itemBiz.key }- 阈值：${itemBiz.threshold }】</h1>&nbsp;&nbsp;
		<span>${itemBiz.comment }</span>
	</div>

	<!-- 表格 -->
	<table id="listTable" 
		title="【${itemBiz.name }】业务字段配置" 
		url="${pageContext.request.contextPath}/mgr/bizfieldconfig/findByBizConfigId/${itemBiz.id}">
		<thead>
			<tr align="center">
				<th field="key" width=100>唯一标识</th>
				<th field="name" width=100>字段名</th>
				<th field="comment" width=100>备注</th>
				<th field="algorithm" width=100>算法</th>
				<th field="rank" width=100>顺序</th>
				<th field="weight" width=100>权重</th>
				<th field="bizCommonFieldConfigId" width=100>通用字段</th>
				<th field="id" width="150" formatter="buildOptLink">操作</th>
			</tr>
		</thead>
	</table>
</body>
</html>
