var INUSED_FIELDS={};

$(function() {
	// 加载表格数据
	ajaxTable();
    
    var p = $('#listTable').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',
        afterPageText: '页    共 {pages} 页',    
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',   
        pageSize:10,
        showPageList:false
        
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
	// 加载表格
	$('#listTable').datagrid({
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
function openAddPage(){
	var url = root+'/domainsrclevel/srclevel.ac?method=toAdd';
	var name = 'iframe_tabId_srclevelAdd';	
	//如果当前id的tab不存在则创建一个tab
	if(parent.$("#tabId_srclevelAdd").html()==null){
		parent.$('#centerTab').tabs('add',{
			title: '添加数据源级别配置',         
			closable:true,
			cache : false,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'" id="tabId_srclevelAdd" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}else{
		parent.$('#centerTab').tabs('select','添加数据源级别配置');
	}
}
function openEditPage(){
	
	var row = $('#listTable').datagrid('getSelected');
	if(row==undefined || row==null){
	        alert("请选择要修改的行!");
	        return;
	}    
	var id = row.ID;
	var url = root+'/domainsrclevel/srclevel.ac?method=toAdd&ID='+id;
	var name = 'iframe_tabId_srclevelEdit';	
	//如果当前id的tab不存在则创建一个tab
	if(parent.$("#tabId_srclevelEdit").html()==null){
		parent.$('#centerTab').tabs('add',{
			title: '修改数据源级别配置',         
			closable:true,
			cache : false,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'" id="tabId_srclevelEdit" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}else{
		parent.$('#centerTab').tabs('select','修改数据源级别配置');
	}
}
//删除
function removeData(){
    var row = $('#listTable').datagrid('getSelected');
    if(row==undefined || row==null){
        alert("请选择要删除的行!");
        return;
    }     
    var id = row.ID;
    var field_name = row.FIELD_DESC;
    if(confirm("确认要删除:"+row.FIELD_DESC+"么?")){
	    $.ajax({
	        async : false,
	        cache : false,
	        type : 'POST',
	        dataType : "json",
	        data : {
	            "ID":id            
	        },
	        url : root + '/domainsrclevel/srclevel.ac?method=del',// 请求的action路径
	        error : function() {// 请求失败处理函数
	            alert('请求失败');
	        },
	        success : function(data) {
	            var messgage = "删除成功!";
	            if (data == null) {// 未返回任何消息表示添加成功
	                // 刷新列表
	                reloadTable();
	            } else if (data.errorMsg != null) {// 返回异常信息
	                messgage = data.errorMsg;
	            }
	            alert(messgage);
	        }
	    });        
    }
}



