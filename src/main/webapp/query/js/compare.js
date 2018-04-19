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
function splitToIndex(){
    if(!confirm("确认拆分么?"))
        return;
    
    // 锁定按钮防止重复点击
    lockBtn("#split_person_btn");
    
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        dataType : "text",
        data : {
            "indexId":$("#selectIndexId").val(),
            "personId":$("#selectPersonId").val(),
            "fromIndexId":$("#fromIndexId").val()
        },
        url : root + '/manual/manual.ac?method=splitToIndex',// 请求的action路径
        error : function() {// 请求失败处理函数
            alert('请求失败');
            // 解锁按钮
            unlockBtn("#split_person_btn",splitToIndex);
        },
        success : function(data) {
            if (data == null || data == "") {// 未返回任何消息表示添加成功
                alert("操作成功!");
                closeOneTwo();
            } else {// 返回异常信息
                alert(data);
                // 合并失败 解锁按钮
                unlockBtn("#split_person_btn",splitToIndex);
            }
        }
    });    
}

/**
 * 关闭并返回到第一步页面
 */
function closeOneTwo(){
    var url = root+'/query/page/query.jsp';
    var tabId = "tabId_qi";
    var title = "主索引记录查询";
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
        parent.$('#centerTab').tabs('select',title);
    }     
    
    parent.$('#centerTab').tabs('close','选择目标索引');
    parent.$('#centerTab').tabs('close','拆分至索引明细对比');
}
/**
 * 关闭并返回
 * @param {Boolean} refresh 是否刷新原窗口
 */
function goBackClose(){
    var personId = $("#selectPersonId").val();
    var indexId = $("#fromIndexId").val();
    var url = root+'/query/query.ac?method=toQueryIdx&personId='+personId+'&indexId='+indexId;
    var tabId = "tabId_splitToIndex";
    var title = "选择目标索引";
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
        var tabs = parent.$('#centerTab').tabs('tabs');
        for(var i = 0 ; i < tabs.length ; i++){
            var tab = tabs[i];
            if(tab.panel("options").title == title){
                parent.$('#centerTab').tabs('update',{
                    tab: tab,
                    options:{
                        content : '<iframe name="'+name+'" id="'+tabId+'" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
                    }
                });
                parent.$('#centerTab').tabs('select',title);
                break;
            }
        }   
    } 
    parent.$('#centerTab').tabs('close','拆分至索引明细对比');
}
