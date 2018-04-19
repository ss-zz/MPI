$(function() {	
	
	// 搜索框初始化
	$('#search_opstatus').combobox({
		width:100
	});
	// 加载表格数据
	ajaxTable();
    
    var p = $('#listTable').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',
        afterPageText: '页    共 {pages} 页',    
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',   
        pageSize:10,
        pageList:[10,20,50,100]
    });
});
// 锁定按钮
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
	$('#listTable').datagrid({
		toolbar:"#listTable_tb",
        singleSelect:true,//单选
        pagination:true,//分页
		loadMsg : '数据加载中,请稍后...',
		onLoadError : function() {
			alert('数据加载失败!');
		},
		queryParams : {// 查询条件
		},
		onClickRow : function(rowIndex, rowData) {
			// 取消选择某行后高亮
			$('#listTable').datagrid('unselectRow', rowIndex);
		},
		onLoadSuccess : function(data) {
			var value = $('#listTable').datagrid('getData')['errorMsg'];
			if (value != null) {
				alert("错误消息:" + value);
			}
		}
	}).datagrid('acceptChanges');
}

// 查询匹配详情
function searchListTable() {   
	// 查询的时候重置回第一页
	$("#listTable").datagrid("options").pageNumber = 1; 
	
	var type = $("#search_opstatus").combobox('getValue');
	var personName = $("#search_personName").val();
	var personSex = $("#search_personSex").combobox('getValue');
	
	$("#listTable").datagrid({
		queryParams : {
            "MAN_OP_STATUS":type,
            "FIELD_PK":personName,
            "MAN_OP_ID":personSex
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
 * 构建居民信息查看连接
 */
function buildPersonView(val,row){
	var id = row.FIELD_PK;
	return '<a href="#" onclick="unify_viewPerson(\''+id+'\',\''+val+'\');">'+val+'</a>';
}

// 构建匹配索引列表查看
function buildMatchUrl(val,row){
    var opStatus = row.MAN_OP_STATUS;
    var opId = row.MAN_OP_ID;
    if(opStatus == "0"){
        return '<a href="#" onclick="viewMatch(\''+val+'\',\''+opId+'\');">查看匹配</a>';
    }else{
        return '';
    }
}

// 构建操作类型描述
function buildTypeStr(val,row){
    switch (val) {
    case "0":
        return "未操作";
        break;
    case "1":
        return "已操作";        
        break;
    default:
        return "未知状态";
        break;
    }
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}


function viewMatch(personId,opId){
	console.dir(personId+"=personId");
	console.dir(opId+"=opId");
	var url = root+'/manual/manual.ac?method=toMatch&personId='+personId+'&opId='+opId;
    var tabId = "tabId_manualMatch";
    var title = "查看匹配结果";
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

// 将匹配度转化为百分数
function matchDegreeToPercent(degree){
	if(isNumeric(degree)){
		var degreeNum = parseFloat(degree);
		
		return (Math.round(degreeNum*100*100)/100)+"%";
	}else{
		return degree;
	}
}

// 判断字符串是否是数字
function isNumeric(str){ 
        return (str.search(/^[\+\-]?\d+\.?\d*$/)==0);
}