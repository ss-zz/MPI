$(function() {
	//初始化时间输入框
	initTimeInput();
	// 加载表格数据
	ajaxTable();
	
	// 日期默认值
	var sysdate = new Date();
	var year = sysdate.getFullYear(),
		month = sysdate.getMonth()+1,
		day = sysdate.getDate();
	
	$('#search_optime_begin').datetimebox('setValue',year + "-" + month +  "-01 00:00:00");
	$('#search_optime_end').datetimebox('setValue',year + "-" + month + "-" + day + " 23:59:59");
});

/**
 * 初始化时间选择框
 */
function initTimeInput(){
	var option = {
		showSeconds: true,
		formatter: function(date){
			return date.formatDate("yyyy-MM-dd hh:mm:ss");
		}
	};
	
	$('#search_optime_begin').datetimebox(option);
	$('#search_optime_end').datetimebox(option);
}

/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').datagrid({
		toolbar : "#listTable_tb"
	}).datagrid('acceptChanges');
}

//构建索引查看连接
function buildMatchUrl(val, row) {
	var logId = row.PERSON_IDX_LOG_ID;
	return '<a href="#" onclick="viewMatch(\'' + logId + '\');">查看</a>';
}

// 打开查看页面
function viewMatch(logId){
	openTab('tabId_logDetailView', '索引日志处理详情', root+'/indexlog/il.ac?method=view&logId='+logId);
}

// 构建处理类型描述
function buildTypeStr(val, row) {
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

//构建处理方式描述
function buildStyleStr(val, row) {
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
		return "自动删除";
		break;
	case "11":
		return "人工合并";
		break;
	case "12":
		return "人工新建";
		break;
	case "13":
		return "人工拆分";
		break;
	case "14":
		return "人工删除";
		break;
	default:
		return "";
		break;
	}
}

// 查询匹配详情
function searchListTable() {
	// 查询的时候重置回第一页
	$("#listTable").datagrid("options").pageNumber = 1;
	$("#listTable").datagrid({
		url:root +"/indexlog/il.ac?method=query",
		queryParams : {
			"opType": $("#search_optype").combobox('getValue'),
			"opStyle": $("#search_opstyle").combobox('getValue'),
			"opTime": $("#search_optime_begin").datetimebox('getValue'),
			"opTimeEnd":$("#search_optime_end").datetimebox('getValue'),
			"infoSour": $("#search_domain").combobox('getValue')
		}
	});
}

// 搜索条件重置
function searchReset() {
	$("#search_optype").combobox('setValue', '');
	$("#search_opstyle").combobox('setValue', '');
	$("#search_domain").combobox('setValue', '');
	$("#search_optime_begin").datetimebox('setValue', '');
	$("#search_optime_end").datetimebox('setValue', '');
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}
