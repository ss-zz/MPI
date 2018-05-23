$(function() {
	// 加载表格数据
	ajaxTable();
	// 初始化弹出层
	setDialog_add();
	closeDialog_add();
	setDialog_edit();
	closeDialog_edit();
});

/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').datagrid({
		toolbar : [{// 正上方工具栏
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				openDialog_add();
			}
		}, '-', {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				openDialog_edit();
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				removeData();
			}
		}],
	}).datagrid('acceptChanges');
}
// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 设置弹出框的属性
function setDialog_add() {
	$('#add').dialog({
		title : '系统用户添加',
		onClose : function() {
			addReset();
		}
	});
}
function addReset() {
	$("#add_name").val("");
	$("#add_userName").val("");
	$("#add_password").val("");
	$("#readd_password").val("");
	$("#add_email").val("");
	$("#add_role").val("");
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
				validateResult = false;
				return;
			}
		}
	});
	if (validateResult == false) {
		return;
	}

	$.ajax({
		url : root + '/sysuser/su.ac?method=add',
		type : 'POST',
		data : {
			"name":$("#add_name").val(),
			"userName":$("#add_userName").val(),
			"password":$("#add_password").val(),
			"email":$("#add_email").val(),
			"sysRoleId":$("#add_role").val()
		},
		success : function(data) {
			showMessage("添加成功");
			addReset();
			reloadTable();
		}
	});
}

// 设置弹出框的属性
function setDialog_edit() {
	$('#edit').dialog({
		title : '系统用户编辑'
	});
}
// 打开对话框
function openDialog_edit() {
	editReset();
	
	var row = $('#listTable').datagrid('getSelected');
	if(row==undefined || row==null){
		alert("请选择要修改的用户!");
		return;
	}
	var userId = row.USER_ID; 
	$.ajax({
		url : root + '/sysuser/su.ac?method=load',
		type : 'POST',
		data : {
			"userId":userId
		},
		success : function(data) {
			if (!data) {
				alert('请求失败');
			}else{
				$("#edit_name").val(data.name);
				$("#edit_userName").val(data.userName);
				$("#edit_email").val(data.email);
				$("#edit_role").val(data.sysRoleId);
				$("#edit_userName_show").html(data.userName);
				$("#edit_userId").val(data.userId);
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
	$("#edit_name").val("");
	$("#edit_userName").val("");
	$("#edit_userName_show").html("");
	$("#edit_email").val("");
	$("#edit_role").val("");
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
		url : root + '/sysuser/su.ac?method=edit',
		type : 'POST',
		data : {
			"userId":$("#edit_userId").val(),
			"name":$("#edit_name").val(),
			"email":$("#edit_email").val(),
			"sysRoleId":$("#edit_role").val()
		},
		success : function(data) {
			showMessage("修改成功");
			reloadTable();
		}
	});
}


//删除用户方法
function removeData(){
	var row = $('#listTable').datagrid('getSelected');
	if(row==undefined || row==null){
		alert("请选择要删除的用户!");
		return;
	}	 
	var userId = row.USER_ID;
	confirm("确认删除用户【"+row.USER_NAME+"】?", function(){
		$.ajax({
			url : root + '/sysuser/su.ac?method=del',
			type : 'POST',
			data : {
				"userId":userId
			},
			success : function(data) {
				showMessage("删除成功");
				reloadTable();
			}
		});
	})
}

$.extend($.fn.validatebox.defaults.rules, {
	reapet : {
		validator : function(value, param) {
			var pwd = $(param[0]).attr('value');
			if (pwd != value) {
				return false;
			}
			return true;
		},
		message : '{1}'
	},
	loginName : {
		validator : function(value, param) {
			if (value.length > 16 || value.length < 5) {
				return false;
			}
			var result = $.ajax({
				url : param[0],
				data : {
					"userName":value
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
		message : '用户名已存在或过短!(5-16位)'
	}
});
