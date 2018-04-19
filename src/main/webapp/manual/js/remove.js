$(function() {
	// 加载表格数据
	ajaxTable();
    
    var p = $('#listTable').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',
        afterPageText: '页    共 {pages} 页',    
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',   
        pageSize:10,
        pageList:[10,20,50,100]
    });
    
    //初始化详细显示窗口
    setWindow_view();
});
// 锁定按钮
function lockBtn(btn){
    $(btn).unbind('click').removeAttr('onclick');
    $(btn).attr("disabled",true);  
}

// 解锁按钮
function unlockBtn(btn,handler){
    $(btn).bind("click",handler);
    $(btn).attr("disabled",false);      
}
/** --------------table------------------* */
/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').datagrid({
		toolbar:"#listTable_tb",
        singleSelect:true,//单选
        pagination:true,//分页
		loadMsg : '数据加载中,请稍后...',
		onLoadError : function() {
			alert('数据加载失败!');
		},
		queryParams : {// 查询条件
		},
		onClickRow : function(rowIndex, rowData) {
			// 取消选择某行后高亮
			$('#listTable').datagrid('unselectRow', rowIndex);
		},
		onLoadSuccess : function(data) {
			var value = $('#listTable').datagrid('getData')['errorMsg'];
			if (value != null) {
				alert("错误消息:" + value);
			}
		}
	}).datagrid('acceptChanges');
}

// 构建匹配居民列表查看连接
function buildMatchUrl(val,row){
    var personCount = row.PERSON_COUNT;
    if(personCount>1){
        return '<a href="#" onclick="viewPerson(\''+val+'\');">查看拆分</a>';
    }else{
        return '查看拆分';
    }
    
}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 查询匹配详情
function searchListTable() {   
	// 查询的时候重置回第一页
	$("#listTable").datagrid("options").pageNumber = 1; 
	
	var personName = $("#search_personName").val();
	var personSex = $("#search_personSex").combobox('getValue');
	
	$("#listTable").datagrid({
		queryParams : {
            "name":personName,
            "sex":personSex
		}
	});
}

// 搜索条件重置
function searchReset(){
	$("#search_opstatus").combobox('setValue','');
	$("#search_personName").val('');
	$("#search_personSex").combobox('setValue','');
}

/**
 * 匹配信息显示窗口定义
 */
function setWindow_view(){
    $('#window_view').window({  
        width:800,  
        height:500,  
        modal:true, // 模态
        closed:true, // 初始关闭
        collapsible:false, // 不可卷起
        minimizable:false, // 不可最小化
        maximizable:true, // 可以最大化
        closable:true, //可以关闭
        draggable:false, // 不可拖拽
        resizable:false // 不可改变大小        
    });     
}

function closeWindow_view(){
    $("#window_view").window('close');
    cleanIndexInfo();
}

function viewPerson(indexId){	
	$("#selected_indexId").val(indexId);
	//设置窗口页面索引信息显示
	showIndexInfo(indexId);	
	// 加载表格
	$('#detailTable').datagrid({
		toolbar :"#detailTable_toolbar",
        singleSelect:true,//单选
        pagination:true,//分页
		loadMsg : '数据加载中,请稍后...',
		onLoadError : function() {
			alert('数据加载失败!');
		},
		queryParams : {// 查询条件
			"indexId":indexId
		},
		onClickRow : function(rowIndex, rowData) {
			// 取消选择某行后高亮
			$('#detailTable').datagrid('unselectRow', rowIndex);
		},
		onLoadSuccess : function(data) {
			var value = $('#detailTable').datagrid('getData')['errorMsg'];
			if (value != null) {
				alert("错误消息:" + value);
			}
		}
	}).datagrid('acceptChanges');	
	
    var q = $('#detailTable').datagrid('getPager');
    $(q).pagination({
        beforePageText: '第',
        afterPageText: '页    共 {pages} 页',    
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',   
        pageSize:10,
        pageList:[10,20,50,100]
    });	
    
     $("#window_view").window('open');
}

function showIndexInfo(indexId){
	//取得索引记录
	var datas = $("#listTable").datagrid("getRows");
	for(var i=0;i<datas.length;i++){
		var row = datas[i];
		if(row.INDEX_ID == indexId){
			
			$("#index_name_show").html(row.NAME);
			$("#index_sex_show").html(row.sexName);
			$("#index_birthday_show").html(row.BIRTH_DATE);
			$("#index_idcard_show").html(row.ID_CARD_NUM);
			$("#index_phone_show").html(row.PHONE_ONE);				
			break;
		}
	}

}

function cleanIndexInfo(){
	$("#index_name_show").html("");
	$("#index_sex_show").html("");
	$("#index_birthday_show").html("");
	$("#index_idcard_show").html("");
	$("#index_phone_show").html("");
}

// 刷新居民信息显示表格
function reloadDetailTable() {
	$('#detailTable').datagrid('reload');
}

// 执行拆分方法
function splitPerson(){
	var row = $('#detailTable').datagrid('getSelected');
	if(row == undefined || row == null){
		alert("请选择要拆分的居民信息");
		return;
	}
	if(!confirm("请确认拆分居民信息"))
		return;
	// 锁定按钮防止重复点击
	lockBtn("#toobar_split_btn");
	//取得选中的居民id
	var personId = row.PERSON_ID;
	//取得选中的索引id
	var indexId = $("#selected_indexId").val();   
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        dataType : "json",
        data : {
			"indexId":indexId,
			"personId":personId
        },
        url : root + '/manual/manual.ac?method=split',// 请求的action路径
        error : function() {// 请求失败处理函数
            alert('请求失败');
            // 提交完成解锁按钮
            unlockBtn("#toobar_split_btn",splitPerson);
        },
        success : function(data) {
            var messgage = "成功拆分!";
            if (data == null) {// 未返回任何消息表示添加成功
                // 刷新表格
                reloadTable();
                reloadDetailTable();
            } else if (data.errorMsg != null) {// 返回异常信息
                messgage = data.errorMsg;
            }
            $("#add_message").html(messgage);
             // 提交完成解锁按钮
            unlockBtn("#toobar_split_btn",splitPerson);
        }
    });	
}
