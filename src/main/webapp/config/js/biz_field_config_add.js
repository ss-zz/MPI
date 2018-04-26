$(function(){
	$('#form').form({
		url: root + '/mgr/bizfieldconfig/save',
		onSubmit: function(){
			return $(this).form('validate');
		},
		success:function(data){
			back(function(){
				parent.tabCallPass('iframe_tabId_biz_field_config', "reloadTable");
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
		backTab('tabId_biz_field_config', '业务字段配置', root + '/mgr/bizconfig/toBizFieldConfigPage/' + $("#inputBizConfigId").val(), cb);
		parent.$('#centerTab').tabs('close','业务字段编辑');
	}
	
})
