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
			{field:'bizInpatientSerialNo',title:'患者住院流水号',width:100},
			{field:'createDate',title:'创建日期',width:100},
			{field:'state',title:'状态',width:100},
			{field:'remark',title:'备注',width:100}
		]]
	});
	
});

function d_close(){
 	reloadTable();
	$('#dialog').dialog('close');
	$.messager.alert('消息','合并成功！');
}

function split_close(){
	reloadTable();
	$('#dialog_split').dialog('close');
	$.messager.alert('消息','拆分成功');
}

// 合并业务
function mergeIndex(){
	var row = $('#listTable').treegrid('getSelections');
	var text = '人工业务信息合并';
	var str = '';
	if(row.length == 2){
		if(row[0].ID_NO != row[1].ID_NO || row[0].NAME_CN != row[1].NAME_CN || row[0].GENDER_CD != row[1].GENDER_CD){
			str = '业务基本信息不一致，确定继续合并？';
		}
		var url = root+'/index/index.ac?method=indexDetail&retiredPk='+row[0].MPI_PK+'&survivingPk='+row[1].MPI_PK;
		if(str.length > 0){
			$.messager.confirm('消息 ',str,function(r){
				if (r){
					$("#dialog").dialog({
						title:text,
						width:1000,
						height:450,
						modal:true,
						content : '<iframe name="'+text+'" id="tabId_IndexView" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
					});
					$("#dialog").dialog("open"); // 打开dialog
				}
			});
		}else{
			$("#dialog").dialog({
				title:text,
				width:1000,
				height:450,
				modal:true,
				content : '<iframe name="'+text+'" id="tabId_IndexView" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
			});
			$("#dialog").dialog("open"); // 打开dialog
		}
		
	}else{
		$.messager.alert('消息','请选择需要合并的两个业务');
	}
}
//拆分业务
function spileIndex(val , row){
	var type = row.MERGESTATUS;
	var id = row.MPI_PK;
	if(type != null){
		return '<a href="#" onclick="spile_Index(\''+id+'\')">人工拆分居民业务</a>';
	}
}


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

/**
 * 建立拆分连接
 * @param val
 * @param row
 */
function buildRemoveLink(val , row){
	var type = row.ROW_TYPE;
	if(type == 1){
		return val;
	}else{
		var count = $('#listTable').treegrid("getParent",row.ROW_ID).PERSON_COUNT;
		var personId = row.PERSON_ID;   
		var indexId = $('#listTable').treegrid("getParent",row.ROW_ID).ROW_ID;
		var html = '<a href="#" onclick="splitPersonToExistIndex(\''+personId+'\',\''+indexId+'\')">关联到其他索引</a>';
		if(count < 2){
			return html;
		}else{
			
			html = '<a href="#" onclick="splitPerson(\''+personId+'\',\''+indexId+'\')">拆分并新建</a>&nbsp;&nbsp;'+html;
			return html;
		}
		
	}

}

/**
 * 将居民拆分至已存在索引
 * @param {String} personId
 * @param {String} indexId
 */
function splitPersonToExistIndex(personId,indexId){
	var url = root+'/query/query.ac?method=toQueryIdx&personId='+personId+'&indexId='+indexId;
	var tabId = "tabId_splitToIndex";
	var title = "选择目标索引";
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
			page: 1,
			rows: 10
		},
		url: root + "/indexBIZ/query",
	});
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
