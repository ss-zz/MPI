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
<script>
var domainid='${param.domainid == null ? request.domainid : param.domainid }';
var SELECT_JSON = ${selectJson};
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/srclevel/js/srclevel_add.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/srclevel/js/srclevel.js"></script>

</head>
<body>
<div class="easyui-panel" title="新增域字段数据源级别" id="table_add" onkeydown="if(event.keyCode==13){editData();}">
	<label for="add_domainSelect">域唯一标识:</label>
	<select id="add_domainSelect"></select>
	&nbsp; &nbsp; &nbsp;
	<label for="add_fieldSelect">字段名称:</label>
	<select id="add_fieldSelect">
	<!--   <option value="">--请选择--</option>
		<option value="ID_NO">身份证号</option>
		<option value="NH_CARD">农合卡号</option>
		<option value="HR_ID">城乡居民健康档案编号</option>
		<option value="SSCID">社会保障卡号</option>
		<option value="MEDICALSERVICE_NO">医疗服务编号</option>
		<option value="NAME_CN">姓名</option>
		<option value="GENDER_CD">性别</option>
		<option value="BIRTH_DATE">出生日期</option> -->
	</select>
	&nbsp; &nbsp; &nbsp;<label>字段数据源级别：</label>
	<select id="add_fieldlevel" length="50">
		<option value="">--请选择--</option>
		<option value="0">0级</option>
		<option value="1">1级</option>
		<option value="2">2级</option>
		<option value="3">3级</option>
		<option value="4">4级</option>
		<option value="5">5级</option>
	</select>
	<br/>
	<a onclick="saveMatchCfg()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
	<a onclick="resetAllData()" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
</div>
<!-- 表格 -->
<table 	id="listTable"
		title="已配置的域字段数据源级别"
		idField="ID" 
		singleSelect="false" 
		striped="true"
		url="${pageContext.request.contextPath}/domainsrclevel/srclevel.ac?method=queryByID" >
	<thead>
		<tr align="center">
		    <th field="ck" width="20" checkbox="true" width="20"></th>
		   <!--  <th field="DOMAIN_ID"  width="100">域唯一标示符</th>
			<th field="DOMAIN_DESC"  width="100">域描述</th> -->
			<th field="FIELD_NAME"  width="100">字段名称</th>
			<th field="FIELD_DESC"  width="100">字段描述</th>
			<th field="FIELD_LEVEL"  width="100">字段数据源级别</th>
			<th field="CREATE_DATE"  width="100">创建时间</th>
		</tr>
	</thead>
</table>

</body>
</html>
