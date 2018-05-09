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
			<label for="bizCommonFieldConfigId"><span class="star">*</span>通用字段:</label>
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
			<input class="easyui-textbox" name="key" required="true" validType="key" value="${item.key }" />
			<span class="comment">与注册数据中属性保持一致，同一业务不能重复</span>
		</div>
		<div>
			<label for="name"><span class="star">*</span>字段名:</label>
			<input class="easyui-textbox" type="text" name="name" required="true" value="${item.name }" />
			<span class="comment">字段中文或英文名，仅用于展示</span>
		</div>
		<div>
			<label for="isFirstMatch"><span class="star">*</span>是否初筛:</label>
			<input type="radio" name="isFirstMatch" value="true" />是
			<input type="radio" name="isFirstMatch" value="false" checked/>否
			<span class="comment">是否作为初步过滤条件，会当成查询条件从数据库中进行简单模糊查询</span>
		</div>
		<div>
			<label for="comment">备注:</label>
			<textarea class="easyui-textbox" multiline="true" name="comment">${item.comment }</textarea>
		</div>
		<div>
			<label for="algorithm"><span class="star">*</span>算法:</label>
			<select class="easyui-combobox" name="algorithm" style="width:400px;" data-options="value: '${item.algorithm }'">
				<option>算法1</option>
				<option>算法2</option>
				<option>算法3</option>
				<option>算法4</option>
				<option>算法5</option>
			</select>
		</div>
		<div>
			<label for="weight"><span class="star">*</span>权重:</label>
			<input class="easyui-textbox" name="weight" required="true" validType="decimal" value="${item.weight }" />
		</div>
		<div>
			<label for="rank">顺序:</label>
			<input class="easyui-textbox" name="rank" validType="integer" value="${item.rank }" />
			<span class="comment">仅用于展示位置不同</span>
		</div>
		<div>
			<a id="btn-submit" href="#" class="easyui-linkbutton" iconCls='icon-save'>提交</a>
			<a id="btn-back" href="#" class="easyui-linkbutton" iconCls='icon-undo'>返回</a>
		</div>
	</form>
</body>
</html>