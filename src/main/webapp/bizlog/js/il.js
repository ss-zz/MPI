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
	$('#search_optime_begin').datetimebox('setValue', year+"-01-01 00:00:00");
	$('#search_optime_end').datetimebox('setValue', year+"-"+month+"-"+day + " 23:59:59");
});

//初始化列 --日志类型
function initType(val, row){
	if(val == '1'){
		return '匹配';
	}
}
//初始化列 --百分比
function initMatched(val, row){
	if(val != null){
		return val+'%'
	}
}

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
	
	$('#blTime_begin').datetimebox(option);
	$('#blTime_end').datetimebox(option);
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

function timestampToTime(timestamp) {
	var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes() + ':';
	s = date.getSeconds();
	return Y+M+D+h+m+s;
}

//构建索引查看连接
function buildMatchUrl(val, row) {
	var logId = row.BLIDXLOGID;
	return '<a href="#" onclick="viewMatch(\'' + logId + '\');">查看</a>';
}

function viewMatch(logId){
	openTab('tabId_logDetailView_biz', '业务索引日志处理详情', root+'/bizLog/view?logId='+logId);
}

// 构建操作类型描述
function buildTypeStr(val, row) {
	switch (val) {
	case "1":
		return "匹配";
		break;
	default:
		return "";
		break;
	}
}

//构建操作类型描述
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

// 查询匹配详情
function searchListTable() {
	// 查询的时候重置回第一页
	$("#listTable").datagrid("options").pageNumber = 1;
	$("#listTable").datagrid({
		url:root +"/bizLog/query",
		queryParams : {
			"blInfoSour" : $('#blInfoSour').val(),
			"blTimeBegin":$("#search_optime_begin").datetimebox('getValue'),
			"blTimeEnd":$("#search_optime_end").datetimebox('getValue'),
			"blMatchedBegin":$('#blMatched_begin').val(),
			"blMatchedEnd":$('#blMatched_end').val(),
			"blInfoSour": $("#search_domain").combobox('getValue')
		}
	});
}

// 搜索条件重置
function searchReset() {
	$("#blMatched_begin").val('');
	$("#blMatched_end").val('');
	$("#search_optime_begin").datetimebox('setValue', '');
	$("#search_optime_end").datetimebox('setValue', '');
	$("#search_domain").combobox('setValue', '');	
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 将匹配度转化为百分数
function matchDegreeToPercent(degree) {
	if (isNumeric(degree)) {
		var degreeNum = parseFloat(degree);
		return (Math.round(degreeNum * 100 * 10000) / 10000) + "%";
	} else {
		return degree;
	}
}

// 判断字符串是否是数字
function isNumeric(str) {
	return (str.search(/^[\+\-]?\d+\.?\d*$/) == 0);
}
