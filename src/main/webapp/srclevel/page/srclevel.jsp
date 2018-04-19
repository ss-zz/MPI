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
<font size="3" font-weight='bold' color='#15428b'>新增域字段数据源级别:</font>
<div class="panel-header panel-header-noborder"  style="padding: 5px; width:1050px; height: 200px;"  id="table_add"class="ToolTip_Form" id="table_edit" onkeydown="if(event.keyCode==13){editData();}">
 <ul style='margin:0;padding:5px;list-style-type: none;'>       
        <input type="button" value="重置" id="resetBtn" onclick="resetAllData()" />&nbsp;&nbsp;
		<input type="button" value="保存" id="saveBtn" onclick="saveMatchCfg()" />&nbsp;&nbsp;
				<!-- <input type="button" value="返回" id="gobackBtn" onclick="goBackClose()" /><br/><br/>	 -->
			<li>
				<label for="add_domainSelect">域唯一标识:</label><select id="add_domainSelect"></select>
			    &nbsp; &nbsp; &nbsp;<label for="add_fieldSelect">字段名称:</label>
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
			</li>		
</ul>
</div>  
<!-- 表格 -->
<table 	id="listTable"
		title="已配置的域字段数据源级别列表"  
		border="0"
		cellspacing="0"
		cellpadding="0"
		iconCls="icon-edit" 
		width="98%" 
		idField="ID" 
		remoteSort="false" 
		singleSelect="false" 
		showFooter="false"
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
