$(function() {
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

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 查询
function searchListTable() {   
	// 查询的时候重置回第一页
	$("#listTable").datagrid("options").pageNumber = 1; 
    
	$("#listTable").datagrid({
		queryParams : {
            "name":$("#search_personName").val(),
            "sex":$("#search_personSex").combobox('getValue'),
            "birthDate":$("#search_birthDate").val(),
            "idCardNum":$("#search_idCardNum").val()
		}
	});
}

// 搜索条件重置
function searchReset(){
	$("#search_personName").val("");
	$("#search_personSex").combobox('setValue','');
	$("#search_birthDate").val("");
	$("#search_idCardNum").val("");
}

function splitToSelectIndex(){
    // 取得选定行
    var row = $('#listTable').datagrid('getSelected');
    if(row == undefined || row == null || row == ""){
        alert("请先选择索引!");
        return;
    }
    var indexId = row.INDEX_ID;
    var personId = $("#personId").val();
    var fromIndexId = $("#fromIndexId").val();
    
    var url = root+'/query/query.ac?method=toCompare&personId='+personId+'&indexId='+indexId+'&fromIndexId='+fromIndexId;
    var tabId = "tabId_splitPersonAndIndexComp";
    var title = "拆分至索引明细对比";
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

