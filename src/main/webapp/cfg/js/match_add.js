var INUSED_FIELDS={};

$(function() {
	// 初始化字段下拉
	createSelect("add_fieldSelect",SELECT_JSON.pList,"codeName","codeId",INUSED_FIELDS);
});

/**
 * 添加字段配置
 */
function addFieldCfg(){
	var filedId = $("#add_fieldSelect").val();
	if(!filedId){
		alert("请选择字段");
		return ;
	}
	
	var data = "";
	for(var i = 0 ; i<SELECT_JSON.pList.length ;i++){
		var obj = SELECT_JSON.pList[i];
		if(obj.codeId == filedId){
			data = obj;
			INUSED_FIELDS[filedId]=filedId;
			break;
		}
	}
	
	if(!data){
		alert("无效字段,请重新选择");
		return;
	}
	// 定义要添加的html代码	
	var htm = '<fieldset id="'+filedId+'_add_fieldset"><legend><span style="color:#78a9cb;font-weight:bold;">'+data.codeName+'</span>-匹配设定</legend>'+
		'<input type="hidden" id="'+filedId+'_add_fieldName" value="xxx">'+
		'<input type="hidden" id="'+filedId+'_add_desc" value="'+data.codeName+'">'+
		'<label for="'+filedId+'_add_matchThreshold">匹配阀值:</label><input type="text" class="easyui-validatebox" id="'+filedId+'_add_matchThreshold" maxlength="10" required="true" validType="decimalValid"/>'+
		'<label for="'+filedId+'_add_weight">权重:</label><input type="text" class="easyui-validatebox" id="'+filedId+'_add_weight" maxlength="10" required="true" validType="decimalValid"/>&nbsp;&nbsp;'+
		'<label for="'+filedId+'_add_matchFunction">匹配算法:</label><select id="'+filedId+'_add_matchFunction" required="true"></select>&nbsp;&nbsp;'+
		'<div class="my-btn my-btn-danger" onclick="removeFieldCfg(\''+filedId+'\')">移除字段</div></fieldset>';
	$("#field_cfg_div").prepend(htm);
	// 重建 字段下拉
	createSelect("add_fieldSelect",SELECT_JSON.pList,"codeName","codeId",INUSED_FIELDS);
	
	//建立 函数下拉
	createSelect(filedId+"_add_matchFunction",SELECT_JSON.mList,"nameCn","name");
	
	// 添加校验
	$.parser.parse();
}

/**
 * 移除字段匹配属性
 */
function removeFieldCfg(fieldId){
	delete INUSED_FIELDS[fieldId];
	$("#"+fieldId+"_add_fieldset").remove();
	createSelect("add_fieldSelect",SELECT_JSON.pList,"codeName","codeId",INUSED_FIELDS);
}

/**
 * 保存匹配设置
 */
function saveMatchCfg(){
	// 校验数据
	if(!validMatchCfgData()){
		return ;
	}
	var params = {};
	params.configDesc=$("#add_configDesc").val();
	params.agreeThreshold=$("#add_agreeThreshold").val();
	params.matchThreshold=$("#add_matchThreshold").val();
	params.matchFieldCfgs = [];
	var i = 0;
	for(var fieldId in INUSED_FIELDS){
		params.matchFieldCfgs[i]={};
		params.matchFieldCfgs[i].propertyName=fieldId;
		params.matchFieldCfgs[i].matchThreshold=$("#"+fieldId+"_add_matchThreshold").val();
		params.matchFieldCfgs[i].matchFunction=$("#"+fieldId+"_add_matchFunction").val();
		params.matchFieldCfgs[i].weight=$("#"+fieldId+"_add_weight").val();
		params.matchFieldCfgs[i].cfgDesc=$("#"+fieldId+"_add_desc").val();
		i++;
	}
	
	$.ajax({
		url : root + '/cfg/match.ac?method=add',
		type : 'POST',
		dataType : "text",
		contentType: "application/json",
		data : JSON.stringify(params),
		success : function(msg) {
			goBackClose();
			showMessage("添加成功");
		}
	});
}

/**
 * 校验数据
 */
function validMatchCfgData(){
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
	// 判断选用字段
	if(getPropertyCount(INUSED_FIELDS)==0){
		alert("请添加匹配字段");
		validateResult = validateResult&&false;
	}
	// 接着规则校验
	var mainAT = parseFloat($("#add_agreeThreshold").val());
	var mainMT = parseFloat($("#add_matchThreshold").val());
	if(mainMT>=mainAT){
		alert("完全匹配值 必须大于 可能匹配值");
		validateResult = validateResult&&false;
	}
	// 字段属性校验
	for(var fieldId in INUSED_FIELDS){
		if(!validFieldData(fieldId)){
			validateResult = validateResult&&false;
			break;
		}
	}
	return validateResult;
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
 * 验证字段数据
 */
function validFieldData(fieldId){
	return true;
}

/**
 * 页面重置
 */
function resetAllData(){
	$("#add_agreeThreshold").val("");
	$("#add_matchThreshold").val("");
	for(var key in INUSED_FIELDS){
		removeFieldCfg(key);
	}
	createSelect("add_fieldSelect",SELECT_JSON.pList,"codeName","codeId",INUSED_FIELDS);
}

/**
 * 关闭页面并返回
 */
function goBackClose(){
	backTab('tabId_mc', '主索引匹配规则管理', root+'/cfg/page/match.jsp', function(){
		parent.tabCallPass("iframe_tabId_mc", "reloadTable");
	});
	parent.$('#centerTab').tabs('close','添加匹配配置');
}

$.extend($.fn.validatebox.defaults.rules, {
	decimalValid : {
		validator : function(value, param) {
			if(/^(0(.\d+)?|1(\.0+)?)$/.test(value)){ 
				var num = parseFloat(value);
				if(num>0&&num<=1){
					return true;
				}else{
					return false;
				}				
			}else{ 
				return false;
			} 
		},
		message : '请输入0-1之间的数字(不包含0)'
	}
});
