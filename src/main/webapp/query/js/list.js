$(function() {
	// 加载表格数据
	ajaxTable();
});

/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').datagrid({
		toolbar:"#listTable_tb"
	}).datagrid('acceptChanges');
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 查询
function searchListTable() {
	// 查询的时候重置回第一页
	$("#listTable").datagrid("options").pageNumber = 1; 
	$("#listTable").datagrid({
		queryParams : {
			"name":$("#search_personName").val(),
			"sex":$("#search_personSex").combobox('getValue'),
			"birthDate":$("#search_birthDate").val(),
			"idCardNum":$("#search_idCardNum").val()
		}
	});
}

// 搜索条件重置
function searchReset(){
	$("#search_personName").val("");
	$("#search_personSex").combobox('setValue','');
	$("#search_birthDate").val("");
	$("#search_idCardNum").val("");
}

function splitToSelectIndex(){
	// 取得选定行
	var row = $('#listTable').datagrid('getSelected');
	if(row == undefined || row == null || row == ""){
		alert("请先选择索引!");
		return;
	}
	var indexId = row.INDEX_ID;
	var personId = $("#personId").val();
	var fromIndexId = $("#fromIndexId").val();
	openTab('tabId_splitPersonAndIndexComp', '拆分至索引明细对比',
		root+'/query/query.ac?method=toCompare&personId='+personId+'&indexId='+indexId+'&fromIndexId='+fromIndexId);
	
}

