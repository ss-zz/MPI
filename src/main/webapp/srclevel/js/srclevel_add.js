var INUSED_FIELDS={};
var DOMAINS={};
$(function() {
	// 初始化字段下拉
	createSelect("add_fieldSelect",SELECT_JSON.pList,"codeName","codeId",INUSED_FIELDS);
	// 初始化字段下拉
	createSelect("add_domainSelect",SELECT_JSON.domainList,"domainDesc","uniqueSign",DOMAINS);
	if (typeof (JSON) == 'undefined') {
		$.getScript(root+'/js/easyui/json2.js');
	}
});

/**
 * 保存匹配设置
 */
function saveMatchCfg(){
	// 校验数据
	if(!validMatchCfgData()){
		return ;
	}
	$.ajax({
		url : root + '/domainsrclevel/srclevel.ac?method=add',// 请求的action路径
		type : 'POST',
		data : {
			domainId:$("#add_domainSelect").val(),
			domainDesc:$("#add_domainSelect").find("option:selected").text(),
			fieldName:$("#add_fieldSelect").val(),
			fieldDesc:$("#add_fieldSelect").find("option:selected").text(),
			fieldLevel:$("#add_fieldlevel").val()
		},
		success : function(msg) {
			showMessage("添加成功");
			$('#listTable').datagrid('reload');
		}
	});
}

/**
 * 校验数据
 */
function validMatchCfgData(){
	// 校验数据
	var validateResult = true;
	DOMAIN_ID=$("#add_domainSelect").val();
	FIELD_NAME=$("#add_fieldSelect").val();
	FIELD_LEVEL=$("#add_fieldlevel").val();
	
	// 判断选用字段
	if(""==DOMAIN_ID){
		alert("请选择域标识");
		validateResult = validateResult&&false;
		return validateResult;
	}else if(""==FIELD_NAME){
		alert("请选择字段");
		validateResult = validateResult&&false;
		return validateResult;
	} else if(""==FIELD_LEVEL){
		alert("请选择字段数据源级别");
		validateResult = validateResult&&false;
		return validateResult;
	}
	return validateResult;
}


/**
 * 页面重置
 */
function resetAllData(){
	$("#add_fieldSelect").val("");
	$("#add_fieldlevel").val("");
}

/**
 * 关闭页面并返回
 */
function goBackClose(){
	openTab('tabId_id', '数据源级别配置', root+'/srclevel/page/srclevel.jsp');
	parent.$('#centerTab').tabs('close','添加数据源级别配置');
}

