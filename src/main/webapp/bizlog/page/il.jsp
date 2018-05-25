<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/bizlog/js/il.js"></script>
</head>
<body>
<!-- 表格 -->
<table id="listTable"
	title="主索引处理日志"
	idField="PERSON_IDX_LOG_ID" 
	>
	<thead>
		<tr align="center">
			<th field="BLTIME" width="150">处理时间</th>
			<th field="BLDESC" width="400">处理描述</th>
			<th field="BLMATCHED" width="50" formatter="initMatched">匹配度</th>
			<th field="SER_DESC" width="100">数据来源</th>
			<th field="PERSON_IDX_LOG_ID" width="100" formatter="buildMatchUrl" >查看</th>
		</tr>
	</thead>
</table>

<!-- 搜索工具条 -->
<div id="listTable_tb">
	<table class="formeTable" align="center" >
		<tr>
			<td>处理日期:从</td>
			<td>
				<input class="easyui-datetimebox" id="search_optime_begin"  style="width:200px">
				至:
				<input class="easyui-datetimebox" id="search_optime_end" style="width:200px">
			</td>
		</tr>
		<tr>
			<td>匹配度:大于(&gt;)</td>
			<td>
				<input type="text" style="width:180px;" id="blMatched_begin"/>
				小于(&lt;)
				<input  type="text" style="width:180px;" id="blMatched_end"/>
			</td>
		</tr>
		<tr>
			<td>数据来源:</td>
			<td>
				<input class="easyui-combobox" style="width:200px;" id="search_domain" 
					url="${pageContext.request.contextPath}/indexlog/il.ac?method=listDomain" 
					valueField="domainId"
					textField="domainDesc"
					panelHeight="auto" /></td>
		</tr>
	</table>
	
	<div class="formeTable-btn">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchListTable();">搜索</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="searchReset();">重置</a>
	</div>

</div>

</body>
</html>
