$(function(){
	$('#form').form({
		url: root + '/mgr/bizcommonfieldconfig/save',
		onSubmit: function(){
			return $(this).form('validate');
		},
		success:function(data){
			back(function(){
				parent.tabCallPass('iframe_tabId_tyzdgl', "reloadTable");
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
		backTab('tabId_tyzdgl', '通用字段管理', root + '/config/page/common_field_config.jsp', cb);
		parent.$('#centerTab').tabs('close','通用字段编辑');
	}
	
})
