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
		toolbar:[
		{
			text : '添加匹配规则',
			iconCls : 'icon-add',
			handler : function(){
				openAddPage();	
			}
		}]
	}).datagrid('acceptChanges');
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 状态 编码转换
function buildState(val,row){
	if(val == '1'){
		return '已生效';
	}else{
		return '未生效';
	}
}

// 创建操作连接
function buildOptLink(val,row){
	var link = '';
	link +='<a href="#" onclick="openViewPage(\''+row.configId+'\')">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	if(row.state != "1"){
		var name = row.configDesc;
		link +='<a href="#" onclick="activateCfg(\''+row.configId+'\',\''+name+'\',this)">使用</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		link += '<a href="#" onclick="del(\'' + row.configId + '\',\'' + name + '\',this)">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	}
	return link;
}

// 打开查看匹配配置页面
function openViewPage(cfgId){
	openTab('tabId_match_cfg_view_biz', '业务匹配配置详情', root+'/matchCfgbiz/view?cfgId='+cfgId);
}

// 查看当前配置
function openCurrentPage(){
	openTab('tabId_current_match_view_biz', '当前业务匹配配置详情', root+'/matchCfgbiz/current');
}

// 使配置生效
function activateCfg(cfgId,cfgName,btn){
	confirm("确认使用["+cfgName+"]匹配配置么?此配置生效后,现使用的配置将失效。", function(){
		$.ajax({
			url : root + '/matchCfgbiz/effect',
			type : 'POST',
			data : {
				cfgId:cfgId
			},
			success : function(data) {
				showMessage("操作成功");
				reloadTable();
			}
		});
	})
}

//删除
function del(cfgId, cfgName) {
	confirm("确定删除[" + cfgName + "]？删除之后不可撤销。", function() {
		$.ajax({
			url : root + '/matchCfgbiz/del',
			type : 'POST',
			data : { id : cfgId },
			success : function(data) {
				reloadTable();
				showMessage("操作成功");
			}
		});
	})
}

// 打开添加页面
function openAddPage(){
	openTab('tabId_matchCfgAdd', '添加业务匹配配置', root+'/matchCfgbiz/toAdd');
}
