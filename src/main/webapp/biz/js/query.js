$(function() {
	// 加载表格数据
	$('#listTable').treegrid({
		title : '业务记录',
		toolbar: "#listTable_tb",
		loadMsg: '数据加载中,请稍后...',
		idField: "ROW_ID",
		singleSelect:false,
		treeField: "NAME",
		fitColunms: true,
		columns:[[  
			{field:'ck',checkbox:true},  
			{field:'bizPatientId',title:'患者姓名',width:100},
			{field:'bizSystemId',title:'业务来源',width:150},
			{field:'bizSerialId',title:'患者就诊流水号',width:100},
			{field:'bizInpatientSerialno',title:'患者住院流水号',width:100},
			{field:'create_Date',title:'创建日期',width:100},
			{field:'state',title:'状态',width:100},
			{field:'remark',title:'备注',width:100}
		]]
	});
	
});

//锁定按钮
function lockBtn(btn){
	$(btn).unbind('click').removeAttr('onclick');
	$(btn).attr("disabled",true);  
}

// 解锁按钮
function unlockBtn(btn,handler){
	$(btn).bind("click",handler);
	$(btn).attr("disabled",false);	  
}
/** --------------table------------------* */
/**
 * 加载表格数据
 */
function ajaxTable() {
	alert(111);
	// 加载表格
	$('#listTable').treegrid({
		title : '业务记录查询',
		toolbar:"#listTable_tb",
		iconCls:"icon-edit",
		idField:"ROW_ID",
		treeField:"NAME",
		url: root + "/indexBIZ/query",	
		columns:[[  
			{field:'NAME_CN',title:'姓名',width:150,formatter:buildViewLink},
			{field:'GENDER_CD',title:'性别',width:100},
			{field:'BIRTH_DATE',title:'出生日期',width:150},
			{field:'ID_NO',title:'身份证号',width:200},
			{field:'PERSON_TEL_NO',title:'电话号码',width:100},
			{field:'PERSON_COUNT',title:'关联居民数',width:200,formatter:buildRemoveLink}
		]],
		onBeforeLoad : function(row, param) {
			if (row) {
				$(this).treegrid('options').url =  root + '/query/query.ac?method=listPerson&indexId='+row.INDEX_ID;
			} else {
				$(this).treegrid('options').url =  root + '/query/query.ac';
				var personName = $("#search_system_id").val();
				var id_no = $("#search_personIdno").val();
				var birthdate  = $("#search_create_date").datebox('getValue');
				var personSex = $("#search_personSex").combobox('getValue');
				param = {
					"NAME_CN" : personName,
					"ID_NO" : id_no,
					"BIRTH_DATE":birthdate,
					"GENDER_CD" : personSex
				};
			}
			return true;
		},
		onContextMenu : function(e, row) {
			e.preventDefault();
			$(this).treegrid('unselectAll');
			$(this).treegrid('select', row.code);
		}
	});
}

function buildViewLink(val , row){
	var type = row.ROW_TYPE;
	var id = row.ROW_ID;
	if(type == 1){
		return '<a href="#" onclick="unify_viewIndex(\''+id+'\',\''+val+'\')">'+val+'</a>';
	}else{
		return '<a href="#" onclick="unify_viewPerson(\''+id+'\',\''+val+'\')">'+val+'</a>';
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

	var search_system_id = $("#search_system_id").val();
	var search_create_date = $("#search_create_date").datebox('getValue');
	var search_inpatient_no = $("#search_inpatient_no").val();
	var search_clinic_serial_no = $("#search_clinic_serial_no").val();
	
	//var birthdate = $("#search_create_date").datebox('getValue');
	//var mergeStatus = $('#search_mergeStatus').combobox('getValue'); 
	$("#listTable").treegrid({
		queryParams : {
			"bizSystemId" : search_system_id,
			"createDate":search_create_date,
			"bizInpatientSerialNo":search_inpatient_no,
			"bizClinicSerialNo":search_clinic_serial_no,
			page: 0,
			rows: 10
		},
		url: root + "/indexBIZ/query",
	});
	$('#listTable').treegrid('loadData');
	  //$('#listTable').treegrid('loadData',data);
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
