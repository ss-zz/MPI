
$(function() {	
	// 初始化
	init_fun();
});

/**
 * 初始化
 */
function init_fun(){
    $("#dataFromSpan").text(BASE_DATAS.survive.DOMAIN_DESC);
	// 循环 构建表格
	$.each(BASE_DATAS.fields,function(idx,data){
	   buildTr("data_show_table",data.codeId,3,true);
       addCellData(data.codeId,0,[
            data.codeName,
            BASE_DATAS.survive[data.codeId],
            BASE_DATAS.retired[data.codeId]
        ]);
	});
}


/**
 * 创建tr
 * @param {String} tbId  tbody的Id
 * @param {String} rowid  创建出来tr的Id
 * @param {Integer} cellNum  创建的td数量
 * @param {String} thFirst  首列是否Th
 */
function buildTr(tbId,rowid,cellNum,thFirst){
	var count = thFirst?(cellNum-1):cellNum;
	var html = '<tr id="'+rowid+'">';
	if(thFirst)
		html+='<th></th>';
	for(var i = 0 ; i < count ; i++){
		html+='<td></td>';		
	}
	html+='</tr>';
	$("#"+tbId).append(html);
}

/**
 * 添加 cell 数据
 * @param {String} rowid  行id
 * @param {Integer} startCell  起始添加的列数
 * @param {Array} datas  数据
 */
function addCellData(rowid,startCell,datas){
	var tr = $("#"+rowid);
	$.each(datas,function(index,val){
		var textVal;
		console.dir($.type(val));
		if($.type(val) === "string"){
			textVal = val;
		}else{
			textVal = val;
		}
		tr.children().get(startCell+index).innerHTML=textVal;
	});
}
/**
 * 锁定按钮
 */
function lockBtn(btn){
    $(btn).unbind('click').removeAttr('onclick');
    $(btn).attr("disabled",true);  
}

/**
 * 解锁按钮
 */
function unlockBtn(btn,handler){
    $(btn).bind("click",handler);
    $(btn).attr("disabled",false);      
}
/**
 * 合并用户
 */
function mergePerson(){
    if(!confirm("确认要合并么?"))
        return;
    
    // 锁定按钮防止重复点击
    lockBtn("#merge_person_btn");
    
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        dataType : "text",
        data : {
            "survivePersonId":BASE_DATAS.survive.FIELD_PK,
            "retiredPersonId":BASE_DATAS.retired.FIELD_PK
        },
        url : root + '/merge/merge.ac?method=merge',// 请求的action路径
        error : function() {// 请求失败处理函数
            alert('请求失败');
            // 解锁按钮
            unlockBtn("#merge_person_btn",mergePerson);
        },
        success : function(data) {
            if (data == null || data == "") {// 未返回任何消息表示添加成功
                alert("操作成功!");
                // 关闭本tab 刷新原窗口列表
                goBackClose(true);
            } else {// 返回异常信息
                alert(data);
                // 合并失败 解锁按钮
                unlockBtn("#merge_person_btn",mergePerson);
            }
        }
    });    
}
/**
 * 关闭并返回
 * @param {Boolean} refresh 是否刷新原窗口
 */
function goBackClose(refresh){
    var tabId = 'tabId_me';
    var title = '合并居民信息';
    var url = root+'/merge/page/merge.jsp';
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
    }else {
        if(refresh){
	        var tabs = parent.$('#centerTab').tabs('tabs');
	        for(var i = 0 ; i < tabs.length ; i++){
	            var tab = tabs[i];
	            if(tab.panel("options").title==title){
	                parent.$('#centerTab').tabs('update',{
	                    tab: tab,
	                    options:{
	                        content : '<iframe name="'+name+'" id="'+tabId+'" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
	                    }
	                });	                
	                break;
	            }
	        }            
        }
        
        parent.$('#centerTab').tabs('select',title);
    }
    
    parent.$('#centerTab').tabs('close','合并居民详情比较');
}
