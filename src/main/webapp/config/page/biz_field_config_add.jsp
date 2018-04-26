<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/config/js/biz_field_config_add.js"></script>
</head>
<body>
	<form id="form" method="post" class="my-form">
		<input type="hidden" name="id" value="${item.id }"/>
		<input id="inputBizConfigId" type="hidden" name="bizConfigId" value="${bizConfigId }"/>
		<div>
			<label for="algorithm">通用字段:</label>
			<select class="easyui-combobox" name="bizCommonFieldConfigId" style="width:400px;" data-options="value: '${item.bizCommonFieldConfigId }'">
				<option>算法1</option>
				<option>算法2</option>
				<option>算法3</option>
				<option>算法4</option>
				<option>算法5</option>
			</select>
		</div>
		<div>
			<label for="key"><span class="star">*</span>唯一标识:</label>
			<input class="easyui-validatebox" type="text" name="key" required="true" validType="key" value="${item.key }" />
			<span class="comment">与注册数据中属性保持一致，同一业务不能重复</span>
		</div>
		<div>
			<label for="name">字段名:</label>
			<input class="easyui-validatebox" type="text" name="name" required="true" value="${item.name }" />
			<span class="comment">字段中文或英文名，仅用于展示</span>
		</div>
		<div>
			<label for="comment">备注:</label>
			<textarea class="easyui-validatebox" name="comment">${item.comment }</textarea>
		</div>
		<div>
			<label for="algorithm">算法:</label>
			<select class="easyui-combobox" name="algorithm" style="width:400px;" data-options="value: '${item.algorithm }'">
				<option>算法1</option>
				<option>算法2</option>
				<option>算法3</option>
				<option>算法4</option>
				<option>算法5</option>
			</select>
		</div>
		<div>
			<label for="rank">顺序:</label>
			<input class="easyui-validatebox" type="text" name="rank" required="true" validType="integer" value="${item.rank }" />
			<span class="comment">仅用于展示位置不同</span>
		</div>
		<div>
			<label for="weight">权重:</label>
			<input class="easyui-validatebox" type="text" name="weight" required="true" validType="decimal" value="${item.weight }" />
		</div>
		<div>
			<a id="btn-submit" href="#" class="easyui-linkbutton" iconCls='icon-save'>提交</a>
			<a id="btn-back" href="#" class="easyui-linkbutton" iconCls='icon-undo'>返回</a>
		</div>
	</form>
</body>
</html>
