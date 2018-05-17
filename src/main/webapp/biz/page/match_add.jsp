<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/biz/js/match_add.js"></script>
<script type="text/javascript">
	var SELECT_JSON = ${selectJson};
</script>
</head>
<body>
	<div class="ToolTip_Form" id="table_add">
		<div class="easyui-panel" style="padding:5px; margin-bottom: 10px;">
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-back'" id="gobackBtn" onclick="goBackClose()">返回</a>
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" id="saveBtn" onclick="saveMatchCfg()">保存</a>
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" id="resetBtn" onclick="resetAllData()">重置</a>
		</div>
		<fieldset>
			<legend>整体匹配设定</legend>
				<label for="add_agreeThreshold">完全匹配值:</label><input type="text" class="easyui-validatebox" id="add_agreeThreshold" maxlength="10" required="true" validType="decimalValid"/>
				<label for="add_matchThreshold">可能匹配值:</label><input type="text" class="easyui-validatebox" id="add_matchThreshold" maxlength="10" required="true" validType="decimalValid"/>
				<label for="add_configDesc">匹配设定描述:</label><input type="text" class="easyui-validatebox" id="add_configDesc" maxlength="100" required="true"/>
		</fieldset><br/>
		<label for="add_fieldSelect">请选择匹配字段:</label><select id="add_fieldSelect" onchange="addFieldCfg()"></select>
		<div id="field_cfg_div"></div>
	</div>
</body>
</html>
