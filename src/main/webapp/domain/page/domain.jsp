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
<script type="text/javascript" src="${pageContext.request.contextPath}/domain/js/domain.js"></script>
</head>
<body>
<!-- 表格 -->
<table 	id="listTable"
		title="居民身份域列表"  
		border="0"
		cellspacing="0"
		cellpadding="0"
		iconCls="icon-edit" 
		width="98%" 
		idField="DOMAIN_ID" 
		remoteSort="false" 
		singleSelect="false" 
		showFooter="false"
		striped="true"
		url="${pageContext.request.contextPath}/domain/domain.ac?method=query" >
	<thead>
		<tr align="center">
			<th field="ck" width="20" checkbox="true" width="20"></th>
			<th field="UNIQUE_SIGN"  width="100">唯一标识</th>
			<!-- <th field="DOMAIN_TYPE"  width="100">域类型</th> -->
			<th field="DOMAIN_DESC"  width="100">域描述</th>	
			<th field="PUSH_ADDR"  width="300">推送地址</th>
			<th field="BOOK_TYPE"  width="100" formatter="convertBookType">订阅类型</th>	
			<th field="DOMAIN_LEVEL"  width="100">域数据源级别</th>
			<th field="DOMAIN_ID"  width="150" formatter="buildOptLink">操作</th>	
		</tr>
	</thead>
</table>

<!-- 添加 -->
<div id="add" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="add_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_add" onkeydown="if(event.keyCode==13){addData();}">
        <ul>
			<li>
				<label>唯一标识：</label>
				<input type="text" class="easyui-validatebox" id="add_uniqueSign" maxlength="50" required="true" validType="uniqueSign['${pageContext.request.contextPath}/domain/domain.ac?method=test']"></input>
			</li>
			<li>
				<label>域&nbsp;类&nbsp;型：</label>
				<input type="text" class="easyui-validatebox" id="add_domainType" maxlength="50" required="true"></input>
			</li>
			<li>
				<label>域&nbsp;描&nbsp;述：</label>
				<input type="text" class="easyui-validatebox" id="add_domainDesc" maxlength="50" required="true"></input>
			</li>
			<li>
				<label>推送地址：</label>
				<input type="text" class="easyui-validatebox" id="add_pushAddr" maxlength="200"></input>
			</li>
			<li>
				<label>订阅类型：</label>
				<select id="add_bookType">
					<option value="0">推送</option>
					<option value="1">拉取</option>
				</select>
			</li>
			<li>
				<label>域数据源级别：</label>
				<input type="text" class="easyui-validatebox" id="add_domainLevel" required="true" maxlength="2" validType="isInteger"></input>
			</li>
			<li>
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="addData();">提交</a>
			</li>
		</ul>
	</div>
</div>

<!-- 编辑 -->
<div id="edit" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="edit_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_edit" onkeydown="if(event.keyCode==13){editData();}">
        <ul>
			<li>
				<label>唯一标识：</label>
				<input type="hidden" id="edit_domainId" />
				<input type="text" class="easyui-validatebox" id="edit_uniqueSign" maxlength="50" required="true" validType="uniqueSign['${pageContext.request.contextPath}/domain/domain.ac?method=test','#edit_domainId']"></input>
			</li>
			<li>
				<label>域&nbsp;类&nbsp;型：</label>
				<input type="text" class="easyui-validatebox" id="edit_domainType" maxlength="50" required="true"></input>
			</li>
			<li>
				<label>域&nbsp;描&nbsp;述：</label>
				<input type="text" class="easyui-validatebox" id="edit_domainDesc" maxlength="50" required="true"></input>
			</li>
			<li>
				<label>推送地址：</label>
				<input type="text" class="easyui-validatebox" id="edit_pushAddr" maxlength="200"></input>
			</li>
			<li>
				<label>订阅类型：</label>
				<select id="edit_bookType">
					<option value="0">推送</option>
					<option value="1">拉取</option>
				</select>
			</li>
			<li>
				<label>域数据源级别：</label>
				<input type="text" class="easyui-validatebox" id="edit_domainLevel" required="true" maxlength="2" validType="isInteger"></input>
			</li>
			<li>
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="editData();">提交</a>
			</li>
		</ul>
	</div>
</div>
</body>
</html>
