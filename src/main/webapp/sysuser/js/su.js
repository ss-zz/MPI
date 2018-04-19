$(function() {
	// 加载表格数据
	ajaxTable();
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
		toolbar : [{// 正上方工具栏
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				// 点击工具栏运行的js方法
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
		singleSelect:true,//单选
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

/** --------------添加操作弹出框------------------* */
// 设置弹出框的属性
function setDialog_add() {
	$('#add').dialog({
		title : '系统用户添加',
		modal : true, // 模式窗口：窗口背景不可操作
		collapsible : true, // 可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true, // 可拖动边框大小
		onClose : function() { // 继承自panel的关闭事件
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
		   "name":$("#add_name").val(),
		   "userName":$("#add_userName").val(),
		   "password":$("#add_password").val(),
		   "email":$("#add_email").val(),
		   "sysRoleId":$("#add_role").val()
		},
		url : root + '/sysuser/su.ac?method=add',// 请求的action路径
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
		title : '系统用户编辑',
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
		alert("请选择要修改的用户!");
		return;
	}	   
	var userId = row.USER_ID; 
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		data : {
			"userId":userId
		},
		url : root + '/sysuser/su.ac?method=load',// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) {
			if (data == null) {// 未返回任何消息表示添加成功
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
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		data : {
			"userId":$("#edit_userId").val(),
			"name":$("#edit_name").val(),
			"email":$("#edit_email").val(),
			"sysRoleId":$("#edit_role").val()			
		},
		url : root + '/sysuser/su.ac?method=edit',// 请求的action路径
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
		alert("请选择要删除的用户!");
		return;
	}	 
	var userId = row.USER_ID;
	if(confirm("确认要删除用户:"+row.USER_NAME+"么?")){
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			data : {
				"userId":userId			
			},
			url : root + '/sysuser/su.ac?method=del',// 请求的action路径
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
