$(function() {
	// 加载表格数据
	ajaxTable();
});

/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').datagrid({
	}).datagrid('acceptChanges');
}
// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}
// 打开添加页面
function openAddPage(){
	openTab('tabId_srclevelAdd', '添加数据源级别配置', root+'/domainsrclevel/srclevel.ac?method=toAdd');
}

// 打开编辑页面
function openEditPage(){
	var row = $('#listTable').datagrid('getSelected');
	if(row==undefined || row==null){
		alert("请选择要修改的数据");
		return;
	}
	var id = row.ID;
	openTab('tabId_srclevelEdit', '修改数据源级别配置', root+'/domainsrclevel/srclevel.ac?method=toAdd&ID='+id);
}

//删除
function removeData(){
	var row = $('#listTable').datagrid('getSelected');
	if(row==undefined || row==null){
		alert("请选择要删除的数据");
		return;
	}
	var id = row.ID;
	var field_name = row.FIELD_DESC;
	confirm("确认要删除【"+row.FIELD_DESC+"】?", function(){
		$.ajax({
			url : root + '/domainsrclevel/srclevel.ac?method=del',
			type : 'POST',
			data : {"ID":id},
			success : function(data) {
				reloadTable();
				showMessage("删除成功");
			}
		});
	})
}