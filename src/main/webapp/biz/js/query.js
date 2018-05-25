$(function() {
	// 加载表格数据
	$('#listTable').datagrid({
		title : '业务记录',
		toolbar: "#listTable_tb",
		columns:[[  
			{field:'UNAME',title:'姓名',width:100},
			{field:'DOMAINNAME',title:'数据来源',width:150},
			{field:'BIZSERIALID',title:'就诊流水号',width:100},
			{field:'BIZINPATIENTNO',title:'住院号',width:100},
			{field:'BIZCLINICNO',title:'门诊号',width:100},
			{field:'CREATE_DATE',title:'创建日期',width:100},
			{field:'CONFIGID',title:'操作',width:100,formatter:detail}
		]]
	});
});

/**
 * 加载表格数据
 */
function buildViewLink(val , row){
	var type = row.ROW_TYPE;
	var id = row.ROW_ID;
	if(type == 1){
		return '<a href="#" onclick="unify_viewIndex(\''+id+'\',\''+val+'\')">'+val+'</a>';
	}else{
		return '<a href="#" onclick="unify_viewPerson(\''+id+'\',\''+val+'\')">'+val+'</a>';
	}	
}

function initState(val , row){
	if(val != null){
		if(val == '0'){
			return '新增';
		}
		if(val == '1'){
			return '更新';
		}
		if(val == '2'){
			return '拆分';
		}
	}
	return '';
}

function detail(val , row){
	val = '查看';
	return '<a href=\'#\' onclick=\"unify_viewIndex(\''+row.ID+'\',\''+val+'\')\">'+val+'</a>';
}

// 查看详情
function unify_viewIndex(logId){
	openTab('tabId_mpiDetailView_biz', '索引业务详情', root+'/indexBIZ/view?logId='+logId);
}

// 查询匹配详情
function searchListTable() {
	// 查询的时候重置回第一页
	$("#listTable").datagrid({
		url: root + "/indexBIZ/query",
		queryParams : {
			"bizSystemId" : $("#search_system_id").val(),
			"createDate": $("#search_create_date").datebox('getValue'),
			"bizInpatientSerialno": $("#search_inpatient_no").val(),
			"bizClinicSerialno": $("#search_clinic_serial_no").val(),
			page: 0,
			rows: 10
		}
	});
}

// 刷新
function reloadTable() {
	$('#listTable').treegrid('reload');
}

// 搜索条件重置
function searchReset() {
	$("#search_opstatus").combobox('setValue', '');
	$("#search_system_id").val('');
	$("#search_personIdno").val('');
	$("#search_create_date").datebox('getValue');
	
	$("#search_system_id").val();
	$("#search_personIdno").val();
	$("#search_inpatient_no").val();
	$("#search_clinic_serial_no").val();
}
