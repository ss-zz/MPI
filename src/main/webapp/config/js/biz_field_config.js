
$(function() {
	// 加载表格数据
	loadTable();
});

// 加载表格数据
function loadTable() {
	// 加载表格
	$('#listTable').datagrid({
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				openSavePage();
			}
		} ]
	}).datagrid('acceptChanges');
}

// 操作列
function buildOptLink(val, row){
	var link = '';
	link +='<a href="#" onclick="openSavePage(\''+row.id+'\')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	link +='<a href="#" onclick="del(\''+row.id+'\')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return link;
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 打开添加、编辑页面
function openSavePage(id) {
	// 业务配置id
	var bizConfigId = $("#inputBizConfigId").val();
	openTab('tabId_biz_field_config_add', '业务字段编辑', root + '/mgr/bizfieldconfig/toEditPage?id=' + id + '&bizConfigId=' + bizConfigId);
}

// 删除
function del(id){
	$.messager.confirm('确认', '确认删除？删除之后不可撤销。', function(r){
		if (r){
			$.ajax({
				url: root + '/mgr/bizfieldconfig/del/' + id,
				type: 'post',
				success: function(){
					$.messager.show({title:'消息', msg:'删除成功'});
					reloadTable();
				}
			})
		}
	});
}