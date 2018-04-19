var INUSED_FIELDS={};
var DOMAINS={};
$(function() {  
    // 初始化字段下拉
    createSelect("add_fieldSelect",SELECT_JSON.pList,"codeName","codeId",INUSED_FIELDS);
    // 初始化字段下拉
    createSelect("add_domainSelect",SELECT_JSON.domainList,"DOMAIN_DESC","UNIQUE_SIGN",DOMAINS);
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
	//sel.prepend('<option value="">--请选择--</option>');
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
 * 保存匹配设置
 */
function saveMatchCfg(){
	// 校验数据
	if(!validMatchCfgData()){
		return ;
	}
	// 将按钮置为无效
	var btn = $("#saveBtn");
	//lockBtn(btn);
	var params = {};
	DOMAIN_ID=$("#add_domainSelect").val();
	DOMAIN_DESC= $("#add_domainSelect").find("option:selected").text();
	FIELD_NAME=$("#add_fieldSelect").val();
	FIELD_DESC= $("#add_fieldSelect").find("option:selected").text();
	FIELD_LEVEL=$("#add_fieldlevel").val();
	params.DOMAIN_ID=DOMAIN_ID;
	params.DOMAIN_DESC=DOMAIN_DESC;
	params.FIELD_NAME=FIELD_NAME;
	params.FIELD_DESC=FIELD_DESC;
	params.FIELD_LEVEL=FIELD_LEVEL;
	//alert(params);
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		data : {
            "DOMAIN_ID":DOMAIN_ID,
            "DOMAIN_DESC":DOMAIN_DESC,
            "FIELD_NAME":FIELD_NAME,
            "FIELD_DESC":FIELD_DESC,
            "FIELD_LEVEL":FIELD_LEVEL
	    },
		url : root + '/domainsrclevel/srclevel.ac?method=add',// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
			// 失败回复 btn
			unlockBtn(btn,saveMatchCfg);
		},
		success : function(msg) {
			var messgage = "添加成功!";
			if (msg == null||msg=="") {// 未返回任何消息表示添加成功
				alert(messgage);
				//goBackClose();
				$('#listTable').datagrid('reload');
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
	$("#add_domainSelect").val("");
	$("#add_fieldSelect").val("");
	$("#add_fieldlevel").val("");
}

/**
 * 关闭页面并返回
 */
function goBackClose(){
	var tabId = 'tabId_id';
	var title = '数据源级别配置';
	var url = root+'/srclevel/page/srclevel.jsp';
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
	parent.$('#centerTab').tabs('close','添加数据源级别配置');
}

