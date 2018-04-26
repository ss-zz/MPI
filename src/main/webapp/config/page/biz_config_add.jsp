<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/config/js/biz_config_add.js"></script>
</head>
<body>
	<form id="form" method="post" class="my-form">
		<input type="hidden" name="id" value="${item.id }"/>
		<div>
			<label for="key"><span class="star">*</span>唯一标识:</label>
			<input class="easyui-validatebox" type="text" name="key" required="true" validType="key" value="${item.key }" />
			<span class="comment">由字母、数字、下划线、连接符组成</span>
		</div>
		<div>
			<label for="name"><span class="star">*</span>业务名:</label>
			<input class="easyui-validatebox" type="text" name="name" required="true" value="${item.name }" />
			<span class="comment">字段中文或英文名，用于查看时显示</span>
		</div>
		<div>
			<label for="comment">备注:</label>
			<textarea class="easyui-validatebox" name="comment">${item.comment }</textarea>
		</div>
		<div>
			<label for="weight"><span class="star">*</span>阈值:</label>
			<input class="easyui-validatebox" type="text" name="threshold" required="true" validType="decimal" value="${item.threshold }" />
			<span class="comment">决定最终是否匹配</span>
		</div>
		<div>
			<a id="btn-submit" href="#" class="easyui-linkbutton" iconCls='icon-save'>提交</a>
			<a id="btn-back" href="#" class="easyui-linkbutton" iconCls='icon-undo'>返回</a>
		</div>
	</form>
</body>
</html>
