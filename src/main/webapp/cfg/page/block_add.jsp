<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/cfg/js/block_add.js"></script>
<script type="text/javascript">
	var SELECT_JSON = ${selectJson};
</script>
<style>
.myTable {
	border-collapse: collapse;
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc; 
	color: #333;
	min-width: 50%;
}

.myTable caption {
	font-size: 1.1em;
	font-weight: bold;
	letter-spacing: -1px;
	margin-bottom: 10px;
	padding: 5px;
	text-align: left;
}

.myTable a {
	text-decoration: none;
	border-bottom: 1px dotted #f60;
	color: #f60;
	font-weight: bold;
}

.myTable a:hover {
	text-decoration: none;
	color: #fff;
	background: #f60;
}

.myTable tr th a {
	color: #369;
	border-bottom: 1px dotted #369;
}

.myTable tr th a:hover {
	color: #fff;
	background: #369;
}

.myTable thead tr th {
	text-transform: uppercase;
	background: #e2e2e2;
}

.myTable td, table th {
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding: 5px;
	line-height: 1.8em;
	font-size: 0.8em;
	vertical-align: top;
}

.myTable tr.odd th, table tr.odd td {
	background: #efefef;
}
</style>
</head>
<body>
	<div class="ToolTip_Form" id="table_add">
		<input type="button" value="保存" id="saveBtn" onclick="saveMatchCfg()" />&nbsp;&nbsp;
		<input type="button" value="返回" id="gobackBtn" onclick="goBackClose()" /><br/><br/>	
		<fieldset>
			<legend>初筛配置设定</legend>			
			<label for="add_configDesc">初筛配置描述:</label><input type="text" class="easyui-validatebox" id="add_configDesc" maxlength="100" required="true"/>
			<div id="add_configTip" style="color:#ff0000;font-weight:bold;"></div>
		</fieldset><br/>
		<input type="button" value="添加分组" onclick="addGroup()" /><br/><br/>
		<div id="field_cfg_div">		
		</div>			
	</div>
</body>
</html>
