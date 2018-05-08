$(function(){
	$('#form').form({
		url: root + '/mgr/fieldconfig/save',
		ajax: true,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success:function(data){
			back(function(){
				parent.tabCallPass('iframe_tabId_field', "reloadTable");
			});
		}
	});
	
	// 提交表单
	$("#btn-submit").click(function(){
		$('#form').submit();
	});
	// 返回
	$("#btn-back").click(function(){
		back();
	});
	
	// 返回上一页
	function back(cb){
		backTab('tabId_field', '主索引字段管理', root + '/cfg/page/field.jsp', cb);
		parent.$('#centerTab').tabs('close','主索引字段编辑');
	}
	
})
