<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/indexlog/js/il.js"></script>
</head>
<body>
<!-- 表格 -->
<table id="listTable"
	title="主索引处理日志"
	idField="PERSON_IDX_LOG_ID"
	>
	<thead>
		<tr align="center">
			<th field="OP_TIME" width="120">处理时间</th>
			<th field="OP_TYPE" width="80" formatter="buildTypeStr">处理类型</th>
			<th field="OP_STYLE" width="80" formatter="buildStyleStr">处理方式</th>
			<th field="OP_DESC" width="400">处理描述</th>
			<th field="DOMAIN_DESC" width="200">数据来源</th>
			<th field="PERSON_IDX_LOG_ID" width="100" formatter="buildMatchUrl">查看</th>
		</tr>
	</thead>
</table>

<!-- 搜索工具条 -->
<div id="listTable_tb" style="height: auto;text-align: center;">
	<table class="formeTable" align="center" >
		<tr>
			<td>处理类型:</td>
			<td><select id="search_optype" class="easyui-combobox" style="width: 200px;">
				<option value="">--请选择--</option>
				<option value="1">匹配</option>
				<option value="2">修订</option>
			</select></td>
			<td>处理方式:</td>
			<td><select id="search_opstyle" class="easyui-combobox" style="width: 200px;">
				<option value="">--请选择--</option>
				<option value="1">自动合并</option>
				<option value="2">自动新建</option>
				<option value="3">自动拆分</option>
				<option value="4">自动删除</option>
				<option value="11">人工合并</option>
				<option value="12">人工新建</option>
				<option value="13">人工拆分</option>
				<option value="14">人工删除</option>
			</select></td>
		</tr>
		<tr>
			<td>处理日期起:</td>
			<td><input id="search_optime_begin" style="width:200px"></td>
			<td>处理日期止:</td>
			<td><input id="search_optime_end" style="width:200px"></td>
		</tr>
		<tr>
			<td>数据来源:</td>
			<td>
				<input class="easyui-combobox" style="width:200px;" id="search_domain" 
					url="${pageContext.request.contextPath}/indexlog/il.ac?method=listDomain" 
					valueField="domainId"
					textField="domainDesc"
					panelHeight="auto" /></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<div class="formeTable-btn">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchListTable();">搜索</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="searchReset();">重置</a>
	</div>

</div>

</body>
</html>
