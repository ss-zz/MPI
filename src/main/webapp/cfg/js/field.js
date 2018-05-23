$(function() {
	// 加载表格数据
	loadTable();
});

// 加载表格数据
function loadTable() {
	// 加载表格
	$('#listTable').datagrid({
		toolbar:[
		{
			text : '添加字段',
			iconCls : 'icon-add',
			handler : function(){
				openEditPage();
			}
		}],
		singleSelect:true,//单选
		pagination:false//分页
	}).datagrid('acceptChanges');
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

//操作列
function buildOptLink(val, row){
	var link = '';
	link +='<a href="#" onclick="openEditPage(\''+row.id+'\')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	link +='<a href="#" onclick="del(\''+row.id+'\')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return link;
}

// 打开编辑
function openEditPage(id){
	openTab('tabId_field_cfg_edit', '主索引字段编辑', root + '/mgr/fieldconfig/toEditPage?id=' + id);
}

//删除
function del(id){
	confirm('确认删除？删除之后不可撤销。', function(){
		$.ajax({
			url: root + '/mgr/fieldconfig/del/' + id,
			type: 'post',
			success: function(){
				$.messager.show({title:'消息', msg:'删除成功'});
				reloadTable();
			}
		})
	});
}
