<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/indexlog/js/il.js"></script>
<style type="text/css">
.formeTable {
	border: 0px;
	border-collapse: collapse;
	width: 60%;
}

.formeTable tr td:nth-child(odd){
	width:20%;
	text-align: right;
}

.formeTable tr td:nth-child(even){
	width:30%;
	text-align: left;
}

</style>
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
			<th field="PERSONIDCARD" width="200">身份证号</th>
			<th field="OP_DESC" width="400">处理描述</th>
			<!-- <th field="DOMAIN_DESC" width="100">数据来源</th> -->
			<th field="NAME" width="80">操作人</th>
			<th field="PERSON_IDX_LOG_ID" width="100" formatter="buildMatchUrl">查看</th>
		</tr>
	</thead>
</table>


<!--主索引处理日志处理日期设置默认值  -->
<script type="text/javascript">
$(function(){
	
	var sysdate = new Date();
	var year = sysdate.getFullYear(),
		month = sysdate.getMonth()+1,
		day = sysdate.getDate();
	var search_optime_end = year+"-"+month+"-"+day,
		search_optime_begin = year+"-01-01";
	stdate = search_optime_begin;
	enddate = search_optime_end;
	$('#search_optime_begin').datebox('setValue',search_optime_begin);
	$('#search_optime_end').datebox('setValue',search_optime_end);
	var params = {
		'stDate':search_optime_begin,
		'endDate':search_optime_end
	};
});
</script>

<!-- 搜索工具条 -->
<div id="listTable_tb" style="height: auto;text-align: center;">
	<table class="formeTable" align="center" >
		<tr>
			<td>处理类型:</td>
			<td><select id="search_optype" >
			<!-- 	<option value="">--请选择--</option> -->
				<option value="1">匹配</option>
				<option value="2">修订</option>
			</select></td>
			<td>处理方式:</td>
			<td><select id="search_opstyle" >
			<!-- 	<option value="">--请选择--</option> -->
				<option value="1">自动合并</option>
				<option value="2">自动新建</option>
				<option value="3">自动拆分</option>
				<option value="4">人工合并</option>
				<option value="5">人工新建</option>
				<option value="6">人工拆分</option>
			</select></td>
		</tr>
		<tr>
			<td>处理日期起:</td>
			<td><input class="easyui-datetimebox" id="search_optime_begin"  style="width:150px"></td>
			<td>处理日期止:</td>
			<td><input class="easyui-datetimebox" id="search_optime_end" style="width:150px"></td>
		</tr>
		<tr>
			<td>操作人:</td>
			<td><input type="text" class="combo-text" id="search_opuser" style="width:150px"></td>
			<td>数据来源:</td>
			<td><input class="easyui-combobox" style="width:150px;" id="search_domain" url="${pageContext.request.contextPath}/indexlog/il.ac?method=listDomain" valueField="DOMAIN_ID" textField="DOMAIN_DESC" panelHeight="auto" /></td>
		</tr>
		<tr>
			<td>姓名:</td>
			<td><input type="text" class="combo-text" id="search_personName" style="width:150px"></td>
			<td>身份证号:</td>
			<td><input type="text" class="combo-text" id="search_personIdcard" style="width:150px"></td>
		</tr>

	</table>
	<div>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchListTable();">搜索</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchReset();">重置</a>
	</div>

</div>

</body>
</html>
