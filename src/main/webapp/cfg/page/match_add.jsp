<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/page/master.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/cfg/js/match_add.js"></script>
<script type="text/javascript">
	var SELECT_JSON = ${selectJson};
</script>
</head>
<body>
	<div class="ToolTip_Form" id="table_add">
		<input type="button" value="重置" id="resetBtn" onclick="resetAllData()" />&nbsp;&nbsp;
		<input type="button" value="保存" id="saveBtn" onclick="saveMatchCfg()" />&nbsp;&nbsp;
		<input type="button" value="返回" id="gobackBtn" onclick="goBackClose()" /><br/><br/>	
		<fieldset>
			<legend>整体匹配设定</legend>
				<label for="add_agreeThreshold">完全匹配值:</label><input type="text" class="easyui-validatebox" id="add_agreeThreshold" maxlength="10" required="true" validType="decimalValid"/>
				<label for="add_matchThreshold">可能匹配值:</label><input type="text" class="easyui-validatebox" id="add_matchThreshold" maxlength="10" required="true" validType="decimalValid"/>
				<label for="add_configDesc">匹配设定描述:</label><input type="text" class="easyui-validatebox" id="add_configDesc" maxlength="100" required="true"/>
		</fieldset><br/>
		<label for="add_fieldSelect">选择字段:</label><select id="add_fieldSelect"></select>
		<input type="button" value="添加字段配置" onclick="addFieldCfg()" /><br/>
		<div id="field_cfg_div"></div>			
	</div>
</body>
</html>
