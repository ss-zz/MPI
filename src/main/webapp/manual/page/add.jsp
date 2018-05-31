<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/manual/js/add.js"></script>
</head>
<body>

<div class="help-info">
	列表中【未操作】的数据为自动合并过程中匹配度大于【可能匹配值】小于【完全匹配值】的数据。此部分数据已默认合并到匹配度最高的主索引信息上。
</div>

<!-- 表格 -->
<table id="listTable"
		title="人工操作居民信息"
		url="${pageContext.request.contextPath}/manual/manual.ac?method=query" >
	<thead>
		<tr align="center">
			<th field="MAN_OP_STATUS" width="50" formatter="buildTypeStr">操作状态</th>
			<th field="MAN_OP_TIME" width="150">操作时间</th>
			<th field="DOMAIN_DESC" width="150">数据来源</th>
			<th field="NAME_CN" width="100" formatter="buildPersonView">姓名</th>
			<th field="GENDER_DN" width="50">性别</th>
			<th field="BIRTH_DATE" width="100">出生日期</th>
			<th field="ID_NO" width="200">身份证号</th>
			<th field="FIELD_PK" width="100" formatter="buildMatchUrl">操作</th>
		</tr>
	</thead>
</table>

<!-- 搜索工具条 -->
<div id="listTable_tb" style="padding:5px;height:auto">
	<div>
		操作状态:<select id="search_opstatus" >
			<option value="">--请选择--</option>
			<option value="0">未操作</option>
			<option value="1">已操作</option>
		</select>
		姓名:<input type="text" class="combo-text" id="search_personName">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchListTable();">搜索</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchReset();">重置</a>
	</div>
</div>
</body>
</html>
