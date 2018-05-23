/**
 * 拆分用户
 */
function splitToIndex(){
	if(!confirm("确认拆分么?"))
		return;
	$.ajax({
		url : root + '/manual/manual.ac?method=splitToIndex',
		type : 'POST',
		data : {
			"indexId":$("#selectIndexId").val(),
			"personId":$("#selectPersonId").val(),
			"fromIndexId":$("#fromIndexId").val()
		},
		success : function(data) {
			showMessage("操作成功");
			closeOneTwo();
		}
	});	
}

/**
 * 关闭并返回到第一步页面
 */
function closeOneTwo(){
	backTab('tabId_qi', '主索引记录查询',root+'/query/page/query.jsp', function(){
		parent.tabCallPass("iframe_tabId_qi", "reloadTable");
	});
	parent.$('#centerTab').tabs('close','选择目标索引');
	parent.$('#centerTab').tabs('close','拆分至索引明细对比');
}

/**
 * 关闭并返回
 */
function goBackClose(){
	var personId = $("#selectPersonId").val();
	var indexId = $("#fromIndexId").val();
	openTab('tabId_splitToIndex', '选择目标索引', root+'/query/query.ac?method=toQueryIdx&personId='+personId+'&indexId='+indexId);
	parent.$('#centerTab').tabs('close','拆分至索引明细对比');
}
