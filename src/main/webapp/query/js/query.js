$(function() {
	// 加载表格数据
	$('#listTable').treegrid({
		title : '主索引记录',
		toolbar: "#listTable_tb",
		idField: "ROW_ID",
		singleSelect:false,
		treeField: "NAME",
		fitColunms: true,
		columns:[[  
			{field:'ck',checkbox:true},  
			{field:'NAME_CN',title:'姓名',width:150,formatter:buildViewLink},
			{field:'GENDER_CD',title:'性别',width:100},
			{field:'BIRTH_DATE',title:'出生日期',width:150},
			{field:'ID_NO',title:'身份证号',width:200},
			{field:'PERSON_TEL_NO',title:'电话号码',width:100},
			{field:'PERSON_COUNT',title:'关联居民数',width:200,formatter:buildRemoveLink},
			{field:'OPERATION',title:'操作',width:115,formatter:spileIndex}
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

// 合并主索引
function mergeIndex(){
	var row = $('#listTable').treegrid('getSelections');
	var text = '人工主索引信息合并';
	var str = '';
	if(row.length == 2){
		if(row[0].ID_NO != row[1].ID_NO || row[0].NAME_CN != row[1].NAME_CN || row[0].GENDER_CD != row[1].GENDER_CD){
			str = '主索引基本信息不一致，确定继续合并？';
		}
		var url = root+'/index/index.ac?method=indexDetail&retiredPk='+row[0].MPI_PK+'&survivingPk='+row[1].MPI_PK;
		if(str.length > 0){
			$.messager.confirm('消息 ',str,function(r){
				if (r){
					$("#dialog").dialog({
						title:text,
						width:1000,
						height:450,
						content : '<iframe name="'+text+'" id="tabId_IndexView" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
					});
					$("#dialog").dialog("open"); 
				}
			});
		}else{
			$("#dialog").dialog({
				title:text,
				width:1000,
				height:450,
				content : '<iframe name="'+text+'" id="tabId_IndexView" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
			});
			$("#dialog").dialog("open");
		}
		
	}else{
		$.messager.alert('消息','请选择需要合并的两个主索引');
	}
}

//拆分主索引
function spileIndex(val , row){
	var type = row.MERGESTATUS;
	var id = row.MPI_PK;
	if(type != null){
		return '<a href="#" onclick="spile_Index(\''+id+'\')">人工拆分居民主索引</a>';
	}
}

function spile_Index(id){
	var url = root+'/index/index.ac?method=indexSplitDetail&splitPk='+id;
	$("#dialog_split").dialog({
		title: '人工主索引信息拆分',
		width:1000,
		height:450,
		content : '<iframe name="'+text+'" id="tabId_SplitIndexView" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
	});
	$("#dialog_split").dialog("open");
}

/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').treegrid({
		title : '主索引记录查询',
		toolbar:"#listTable_tb",
		iconCls:"icon-edit",
		idField:"ROW_ID",
		treeField:"NAME",
		url: root + "/query/query.ac",
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
				var personName = $("#search_personName").val();
				var id_no = $("#search_personIdno").val();
				var birthdate  = $("#search_personBirthdate").datebox('getValue');
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
	openTab('tabId_splitToIndex', '选择目标索引', root+'/query/query.ac?method=toQueryIdx&personId='+personId+'&indexId='+indexId);
}

//执行拆分方法
function splitPerson(personId,indexId){
	confirm("请确认拆分居民信息", function(){
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
			}
		});
	})
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
	var id_no = $("#search_personIdno").val();
	var birthdate = $("#search_personBirthdate").datebox('getValue');
	var mergeStatus = $('#search_mergeStatus').combobox('getValue'); 
	$("#listTable").treegrid({
		url: root + "/query/query.ac",
		queryParams : {
			"NAME_CN" : personName,
			"ID_NO":id_no,
			"BIRTH_DATE":birthdate,
			"STATE":mergeStatus,
			page: 1,
			rows: 10
		}
	});
}

// 搜索条件重置
function searchReset() {
	$("#search_opstatus").combobox('setValue', '');
	$("#search_personName").val('');
	$("#search_personIdno").val('');
	$("#search_personBirthdate").datebox('getValue');
}
