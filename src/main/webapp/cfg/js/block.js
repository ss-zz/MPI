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
		toolbar : [ {
			text : '添加初筛配置',
			iconCls : 'icon-add',
			handler : function() {
				openAddPage();
			}
		} ],
	}).datagrid('acceptChanges');
}
// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 状态 编码转换
function buildState(val, row) {
	if (val == '1') {
		return '已生效';
	} else {
		return '未生效';
	}
}

// 创建操作连接
function buildOptLink(val, row) {
	var link = '';
	link += '<a href="#" onclick="openViewPage(\'' + val + '\')">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	if (row.state != "1") {
		var name = row.blockDesc;
		link += '<a href="#" onclick="activateCfg(\'' + val + '\',\'' + name + '\',this)">使用</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		link += '<a href="#" onclick="del(\'' + val + '\',\'' + name + '\',this)">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	}
	return link;
}

// 打开查看初筛配置页面
function openViewPage(cfgId) {
	openTab('tabId_block_cfg_view', '初筛配置详情', root + '/cfg/block.ac?method=view&cfgId=' + cfgId);
}

// 查看当前配置
function openCurrentPage() {
	openTab('tabId_current_block_view', '当前初筛配置详情', root + '/cfg/block.ac?method=current');
}

// 使配置生效
function activateCfg(cfgId, cfgName) {
	confirm("确定使用[" + cfgName + "]？配置生效后，现使用的配置将失效", function() {
		$.ajax({
			url : root + '/cfg/block.ac?method=effect',
			type : 'POST',
			data : { cfgId : cfgId },
			success : function(data) {
				reloadTable();
				showMessage("操作成功");
			}
		});
	})
}

// 删除
function del(cfgId, cfgName) {
	confirm("确定删除[" + cfgName + "]？删除之后不可撤销。", function() {
		$.ajax({
			url : root + '/cfg/block.ac?method=del',
			type : 'POST',
			data : { id : cfgId },
			success : function(data) {
				reloadTable();
				showMessage("操作成功");
			}
		});
	})
}

// 打开新增配置页面
function openAddPage() {
	openTab('tabId_blockCfgAdd', '添加初筛配置', root + '/cfg/block.ac?method=toAdd');
}
