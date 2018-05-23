$(function() {
	// 搜索框初始化
	$('#search_opstatus').combobox({
		width:100
	});
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

// 查询匹配详情
function searchListTable() {   
	// 查询的时候重置回第一页
	$("#listTable").datagrid("options").pageNumber = 1; 
	
	var type = $("#search_opstatus").combobox('getValue');
	var personName = $("#search_personName").val();
	var personSex = $("#search_personSex").combobox('getValue');
	
	$("#listTable").datagrid({
		queryParams : {
			"MAN_OP_STATUS":type,
			"FIELD_PK":personName,
			"MAN_OP_ID":personSex
		}
	});
}

// 搜索条件重置
function searchReset(){
	$("#search_opstatus").combobox('setValue','');
	$("#search_personName").val('');
	$("#search_personSex").combobox('setValue','');
}

/**
 * 构建居民信息查看连接
 */
function buildPersonView(val,row){
	var id = row.FIELD_PK;
	return '<a href="#" onclick="unify_viewPerson(\''+id+'\',\''+val+'\');">'+val+'</a>';
}

// 构建匹配索引列表查看
function buildMatchUrl(val,row){
	var opStatus = row.MAN_OP_STATUS;
	var opId = row.MAN_OP_ID;
	if(opStatus == "0"){
		return '<a href="#" onclick="viewMatch(\''+val+'\',\''+opId+'\');">查看匹配</a>';
	}else{
		return '';
	}
}

// 构建操作类型描述
function buildTypeStr(val,row){
	switch (val) {
	case "0":
		return "未操作";
		break;
	case "1":
		return "已操作";
		break;
	default:
		return "未知状态";
		break;
	}
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 查看页面
function viewMatch(personId, opId){
	openTab('tabId_manualMatch', '查看匹配结果', root+'/manual/manual.ac?method=toMatch&personId='+personId+'&opId='+opId);
}

// 将匹配度转化为百分数
function matchDegreeToPercent(degree){
	if(isNumeric(degree)){
		var degreeNum = parseFloat(degree);
		
		return (Math.round(degreeNum*100*100)/100)+"%";
	}else{
		return degree;
	}
}

// 判断字符串是否是数字
function isNumeric(str){ 
	return (str.search(/^[\+\-]?\d+\.?\d*$/)==0);
}