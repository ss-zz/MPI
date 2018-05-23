$(function() {
	// 加载表格数据
	ajaxTable();
	//初始化详细显示窗口
	setWindow_view();
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

// 构建匹配居民列表查看连接
function buildMatchUrl(val,row){
	var personCount = row.PERSON_COUNT;
	if(personCount>1){
		return '<a href="#" onclick="viewPerson(\''+val+'\');">查看拆分</a>';
	}else{
		return '查看拆分';
	}
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 查询匹配详情
function searchListTable() {
	// 查询的时候重置回第一页
	$("#listTable").datagrid("options").pageNumber = 1; 
	var personName = $("#search_personName").val();
	var personSex = $("#search_personSex").combobox('getValue');
	$("#listTable").datagrid({
		queryParams : {
			"name":personName,
			"sex":personSex
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
 * 匹配信息显示窗口定义
 */
function setWindow_view(){
	$('#window_view').window({
		width:800,
		height:500
	});
}

function closeWindow_view(){
	$("#window_view").window('close');
	cleanIndexInfo();
}

function viewPerson(indexId){
	$("#selected_indexId").val(indexId);
	//设置窗口页面索引信息显示
	showIndexInfo(indexId);
	// 加载表格
	$('#detailTable').datagrid({
		toolbar :"#detailTable_toolbar",
		queryParams : {// 查询条件
			"indexId":indexId
		}
	}).datagrid('acceptChanges');
	
	$("#window_view").window('open');
}

function showIndexInfo(indexId){
	//取得索引记录
	var datas = $("#listTable").datagrid("getRows");
	for(var i=0;i<datas.length;i++){
		var row = datas[i];
		if(row.INDEX_ID == indexId){
			$("#index_name_show").html(row.NAME);
			$("#index_sex_show").html(row.sexName);
			$("#index_birthday_show").html(row.BIRTH_DATE);
			$("#index_idcard_show").html(row.ID_CARD_NUM);
			$("#index_phone_show").html(row.PHONE_ONE);
			break;
		}
	}

}

function cleanIndexInfo(){
	$("#index_name_show").html("");
	$("#index_sex_show").html("");
	$("#index_birthday_show").html("");
	$("#index_idcard_show").html("");
	$("#index_phone_show").html("");
}

// 刷新居民信息显示表格
function reloadDetailTable() {
	$('#detailTable').datagrid('reload');
}

// 执行拆分方法
function splitPerson(){
	var row = $('#detailTable').datagrid('getSelected');
	if(row == undefined || row == null){
		alert("请选择要拆分的居民信息");
		return;
	}
	if(!confirm("请确认拆分居民信息"))
		return;
	//取得选中的居民id
	var personId = row.PERSON_ID;
	//取得选中的索引id
	var indexId = $("#selected_indexId").val();
	$.ajax({
		url : root + '/manual/manual.ac?method=split',
		type : 'POST',
		data : {
			"indexId":indexId,
			"personId":personId
		},
		success : function(data) {
			showMessage("拆分成功");
			reloadTable();
			reloadDetailTable();
		}
	});
}
