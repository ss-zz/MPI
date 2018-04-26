$(function(){
	$('#form').form({
		url: root + '/mgr/bizconfig/save',
		onSubmit: function(){
			return $(this).form('validate');
		},
		success:function(data){
			back(function(){
				parent.tabCallPass('iframe_tabId_biz_config', "reloadTable");
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
		backTab('tabId_biz_config', '业务配置', root + '/config/page/biz_config.jsp', cb);
		parent.$('#centerTab').tabs('close','业务编辑');
	}
	
})
