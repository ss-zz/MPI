<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/config/js/common_field_config_add.js"></script>
</head>
<body>
	<form id="form" method="post">
		<input type="hidden" name="id" value="${item.id }"/>
		<div>
			<label for="key">唯一标识:</label>
			<input class="easyui-validatebox" type="text" name="key" data-options="required:true" value="${item.key }" />
		</div>
		<div>
			<label for="name">字段名:</label>
			<input class="easyui-validatebox" type="text" name="name" data-options="required:true" value="${item.name }" />
		</div>
		<div>
			<label for="comment">备注:</label>
			<input class="easyui-validatebox" type="text" name="comment" data-options="required:true" value="${item.comment }" />
		</div>
		<div>
			<label for="algorithm">默认算法:</label>
			<input class="easyui-validatebox" type="text" name="algorithm" data-options="required:false" value="${item.algorithm }" />
		</div>
		<div>
			<label for="rank">顺序:</label>
			<input class="easyui-validatebox" type="text" name="rank" data-options="required:false" value="${item.rank }" />
		</div>
		<div>
			<label for="weight">默认权重:</label>
			<input class="easyui-validatebox" type="text" name="weight" data-options="required:false" value="${item.weight }" />
		</div>
		<div>
			<a id="btn-submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交</a>
			<a id="btn-back" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">返回</a>
		</div>
	</form>
</body>
</html>
