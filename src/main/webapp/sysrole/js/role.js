// 扩展校验规则
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
				}
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

$(function() {
	// 加载表格数据
	ajaxTable();
	
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
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				openDialog_add();
			}
		},"-",{
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				openDialog_edit();
			}
		}]
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
	return '<a href="#" onclick="ajaxUserTable(\''+val+'\',\'' + row.roleName + '\');">查看用户</a>';
}


/**
 * 初始化用户列表显示窗口
 */
function setWindow_view(){
	$('#window_view_user').window({
		width:800,
		height:500,
		closed: true
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
function ajaxUserTable(roleId, roleName) {
	$("#window_view_user").window('setTitle', '角色【'+roleName+'】用户列表');
	$("#window_view_user").window('open');
	$("#current_sysrole_id").val(roleId);
	// 加载表格
	$('#userListTable').datagrid({
		queryParams : {
			"sysRoleId":roleId
		}
	}).datagrid('acceptChanges');
	
}

/**
 * 用户列表刷新方法
 */
function reloadUserTable() {
	$('#userListTable').datagrid('reload');
}


/**
 * 设置添加窗口
 */
function setDialog_add() {
	$('#add').dialog({
		title : '系统角色添加',
		closed: true,
		onClose : function() {
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
	$('#table_add input').each(function() {
		if ($(this).attr('required') || $(this).attr('validType')) {
			if (!$(this).validatebox('isValid')) {
				validateResult = false;
				return;
			}
		}
	});
	if (validateResult == false) {
		return;
	}

	$.ajax({
		url : root + '/role/role.ac?method=add',
		data : {
			"roleName":$("#add_roleName").val()
		},
		success : function(data) {
			showMessage("添加成功");
			addReset();
			reloadTable();
		}
	});
}

//设置弹出框的属性
function setDialog_edit() {
	$('#edit').dialog({
		title : '系统角色编辑',
		closed: true,
		onClose : function() {
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
		url : root + '/role/role.ac?method=load',
		data : {
			"sysRoleId":roleId
		},
		success : function(data) {
			if (!data) {
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
	$('#table_edit input').each(function() {
		if ($(this).attr('required') || $(this).attr('validType')) {
			if (!$(this).validatebox('isValid')) {
				validateResult = false;
				return;
			}
		}
	});
	if (validateResult == false) {
		return;
	}

	$.ajax({
		url : root + '/role/role.ac?method=edit',
		data : {
			"sysRoleId":$("#edit_roleId").val(),
			"roleName":$("#edit_roleName").val(),
		},
		success : function(data) {
			showMessage("修改成功");
			reloadTable();
		}
	});
}
