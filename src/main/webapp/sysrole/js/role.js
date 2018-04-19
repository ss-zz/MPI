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
    setWindow_view();
    setDialog_add();
    setDialog_edit();
});


/**
 * 初始化页面列表
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').datagrid({
		toolbar:[{
			text : '添加系统角色',
			iconCls : 'icon-add',
			handler : function() {
				// 点击工具栏运行的js方法
				openDialog_add();
			}			
		},"-",{
			text : '修改系统角色',
			iconCls : 'icon-edit',
			handler : function() {
				// 点击工具栏运行的js方法
				openDialog_edit();
			}	
		}],
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

/**
 * 页面列表刷新方法
 */
function reloadTable() {
	$('#listTable').datagrid('reload');
}

/**
 * 用户管理列 forrmatter
 * @param val 字段值
 * @param row 行值
 */
function buildViewUserLink(val,row){
	return '<a href="#" onclick="ajaxUserTable(\''+val+'\');">查看用户</a>';
}


/**
 * 初始化用户列表显示窗口
 */
function setWindow_view(){
    $('#window_view_user').window({  
        width:800,  
        height:500,  
        modal:true, // 模态
        closed:true, // 初始关闭
        collapsible:false, // 不可卷起
        minimizable:false, // 不可最小化
        maximizable:true, // 可以最大化
        closable:true, //可以关闭
        draggable:false, // 不可拖拽
        resizable:false // 不可改变大小        
    });     
}

function closeWindow_view(){
	$("#current_sysrole_id").val(""); 
	$("#window_view_user").window('close');
}

/**
 * 初始化用户列表
 * @param roleId 角色id
 */
function ajaxUserTable(roleId) {
	$("#current_sysrole_id").val(roleId);
	// 加载表格
	$('#userListTable').datagrid({
        singleSelect:true,//单选
        pagination:true,//分页
		loadMsg : '数据加载中,请稍后...',
		onLoadError : function() {
			alert('数据加载失败!');
		},
		queryParams : {// 查询条件
			"sysRoleId":roleId
		},
		onClickRow : function(rowIndex, rowData) {
			// 取消选择某行后高亮
			$('#userListTable').datagrid('unselectRow', rowIndex);
		},
		onLoadSuccess : function() {
			var value = $('#userListTable').datagrid('getData')['errorMsg'];
			if (value != null) {
				alert("错误消息:" + value);
			}
			$("#window_view_user").window('open');
		}
	}).datagrid('acceptChanges');
	
	
}

/**
 * 用户列表刷新方法
 */
function reloadUserTable() {
	$('#userListTable').datagrid('reload');
}


/*----------------添加用户定义方法--------------*/

/**
 * 设置添加窗口
 */
function setDialog_add() {
	$('#add').dialog({
		title : '系统角色添加',
		modal : true, // 模式窗口：窗口背景不可操作
		collapsible : true, // 可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true, // 可拖动边框大小
		closed:true, // 初始关闭
		onClose : function() { // 继承自panel的关闭事件
			addReset();
		}
	});
}

/**
 * 重置添加对话框内容
 */
function addReset() {
	$("#add_roleName").val("");
}

/**
 * 打开添加对话框
 */
function openDialog_add() {
	$('#add').dialog('open');
}

/**
 * 关闭添加对话框
 */
function closeDialog_add() {
	$('#add').dialog('close');
}

/**
 * 执行添加动作
 */
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
	       "roleName":$("#add_roleName").val()
	    },
		url : root + '/role/role.ac?method=add',// 请求的action路径
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

/*-----------------------编辑对话框----------------------------*/
//设置弹出框的属性
function setDialog_edit() {
	$('#edit').dialog({
		title : '系统角色编辑',
		modal : true, // 模式窗口：窗口背景不可操作
		collapsible : true, // 可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true, // 可拖动边框大小
		closed:true, // 初始关闭
		onClose : function() { // 继承自panel的关闭事件
			editReset();
		}
	});
}
// 打开对话框
function openDialog_edit() {
    editReset();
    
	var row = $('#listTable').datagrid('getSelected');
    if(row==undefined || row==null){
        alert("请选择要修改的角色!");
        return;
    }       
    var roleId = row.sysRoleId; 
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        dataType : "json",
        data : {
            "sysRoleId":roleId
        },
        url : root + '/role/role.ac?method=load',// 请求的action路径
        error : function() {// 请求失败处理函数
            alert('请求失败');
        },
        success : function(data) {
            if (data == null) {// 未返回任何消息表示添加成功
                alert('请求失败');
            }else{
			    $("#edit_roleName").val(data.roleName);
			    $("#edit_roleId").val(data.sysRoleId);
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
    $("#edit_roleName").val("");
    $("#edit_roleId").val("");
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
			"sysRoleId":$("#edit_roleId").val(),
			"roleName":$("#edit_roleName").val(),        
		},
		url : root + '/role/role.ac?method=edit',// 请求的action路径
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

$.extend($.fn.validatebox.defaults.rules, {
	roleName : {
		validator : function(value, param) {
			if (value.length > 16 || value.length < 4) {
				return false;
			}
			var result = $.ajax({
						url : param[0],
						data : {
                            "roleName":value
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
		message : '角色名已存在或过短!(5-16位)'
	}
});
