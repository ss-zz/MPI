var GROUP_FIELDS = {};
$(function() {
	if (typeof (JSON) == 'undefined') {
		$.getScript(root+'/js/easyui/json2.js');
	}
});

/**
 * 添加字段配置
 */
function addFieldCfg(groupId){	
	var fieldName = $("#add_fieldSelect_"+groupId).val();
	if(fieldName==null || fieldName==undefined || fieldName==""){
		alert("请选择字段");
		return ;
	}
	// 清空没有字段的提示
	$('#add_fieldTip_'+groupId).text("");
	var rowId = uniqid(groupId+"_");
	
	var fieldCnName = $("#add_fieldSelect_"+groupId).find("option:selected").text();
	var funName = $("#add_funName_"+groupId).val();
	
	// 定义要添加的html代码	
	var htm = '<tr id="row_'+rowId+'"><td>属性描述:&nbsp;'+fieldCnName+'</td>'+
		'<td>转换函数:&nbsp;'+(funName==""?"无":funName)+'</td>'+
		'<td><div class="my-btn my-btn-danger my-btn-small" onclick="removeFieldCfg(\''+groupId+'\',\''+rowId+'\')">移除字段</div></td></tr>';
	$("#group_field_view_"+groupId).prepend(htm);
	GROUP_FIELDS[groupId][rowId]={"propertyName":fieldName,"funName":funName};
}

/**
 * 移除字段匹配属性
 */
function removeFieldCfg(groupId,rowId){
	$("#row_"+rowId).remove();
	//清楚字段配置缓存
	delete GROUP_FIELDS[groupId][rowId];	
}

/**
 * 添加分组配置
 */
function addGroup(){
	// 清空 没有分组的提示
	$('#add_configTip').text("");
	var group_id = uniqid();
	// 添加分组字段
	GROUP_FIELDS[group_id]={};
	// 定义要添加的html代码	
	var htm = '<fieldset id="group_fieldset_'+group_id+'">'+
		'<legend>分组配置</legend>'+
		'<label for="add_fieldSelect_'+group_id+'">选择字段:</label><select id="add_fieldSelect_'+group_id+'"></select>&nbsp;&nbsp;'+
		'<label for="add_funName_'+group_id+'">转换函数:</label><select id="add_funName_'+group_id+'"></select>&nbsp;&nbsp;'+
		'<div class="my-btn" onclick="addFieldCfg(\''+group_id+'\')">添加字段</div>&nbsp;&nbsp;'+
		'<div class="my-btn my-btn-danger" onclick="removeGroup(\''+group_id+'\')">移除分组</div><br/><br/>'+
		'<table id="group_field_view_'+group_id+'" class="myTable"></table><div id="add_fieldTip_'+group_id+'" style="color:#ff0000;font-weight:bold;"></div></fieldset>';
	$("#field_cfg_div").prepend(htm);	
	//建立 下拉
	createSelect("add_fieldSelect_"+group_id,SELECT_JSON.pList,"codeName","codeId");
	createSelect("add_funName_"+group_id,SELECT_FUNS,"desc","clazz");
}

/**
 * 添加分组配置
 */
function removeGroup(groupId){
	//清楚字段配置缓存
	delete GROUP_FIELDS[groupId];
	$("#group_fieldset_"+groupId).remove();

}


/**
 * 保存匹配设置
 */
function saveMatchCfg(){
	// 校验数据
	var validateResult = true;
	// easyui 表单验证
	$('#table_add input').each(function() {
		if ($(this).attr('required') || $(this).attr('validType')) {
			if (!$(this).validatebox('isValid')) {
				// 如果验证不通过，则返回false
				validateResult = false;
			}
		}
	});

	var params = {};
	params["groupCount"] = getPropertyCount(GROUP_FIELDS);
	params["blockDesc"] = $("#add_configDesc").val();
	params["groups"]={};
	if(params["groupCount"] == 0){
		$('#add_configTip').text("还没有添加分组,请先添加分组!");
		validateResult = false;
	}
	var i = 0 ; 
	for(var fieldGroup in GROUP_FIELDS){
		if(getPropertyCount(GROUP_FIELDS[fieldGroup])==0){
			$('#add_fieldTip_'+fieldGroup).text("分组中没有添加字段信息,请添加字段配置或移除分组!");
			validateResult = false;
		}
		params["groups"][i+""]=[];
		var j = 0;
		for(var field in GROUP_FIELDS[fieldGroup]){
			params["groups"][i+""][j]=GROUP_FIELDS[fieldGroup][field];
			j++;
		}
		i++;
	}
	if(!validateResult){
		return;
	}
	$.ajax({
		url : root + '/cfg/block.ac?method=add',
		type : 'POST',
		dataType : "text",
		contentType: "application/json",
		data : JSON.stringify(params),
		success : function(msg) {
			showMessage("添加成功");
			goBackClose();
		}
	});
}

/**
 * 关闭页面并返回
 */
function goBackClose(){
	backTab('tabId_bc', '主索引初筛规则管理', root+'/cfg/page/block.jsp', function(){
		parent.tabCallPass("iframe_tabId_bc", "reloadTable");
	});
	parent.$('#centerTab').tabs('close','添加初筛配置');
}

/**
 * 取得Object对象长度
 */
function getPropertyCount(o) {
	var count = 0;
	for (var n in o) {
		if (o.hasOwnProperty(n)) {
			count++;
		}
	}
	return count;
} 

/**
 * 生成唯一id
 */
function uniqid(prefix) {
	var uid = new Date().getTime().toString(16);
	uid += Math.floor((1 + Math.random()) * Math.pow(16, (16 - uid.length)))
		.toString(16).substr(1);	
	return (prefix || '') + uid;
}
