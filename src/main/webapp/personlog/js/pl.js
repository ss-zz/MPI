$(function() {
	// 加载表格数据
	ajaxTable();

	var p = $('#listTable').treegrid('getPager');
	$(p).pagination({
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		pageSize : 10,
		pageList : [ 10, 20, 50, 100 ]
	});

});
// 锁定按钮
function lockBtn(btn) {
	$(btn).unbind('click').removeAttr('onclick');
	$(btn).attr("disabled", true);
}

// 解锁按钮
function unlockBtn(btn, handler) {
	$(btn).bind("click", handler);
	$(btn).attr("disabled", false);
}
/** --------------table------------------* */
/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').treegrid({
		title : '居民操作信息查询',
		toolbar:"#listTable_tb",
        singleSelect:true,//单选
        pagination:true,//分页
		fitColumns:"true",
		loadMsg:'数据加载中,请稍后...',
		idField:"ROW_ID",
		treeField:"DOMAIN_DESC",
		remoteSort:false, 
		showFooter:false,
		striped:true,
		url: root + "/personlog/pl.ac",	
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

function viewMatch(logId){	
	var url = root+'/indexlog/il.ac?method=view&logId='+logId;
    var tabId = "tabId_logDetailView";
    var title = "索引日志处理详情";
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
