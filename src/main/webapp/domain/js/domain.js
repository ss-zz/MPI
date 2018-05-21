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
		toolbar : [{
			text : '添加业务系统',
			iconCls : 'icon-add',
			handler : function() {
				openDialog_add();
			}
		}]
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
		title : '业务系统添加',
		onClose : function() { // 继承自panel的关闭事件
			addReset();
		}
	});
}
// 重置表单
function addReset() {
	$("#add_domainType").val("");
	$("#add_domainDesc").val("");
	$("#add_uniqueSign").val("");
	$("#add_pushAddr").val("");
	$("#add_bookType").val("0");
	$("#add_domainLevel").val("");
	$("#add_message").html("");
}

// 打开对话框
function openDialog_add() {
	$('#add').dialog('open');
}

// 关闭对话框
function closeDialog_add() {
	$('#add').dialog('close');
}

// 执行添加操作
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
		type : 'POST',
		data : {
			domainType:$("#add_domainType").val(),
			domainDesc:$("#add_domainDesc").val(),
			uniqueSign:$("#add_uniqueSign").val(),
			pushAddr:$("#add_pushAddr").val(),
			bookType:$("#add_bookType").val(),
			domainLevel:$("#add_domainLevel").val()
		},
		url : root + '/domain/domain.ac?method=add',// 请求的action路径
		success : function(data) {
			var messgage = "添加成功!";
			if (!data) {// 未返回任何消息表示添加成功
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
		title : '业务系统编辑'
	});
}
// 打开对话框
function openDialog_edit(idx) {
	editReset();
	var row = getRowByIdx(idx);
	if(row==undefined || row==null){
		alert("请选择要修改的业务系统!");
		return;
	}	
	$.ajax({
		type : 'POST',
		data : {domainId: row.domainId},
		url : root + '/domain/domain.ac?method=load',// 请求的action路径
		success : function(data) {
			if (data) {
				$("#edit_domainId").val(data.domainId);
				$("#edit_domainType").val(data.domainType);
				$("#edit_domainDesc").val(data.domainDesc);
				$("#edit_uniqueSign").val(data.uniqueSign);
				$("#edit_pushAddr").val(data.pushAddr);
				$("#edit_bookType").val(data.bookType);
				$("#edit_domainLevel").val(data.domainLevel);
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
		type : 'POST',
		data : {
			"domainId":$("#edit_domainId").val(),
			"domainType":$("#edit_domainType").val(),
			"domainDesc":$("#edit_domainDesc").val(),
			"uniqueSign":$("#edit_uniqueSign").val(),
			"pushAddr":$("#edit_pushAddr").val(),
			"bookType":$("#edit_bookType").val(),
			"domainLevel":$("#edit_domainLevel").val()
		},
		url : root + '/domain/domain.ac?method=edit',// 请求的action路径
		success : function(data) {
			var messgage = "修改成功!";
			if (!data) {// 未返回任何消息表示添加成功
				// 刷新列表
				reloadTable();
			} else if (data.errorMsg != null) {// 返回异常信息
				messgage = data.errorMsg;
			}
			$("#edit_message").html(messgage);
		}
	});
}

//删除方法
function removeData(idx){
	var row = getRowByIdx(idx);
	if(row==undefined || row==null){
		alert("请选择要删除的业务系统");
		return;
	}	 
	var domainId = row.domainId;
	if(confirm("确认要删除业务系统【"+row.domainDesc+"】?")){
		$.ajax({
			type : 'POST',
			data : {
				domainId: domainId
			},
			url : root + '/domain/domain.ac?method=del',// 请求的action路径
			success : function(data) {
				var messgage = "删除成功!";
				if (!data) {// 未返回任何消息表示添加成功
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

// 扩展校验
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
					domainId: domainId,
					uniqueSign: value
				},
				type : 'post',
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
		message : '必须为自然数'
	}
});

// 根据idx获取row
function getRowByIdx(idx){
	return $('#listTable').datagrid("getData").rows[idx];
}

//创建操作连接
function buildOptLink(val,row, idx){
	var link = '';
	link +='<a href="#" onclick="openDialog_edit(\''+idx+'\')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	link +='<a href="#" onclick="removeData(\''+idx+'\')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
	link +='<a href="#" onclick="openAddPage(\''+idx+'\')">管理字段数据源级别</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return link;
}
function openAddPage(idx){
	var domainId = getRowByIdx(idx).domainId;
	openTab('tabId_srclevelAdd', '管理字段数据源级别', root+'/domainsrclevel/srclevel.ac?method=toAdd&domainId='+domainId);
}