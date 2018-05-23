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
			{field:'UNAME',title:'患者姓名',width:100},
			{field:'DOMAINNAME',title:'业务来源',width:150},
			{field:'BIZCLINICSERIALNO',title:'患者就诊流水号',width:100},
			{field:'BIZINPATIENTSERIALNO',title:'患者住院流水号',width:100},
			{field:'CREATE_DATE',title:'创建日期',width:100},
			{field:'STATE',title:'状态',width:100,formatter:initState},
			{field:'REMARK',title:'备注',width:100},
			{field:'CONFIGID',title:'操作',width:100,formatter:detail}
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

function unify_viewIndex(logId){
	var url = root+'/indexBIZ/view?logId='+logId;
	var tabId = "tabId_mpiDetailView";
	var title = "索引业务详情";
	var name = 'iframe_'+tabId; 
	//如果当前id的tab不存在则创建一个tab
	if(parent.$("#"+tabId).html()==null){
		parent.$('#centerTab').tabs('add',{
			title: title,		 
			closable:true,
			cache : false,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'" id="'+tabId+'" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}else{
		var tabs = parent.$('#centerTab').tabs('tabs');
		for(var i = 0 ; i < tabs.length ; i++){
			var tab = tabs[i];
			if(tab.panel("options").title == title){
				parent.$('#centerTab').tabs('update',{
					tab: tab,
					options:{
						content : '<iframe name="'+name+'" id="'+tabId+'" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
					}
				});
				parent.$('#centerTab').tabs('select',title);
				break;
			}
		}
	}
}


// 查询匹配详情
function searchListTable() {
	// 查询的时候重置回第一页
	$("#listTable").treegrid("options").pageNumber = 1;

	var search_system_id = $("#search_system_id").val();
	var search_create_date = $("#search_create_date").datebox('getValue');
	var search_inpatient_no = $("#search_inpatient_no").val();
	var search_clinic_serial_no = $("#search_clinic_serial_no").val();
	$("#listTable").treegrid({
		queryParams : {
			"bizSystemId" : search_system_id,
			"createDate":search_create_date,
			"bizInpatientSerialno":search_inpatient_no,
			"bizClinicSerialno":search_clinic_serial_no,
			page: 0,
			rows: 10
		},
		url: root + "/indexBIZ/query",
	});
}

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
