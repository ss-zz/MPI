<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/biz/js/block_add.js"></script>
<script type="text/javascript">

	// 选择字段数据
	var SELECT_JSON = ${selectJson};
	
</script>
</head>
<body>
	<div class="ToolTip_Form" id="table_add">
	
		<div class="easyui-panel" style="padding:5px; margin-bottom: 10px;">
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" id="saveBtn" onclick="saveMatchCfg()">保存</a>
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-back'" id="gobackBtn" onclick="goBackClose()">返回</a>
		</div>
		
		<fieldset>
			<legend>初筛配置设定</legend>			
			<label for="add_configDesc">初筛配置描述:</label><input type="text" class="easyui-validatebox" id="add_configDesc" maxlength="100" required="true"/>
			<div id="add_configTip" style="color:#ff0000;font-weight:bold;"></div>
		</fieldset><br/>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="resetBtn" onclick="addGroup()">添加分组</a>
		<div id="field_cfg_div">	</div>
	</div>
</body>
</html>
