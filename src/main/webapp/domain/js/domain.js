$(function() {
	// 加载表格数据
	ajaxTable();
	// 设置分页条属性
	var p = $('#listTable').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',
        afterPageText: '页    共 {pages} 页',    
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',   
        pageSize:10,
        pageList:[10,20,50,100]
    });
	// 初始化弹出层
	setDialog_add();
	closeDialog_add();
	setDialog_edit();
	closeDialog_edit();
});

/** --------------table------------------* */
/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').datagrid({
		/*toolbar : [{// 正上方工具栏
			text : '添加身份域',
			iconCls : 'icon-add',
			handler : function() {
				// 点击工具栏运行的js方法
				openDialog_add();
			}
		}, '-', {
			text : '编辑身份域',
			iconCls : 'icon-edit',
			handler : function() {
				openDialog_edit();
			}
		}, '-', {
			text : '删除身份域',
			iconCls : 'icon-edit',
			handler : function() {
				removeData();
			}
		},'-',{
			text : '域字段数据源级别配置',
			iconCls : 'icon-add',
			handler : function(){
				openAddPage();	
			}
		}],*/
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
		onLoadSuccess : function() {
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

function convertBookType(val,row){
	if(val == "0"){
		return "推送";
	}else{
		return "拉取";
	}
}

/** --------------添加操作弹出框------------------* */
// 设置弹出框的属性
function setDialog_add() {
	$('#add').dialog({
		title : '身份域添加',
		modal : true, // 模式窗口：窗口背景不可操作
		collapsible : true, // 可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true, // 可拖动边框大小
		onClose : function() { // 继承自panel的关闭事件
			addReset();
		}
	});
}
function addReset() {
	$("#add_domainType").val("");
	$("#add_domainDesc").val("");
	$("#add_uniqueSign").val("");
	$("#add_pushAddr").val("");
	$("#add_bookType").val("0");
    $("#add_domainLevel").val("");
}
// 打开对话框
function openDialog_add() {
	$('#add').dialog('open');
}
// 关闭对话框
function closeDialog_add() {
	$('#add').dialog('close');
}
// 执行用户添加操作
function addData() {
	var validateResult = true;
	// easyui 表单验证
	$('#table_add input').each(function() {
		if ($(this).attr('required') || $(this).attr('validType')) {
			if (!$(this).validatebox('isValid')) {
				// 如果验证不通过，则返回false
				validateResult = false;
				return;
			}
		}
	});
	if (validateResult == false) {
		return;
	}

	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		data : {
            "DOMAIN_TYPE":$("#add_domainType").val(),
            "DOMAIN_DESC":$("#add_domainDesc").val(),
            "UNIQUE_SIGN":$("#add_uniqueSign").val(),
            "PUSH_ADDR":$("#add_pushAddr").val(),
            "BOOK_TYPE":$("#add_bookType").val(),
            "DOMAIN_LEVEL":$("#add_domainLevel").val()
	    },
		url : root + '/domain/domain.ac?method=add',// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) {
			var messgage = "添加成功!";
			if (data == null) {// 未返回任何消息表示添加成功
				addReset();
				// 刷新列表
				reloadTable();
			} else if (data.errorMsg != null) {// 返回异常信息
				messgage = data.errorMsg;
			}
			$("#add_message").html(messgage);
		}
	});
}

/** --------------编辑操作弹出框------------------* */
// 设置弹出框的属性
function setDialog_edit() {
	$('#edit').dialog({
		title : '身份域编辑',
		modal : true, // 模式窗口：窗口背景不可操作
		collapsible : true, // 可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true // 可拖动边框大小
	});
}
// 打开对话框
function openDialog_edit() {
    editReset();
    
	var row = $('#listTable').datagrid('getSelected');
    if(row==undefined || row==null){
        alert("请选择要修改的身份域!");
        return;
    }    
    var domainId = row.DOMAIN_ID;
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        dataType : "json",
        data : {
            "domainId":domainId
        },
        url : root + '/domain/domain.ac?method=load',// 请求的action路径
        error : function() {// 请求失败处理函数
            alert('请求失败');
        },
        success : function(data) {
            if (data == null) {// 未返回任何消息表示添加成功
                alert('请求失败');
            }else{
                $("#edit_domainId").val(data.DOMAIN_ID);
	            $("#edit_domainType").val(data.DOMAIN_TYPE);
	            $("#edit_domainDesc").val(data.DOMAIN_DESC);
	            $("#edit_uniqueSign").val(data.UNIQUE_SIGN);
	            $("#edit_pushAddr").val(data.PUSH_ADDR);
	            $("#edit_bookType").val(data.BOOK_TYPE);
                $("#edit_domainLevel").val(data.DOMAIN_LEVEL);
            }
        }
    });
    $('#edit').dialog('open');
}
// 关闭对话框
function closeDialog_edit() {
	$('#edit').dialog('close');
}
// 根据用户id查询用户的信息
function editReset() {
    $("#edit_domainId").val("");
    $("#edit_domainType").val("");
    $("#edit_domainDesc").val("");
    $("#edit_uniqueSign").val("");
    $("#edit_pushAddr").val("");
    $("#edit_bookType").val("0"); 
    $("#edit_domainLevel").val();
}
// 执行用户编辑操作
function editData() {
	var validateResult = true;
	// easyui 表单验证
	$('#table_edit input').each(function() {
		if ($(this).attr('required') || $(this).attr('validType')) {
			if (!$(this).validatebox('isValid')) {
				// 如果验证不通过，则返回false
				validateResult = false;
				return;
			}
		}
	});
	if (validateResult == false) {
		return;
	}

	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		data : {
            "DOMAIN_ID":$("#edit_domainId").val(),
            "DOMAIN_TYPE":$("#edit_domainType").val(),
            "DOMAIN_DESC":$("#edit_domainDesc").val(),
            "UNIQUE_SIGN":$("#edit_uniqueSign").val(),
            "PUSH_ADDR":$("#edit_pushAddr").val(),
            "BOOK_TYPE":$("#edit_bookType").val(),
            "DOMAIN_LEVEL":$("#edit_domainLevel").val()
		},
		url : root + '/domain/domain.ac?method=edit',// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) {
			var messgage = "修改成功!";
			if (data == null) {// 未返回任何消息表示添加成功
				// 刷新列表
				reloadTable();
			} else if (data.errorMsg != null) {// 返回异常信息
				messgage = data.errorMsg;
			}
			$("#edit_message").html(messgage);
		}
	});
}

/** --------------编辑操作弹出框------------------* */
//删除用户方法
function removeData(){
    var row = $('#listTable').datagrid('getSelected');
    if(row==undefined || row==null){
        alert("请选择要删除的身份域!");
        return;
    }     
    var domainId = row.DOMAIN_ID;
    if(confirm("确认要删除身份域:"+row.DOMAIN_DESC+"么?")){
	    $.ajax({
	        async : false,
	        cache : false,
	        type : 'POST',
	        dataType : "json",
	        data : {
	            "DOMAIN_ID":domainId            
	        },
	        url : root + '/domain/domain.ac?method=del',// 请求的action路径
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
$.extend($.fn.validatebox.defaults.rules, {
    uniqueSign : {
        validator : function(value, param) {  
            if(value.length<5){
                return false;
            }
            
            var domainId = "";
            if(param[1]!=undefined && param[1]!=null && param[1]!=""){
                domainId = $(param[1]).val();
            }
            var result = $.ajax({
                        url : param[0],
                        data : {
                            "DOMAIN_ID":domainId,
                            "UNIQUE_SIGN":value
                        },
                        type : 'post',
                        dataType : 'json',
                        async : false,
                        cache : false
                    }).responseText;
            if (result == 'false') {
                return false;
            } else {
                return true;
            }
        },
        message : '标识已存在或长度过短(5位以上)!'
    },
    isInteger : {
        validator : function(value, param) {  
            var regex = /^\d+$/;
            return regex.test(value);
        },
        message : '必须为自然数.'    
    }
});

//创建操作连接
function buildOptLink(val,row){
	var link = '';
	var unique_sign=row.UNIQUE_SIGN
	link +='<a href="#" onclick="openViewPage(\''+unique_sign+'\')">域字段数据源级别</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return link;
}
function openAddPage(){
	var row = $('#listTable').datagrid('getSelected');
    if(row==undefined || row==null){
        alert("请选择要配置的身份域!");
        return;
    }    
    var domainId = row.UNIQUE_SIGN;
    
	var url = root+'/domainsrclevel/srclevel.ac?method=toAdd&domainId='+domainId;
	
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

function openViewPage(val){
	//var url = root+'/srclevel/page/srclevel.jsp?domainid='+val;
	var url = root+'/domainsrclevel/srclevel.ac?method=toAdd&domainId='+val;
	var name = 'iframe_tabId_domainfieldlevel';	
	//如果当前id的tab不存在则创建一个tab
	if(parent.$("#tabId_domainfieldlevel").html()==null){
		parent.$('#centerTab').tabs('add',{
			title: '域字段数据源级别',         
			closable:true,
			cache : false,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'" id="tabId_domainfieldlevel" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}else{
		parent.$('#centerTab').tabs('select','域字段数据源级别');
	}
}
