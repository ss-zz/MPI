var INUSED_FIELDS={};

$(function() {	
	// 初始化字段下拉
	createSelect("add_fieldSelect",SELECT_JSON.pList,"codeName","codeId",INUSED_FIELDS);
	if (typeof (JSON) == 'undefined') {
		$.getScript(root+'/js/easyui/json2.js');
	}
});

//锁定按钮
function lockBtn(btn){
	$(btn).unbind('click').removeAttr('onclick');
	$(btn).attr("disabled",true);  
}

// 解锁按钮
function unlockBtn(btn,handler){
	$(btn).bind("click",handler);
	$(btn).attr("disabled",false);	  
}

/**
 * @param id select ID
 * @param data 数据
 * @param textStr 文本标志
 * @param valStr 值标志
 * @param inused 使用中的数据
 */
function createSelect(id,data,textStr,valStr,inused){
	var sel = $("#"+id);
	sel.empty();
	sel.prepend('<option value="">请选择</option>');
	for(var i = 0 ; i < data.length ; i++){
		var obj = data[i];
		var text = obj[textStr];
		var val = obj[valStr];
		if(inused!=undefined && inused!=null && inused[val]!=null)
			continue;		
		sel.append('<option value="'+val+'">'+text+'</option>');
	}
}

/**
 * 添加字段配置
 */
function addFieldCfg(){
	var filedId = $("#add_fieldSelect").val();
	if(filedId==undefined || filedId==null || filedId==""){
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
	
	if(data==undefined || data==null || data==""){
		alert("无效字段,请重新选择");
		return;
	}
	// 定义要添加的html代码	
	var htm = '<fieldset id="'+filedId+'_add_fieldset"><legend><span style="color:#78a9cb;font-weight:bold;">'+data.codeName+'</span>-匹配设定</legend>'+
		'<input type="hidden" id="'+filedId+'_add_fieldName" value="xxx">'+
		'<input type="hidden" id="'+filedId+'_add_desc" value="'+data.codeName+'">'+			
		'<label for="'+filedId+'_add_agreeThreshold">完全匹配值:</label><input type="text" class="easyui-validatebox" id="'+filedId+'_add_agreeThreshold" maxlength="10" required="true" validType="decimalValid"/>&nbsp;&nbsp;'+
		'<label for="'+filedId+'_add_disagreeThreshold">不匹配值:</label><input type="text" class="easyui-validatebox" id="'+filedId+'_add_disagreeThreshold" maxlength="10" required="true" validType="decimalValid"/>&nbsp;&nbsp;'+
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
	// 消除验证框
	/*
	$('#table_add input').each(function() {
		if($(this).attr('id')=="add_agreeThreshold"||$(this).attr('id')=="add_matchThreshold"){
			return;
		}
		if ($(this).attr('required') || $(this).attr('validType')) {
			$(this).validatebox('destroy');
		}
	});
	*/
	delete INUSED_FIELDS[fieldId];
	$("#"+fieldId+"_add_fieldset").remove();
	// 重建 字段下拉
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
	// 将按钮置为无效
	var btn = $("#saveBtn");
	lockBtn(btn);
	var params = {};
	
	params.configDesc=$("#add_configDesc").val();
	params.agreeThreshold=$("#add_agreeThreshold").val();
	params.matchThreshold=$("#add_matchThreshold").val();
	params.matchFieldCfgs = [];
	var i = 0;
	for(var fieldId in INUSED_FIELDS){
		params.matchFieldCfgs[i]={};
		params.matchFieldCfgs[i].propertyName=fieldId;
		params.matchFieldCfgs[i].agreeProb=$("#"+fieldId+"_add_agreeThreshold").val();
		params.matchFieldCfgs[i].disAgree=$("#"+fieldId+"_add_disagreeThreshold").val();
		params.matchFieldCfgs[i].matchThreshold=$("#"+fieldId+"_add_matchThreshold").val();
		params.matchFieldCfgs[i].matchFunction=$("#"+fieldId+"_add_matchFunction").val();
		params.matchFieldCfgs[i].weight=$("#"+fieldId+"_add_weight").val();
		params.matchFieldCfgs[i].cfgDesc=$("#"+fieldId+"_add_desc").val();
		i++;
	}
	
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "text",
		contentType: "application/json",
		data : JSON.stringify(params),
		url : root + '/cfg/match.ac?method=add',// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
			// 失败回复 btn
			unlockBtn(btn,saveMatchCfg);
		},
		success : function(msg) {
			var messgage = "添加成功!";
			if (msg == null||msg=="") {// 未返回任何消息表示添加成功
				alert(messgage);
				goBackClose();
			} else {// 返回异常信息
				// 失败回复 btn
				unlockBtn(btn,saveMatchCfg);
				messgage = msg;
				alert(messgage);
			}
			
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
	//取得值
	var agree = parseFloat($("#"+fieldId+"_add_agreeThreshold").val());
	var disagree = parseFloat($("#"+fieldId+"_add_disagreeThreshold").val());
	var match = parseFloat($("#"+fieldId+"_add_matchThreshold").val());
	
	var name = $("#"+fieldId+"_add_desc").val();

	if(match>=agree||disagree>=match){
		alert("["+name+"]字段中的匹配数据不符合:\n\r 完全匹配值 > 匹配阀值 > 不匹配值");
		return false;
	}
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
	var tabId = 'tabId_mc';
	var title = '匹配规则管理';
	var url = root+'/cfg/page/match.jsp';
	var name = 'iframe_'+tabId;
	//如果当前id的tab不存在则创建一个tab
	if(parent.$("#"+tabId).html()==null){		
		parent.$('#centerTab').tabs('add',{
			title: title,		 
			closable:true,
			cache : false,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'" id="'+tabId+'" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}else{
		parent.tabCallPass(name,"reloadTable");	
	}
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
