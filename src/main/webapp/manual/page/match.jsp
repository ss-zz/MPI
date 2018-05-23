<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/manual/js/match.js"></script>
<title>匹配详情</title>
<script type="text/javascript">
	var BASE_DATAS = ${datas};
</script>
</head>
<body>	
<div>
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="建立新索引" id="add_new_index_btn" onclick="addNewIndex();" />&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="返回" onclick="goBackClose();" />
</div>
<br/>
<table class="myTable">
	<thead>
	<tr>
		<th rowspan="3" style="vertical-align: bottom;">字段名</th>
		<th rowspan="3" style="vertical-align: bottom;">居民信息</th>
		<th colspan="8">
			<div style="display: none;">
				<span class="pageSpan" id="pageInfo_span"></span>
				<br/>
				<span title="前一条" id="page_prev_btn"><a href="#" onclick="next(-1);">&lt;&lt;</a></span>&nbsp;&nbsp;&nbsp;&nbsp;
				索引信息&nbsp;&nbsp;&nbsp;&nbsp;
				<span title="后一条" id="page_next_btn"><a href="#" onclick="next(1);">&gt;&gt;</a></span>			
			</div>
			<div>
				<a href="#" onclick="openSummaryWin();">选择对比数据</a>
			</div>

		</th>
	</tr>
	<tr id="index_comp_tr">
		<th colspan="2"></th>
		<th colspan="2"></th>
		<th colspan="2"></th>
		<th colspan="2"></th>
	</tr>
	<tr>		
		<th>匹配度</th>
		<th>字段值</th>
		<th>匹配度</th>
		<th>字段值</th>
		<th>匹配度</th>
		<th>字段值</th>
		<th>匹配度</th>
		<th>字段值</th>
	</tr>
	</thead>
	<tbody id="data_show_table">
	</tbody>
</table>

<!-- 索引匹配列表 --> 
<div id="window_view" title="匹配索引摘要" iconCls="icon-detail">
	<div class="easyui-layout" fit="true">
		<div id="match_detail_view" region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">	
			<table 	id="detailTable"
					title="匹配索引摘要"
					iconCls="icon-edit"
					idField="INDEX_ID"
					url="${pageContext.request.contextPath}/manual/manual.ac?method=listMatchIndex" >
				<thead>
					<tr align="center">
						<th field="ck" width="20" checkbox="true" width="20"></th>
						<th field="NAME_CN"  width="100">姓名</th>
						<th field="ID_NO" width="100">身份证号</th>
						<th field="MATCH_DEGREE" width="100" formatter="matchDegreeToPercent">综合匹配度</th>
					</tr>
				</thead>
			</table>	
			<!-- 定义toobar -->
			<div id="detailTable_toolbar">  
				<a href="#" class="easyui-linkbutton" id="toobar_show_compare" iconCls="icon-remove" plain="true" onclick="closeAndRerender();">开始对比</a>  
			</div>  		
		</div>
	</div>
</div>

</body>
</html>