<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/cfg/js/field_add.js"></script>
</head>
<body>
	<form id="form" method="post" class="my-form">
		<input type="hidden" name="id" value="${item.id }"/>
		<div>
			<label for="key"><span class="star">*</span>字段名:</label>
			<input class="easyui-validatebox" type="text" name="propertyName" required="true" validType="key" value="${item.propertyName }" />
			<span class="comment">跟注册数据中的属性保持一致</span>
		</div>
		<div>
			<label for="name"><span class="star">*</span>字段描述:</label>
			<input class="easyui-validatebox" type="text" name="propertyDesc" required="true" value="${item.propertyDesc }" />
			<span class="comment">仅用于查看时显示</span>
		</div>
		<div>
			<a id="btn-submit" href="#" class="easyui-linkbutton" iconCls='icon-save'>提交</a>
			<a id="btn-back" href="#" class="easyui-linkbutton" iconCls='icon-undo'>返回</a>
		</div>
	</form>
</body>
</html>
