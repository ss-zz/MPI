$(function() {
	// 加载表格数据
	ajaxTable();
});

/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').treegrid({
		url: root + "/personlog/pl.ac",
		title : '居民操作信息查询',
		toolbar:"#listTable_tb",
		idField:"ROW_ID",
		treeField:"DOMAIN_DESC",
		showFooter:false,
		columns:[[
			{field:'DOMAIN_DESC',title:'所在域',width:200},
			{field:'OPTYPE',title:'处理类型',width:100,formatter:buildTypeStr},
			{field:'OP_STYLE',title:'处理方式',width:100,formatter:buildStyleStr},
			{field:'NAME_CN',title:'姓名',width:80,formatter:buildLink},
			{field:'GENDER_CD',title:'性别',width:50},
			{field:'BIRTH_DATE',title:'出生日期',width:80},
			{field:'ID_NO',title:'身份证号',width:100},
			{field:'PERSON_TEL_NO',title:'电话号码',width:80},
			{field:'OPCOUNT',title:'操作数',width:80,formatter:buildMatchUrl}
		]],
		onBeforeLoad : function(row, param) {
			if (row) {
				$(this).treegrid('options').url =  root + '/personlog/pl.ac?method=listOp&personId='+row.ROW_ID;
			} else {
				$(this).treegrid('options').url =  root + '/personlog/pl.ac';
				var personName = $("#search_personName").val();
				var personSex = $("#search_personSex").combobox('getValue');
				param = {
					"name" : personName,
					"sex" : personSex
				};
			}
		},
		onContextMenu : function(e, row) {
			e.preventDefault();
			$(this).treegrid('unselectAll');
			$(this).treegrid('select', row.code);
		}
	});
}

function buildLink(val, row){
	var rowType = row.ROW_TYPE;
	var id;
	if(rowType == 1){
		id = row.ROW_ID;
		return '<a href="#" onclick="unify_viewPerson(\'' + id + '\',\'' + val + '\');">'+val+'</a>';	
	}else{
		id = row.INDEX_ID;
		return '<a href="#" onclick="unify_viewIndex(\'' + id + '\',\'' + val + '\');">'+val+'</a>';
	}
}

//构建索引查看连接
function buildMatchUrl(val, row) {
	var rowType = row.ROW_TYPE;
	if(rowType == 2){
		var logId = row.ROW_ID;
		return '<a href="#" onclick="viewMatch(\'' + logId + '\');">查看</a>';
	}else{
		return val;
	}
}

// 查看日志处理详情
function viewMatch(logId){	
	openTab('tabId_logDetailView', '索引日志处理详情', root+'/indexlog/il.ac?method=view&logId='+logId);
}

//构建操作类型描述
function buildTypeStr(val,row) {
	switch (val) {
	case "1":
		return "匹配";
		break;
	case "2":
		return "修订";
		break;
	default:
		return "";
		break;
	}
}
//构建操作类型描述
function buildStyleStr(val,row) {
	switch (val) {
	case "1":
		return "自动合并";
		break;
	case "2":
		return "自动新建";
		break;
	case "3":
		return "自动拆分";
		break;
	case "4":
		return "人工合并";
		break;
	case "5":
		return "人工新建";
		break;
	case "6":
		return "人工拆分";
		break;
	default:
		return "";
		break;
	}
}

// 刷新表格
function reloadTable() {
	$('#listTable').treegrid('reload');
}

// 查询匹配详情
function searchListTable() {
	// 查询的时候重置回第一页
	$("#listTable").treegrid("options").pageNumber = 1;

	var personName = $("#search_personName").val();
	var personSex = $("#search_personSex").combobox('getValue');

	$("#listTable").treegrid({
		queryParams : {
			"name" : personName,
			"sex" : personSex
		}
	});
}

// 搜索条件重置
function searchReset() {
	$("#search_opstatus").combobox('setValue', '');
	$("#search_personName").val('');
	$("#search_personSex").combobox('setValue', '');
}
