$(function() {
	// 加载表格数据
	//ajaxTable();
	$('#listTable').treegrid({
		title : '主索引记录查询',
		toolbar:"#listTable_tb",
        singleSelect:false,//单选
        pagination:true,//分页
		loadMsg:'数据加载中,请稍后...',
		iconCls:"icon-edit",
		width:"98%",
		idField:"ROW_ID",
		treeField:"NAME",
		remoteSort:false, 
		showFooter:false,
		striped:true,
		//queryParams : args,
		//url: root + "/query/query.ac",	
		columns:[[  
			{field:'ck',checkbox:true},  
            {field:'NAME_CN',title:'姓名',width:150,formatter:buildViewLink},
            {field:'GENDER_CD',title:'性别',width:100},
            {field:'BIRTH_DATE',title:'出生日期',width:150},
            {field:'ID_NO',title:'身份证号',width:200},
            {field:'PERSON_TEL_NO',title:'电话号码',width:100},
            //{field:'DOMAIN_DESC',title:'数据来源',width:100},
            {field:'PERSON_COUNT',title:'关联居民数',width:200,formatter:buildRemoveLink},
            {field:'OPERATION',title:'操作',width:115,formatter:spileIndex}
		]],
		onClickRow:function(index, row){
        }
	});
	

	var p = $('#listTable').treegrid('getPager');
	$(p).pagination({
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		pageSize : 10,
		pageList : [ 10, 20, 50, 100 ]
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
	$.messager.alert('消息','拆分成功！');
}

function mergeIndex(){
	var row = $('#listTable').treegrid('getSelections');
	var text = '人工主索引信息合并';
	var str = '';
	if(row.length == 2){
		if(row[0].ID_NO != row[1].ID_NO || row[0].NAME_CN != row[1].NAME_CN || row[0].GENDER_CD != row[1].GENDER_CD){
			str = '主索引基本信息不一致！确定继续合并？';
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
		$.messager.alert('消息','合并失败！请选择出需要合并的两个主索引！');
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
		var text = '人工主索引信息拆分';
		var url = root+'/index/index.ac?method=indexSplitDetail&splitPk='+id;
		$("#dialog_split").dialog({
		    title:text,
		    width:1000,
		    height:450,
		    modal:true,
	   		content : '<iframe name="'+text+'" id="tabId_SplitIndexView" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
		$("#dialog_split").dialog("open"); // 打开dialog
}

//合并居民主索引
/*function mergeIndex(num){
	var row = $('#listTable').treegrid('getSelections');
	var text;
	var retiredPk;
	var survivingPk;
	
	if(row.length == 2){
		if(num == null){
			text = '确定合并主索引？    被合并主索引：【'+ row[0].NAME_CN+'】    目标主索引：【'+row[1].NAME_CN+'】'+'<a href=\"#\" onclick=\"mergeIndex(0);\">调换</a>';
			retiredPk = row[0].MPI_PK
			survivingPk = row[1].MPI_PK
		}
		if(num == 0){
			text = '确定合并主索引？    被合并主索引：【'+ row[1].NAME_CN+'】    目标主索引：【'+row[0].NAME_CN+'】'+'<a href=\"#\" onclick=\"mergeIndex(1);\">调换</a>';
			retiredPk = row[1].MPI_PK
			survivingPk = row[0].MPI_PK
			$(".messager-body").window('close');
		}else{
			text = '确定合并主索引？    被合并主索引：【'+ row[0].NAME_CN+'】    目标主索引：【'+row[1].NAME_CN+'】'+'<a href=\"#\" onclick=\"mergeIndex(0);\">调换</a>';
			retiredPk = row[0].MPI_PK
			survivingPk = row[1].MPI_PK
			$(".messager-body").window('close');
		}
		$.messager.confirm('消息 ',text,function(r){
		    if (r){
		    	$.ajax({
			        async : false,
			        cache : false,
			        type : 'POST',
			        dataType : "json",
			        data : {
						"retiredPk":retiredPk,
						"survivingPk":survivingPk
			        },
			        url : root + '/index/index.ac?method=merge',// 请求的action路径
			        error : function() {// 请求失败处理函数
			            alert('请求失败');
			        },
			        success : function(data) {
			            var messgage = "主索引合并拆分!";
			            if (data == null) {// 未返回任何消息表示添加成功
			                // 刷新表格
			                reloadTable();
			            } else if (data.errorMsg != null) {// 返回异常信息
			                messgage = data.errorMsg;
			            }
			            $.messager.alert('消息',messgage);
			        }
    			});	
		    
		    }
		});
	}else{
		$.messager.alert('消息','合并失败！请选出需要合并的两个主索引！');
	}
}*/



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
	/*var args = {};
    if (params != undefined && $.isPlainObject(params))
        args = params;*/
	// 加载表格
	$('#listTable').treegrid({
		title : '主索引记录查询',
		toolbar:"#listTable_tb",
        singleSelect:true,//单选
        pagination:true,//分页
		loadMsg:'数据加载中,请稍后...',
		iconCls:"icon-edit",
		width:"98%",
		idField:"ROW_ID",
		treeField:"NAME",
		remoteSort:false, 
		showFooter:false,
		striped:true,
		//queryParams : args,
		url: root + "/query/query.ac",	
		columns:[[  
            {field:'NAME_CN',title:'姓名',width:150,formatter:buildViewLink},
            {field:'GENDER_CD',title:'性别',width:100},
            {field:'BIRTH_DATE',title:'出生日期',width:150},
            {field:'ID_NO',title:'身份证号',width:200},
            {field:'PERSON_TEL_NO',title:'电话号码',width:100},
            //{field:'DOMAIN_DESC',title:'数据来源',width:100},
            {field:'PERSON_COUNT',title:'关联居民数',width:200,formatter:buildRemoveLink}
		]],
		onLoadError : function() {
			alert('数据加载失败!');
		},
		onLoadSuccess : function(data) {
			var value = $('#listTable').treegrid('getData')['errorMsg'];
			if (value != null) {
				alert("错误消息:" + value);
			}
		},
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
	//alert(type);
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

//执行拆分方法
function splitPerson(personId,indexId){
	if(!confirm("请确认拆分居民信息"))
		return;
	// 锁定按钮防止重复点击
	lockBtn(this);
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        dataType : "json",
        data : {
			"indexId":indexId,
			"personId":personId
        },
        url : root + '/manual/manual.ac?method=split',// 请求的action路径
        error : function() {// 请求失败处理函数
            alert('请求失败');
        },
        success : function(data) {
            var messgage = "成功拆分!";
            if (data == null) {// 未返回任何消息表示添加成功
                // 刷新表格
                reloadTable();
            } else if (data.errorMsg != null) {// 返回异常信息
                messgage = data.errorMsg;
            }
            alert(messgage);
        }
    });	
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
/*	var personSex = $("#search_personSex").combobox('getValue');*/
	$("#listTable").treegrid({
		queryParams : {
			"NAME_CN" : personName,
			"ID_NO":id_no,
			"BIRTH_DATE":birthdate,
			"STATE":mergeStatus
		/*	"GENDER_DN" : personSex
			
		*/
		},
		url: root + "/query/query.ac",
	});
}

// 搜索条件重置
function searchReset() {
	$("#search_opstatus").combobox('setValue', '');
	$("#search_personName").val('');
	$("#search_personIdno").val('');
	$("#search_personBirthdate").datebox('getValue');
/*	$("#search_personSex").combobox('setValue', '');*/
}
