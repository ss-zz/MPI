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
		toolbar:[ {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				removeData();
			}
		}],
		queryParams : {
			domainId: uniqueSign
		}
	}).datagrid('acceptChanges');
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

//删除
function removeData(){
	var row = $('#listTable').datagrid('getSelected');
	if(row==undefined || row==null){
		alert("请选择要删除的行!");
		return;
	}	 
	var id = row.id;
	var field_name = row.fieldDesc;
	confirm("确认删除:"+row.fieldDesc+"？删除之后不可撤销。", function(){
		$.ajax({
			url : root + '/domainsrclevel/srclevel.ac?method=del',// 请求的action路径
			type : 'POST',
			data : { id: id },
			success : function(data) {
				// 刷新列表
				reloadTable();
				showMessage("删除成功");
			}
		});
	})
}