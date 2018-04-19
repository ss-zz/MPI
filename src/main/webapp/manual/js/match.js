var PAGE_INFO = {
    data:[],
    total:0,
    start:0,
    end:0,
    selectedIndexIds:[]
};

$(function() {	
	// 初始化
	init_fun();
	// 初始化摘要窗口
	setWindow_view();
});

/**
 * 初始化
 */
function init_fun(){
	// 初始化 PAGE_INFO
	PAGE_INFO.total = BASE_DATAS.total;
	PAGE_INFO.start = 1;
	PAGE_INFO.end=(PAGE_INFO.total>4?4:PAGE_INFO.total);

	// 渲染居民和字段名
	renderPersonInfo();
	
/*	// 渲染分页信息
	renderPageInfo();
	// 翻页按钮控制
	showHidePageBtn();
	// 查询数据
	queryData(PAGE_INFO.start,PAGE_INFO.end,processData);*/
	
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
		tr.children().get(startCell+index).innerHTML=val;
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
 * 队列操作
 * @param {Array} data
 */
function processData(data,headInsert){
	if(headInsert){
		// 数组倒置
		data.reverse();
		// 队列弹出 
		for(var i = 0 ; i < data.length ; i++){
			// 头部添加
			PAGE_INFO.data.unshift(data[i]);
			// 大于4个的时候弹出一个
			if(PAGE_INFO.data.length>4){
				// 尾部弹出
				PAGE_INFO.data.pop();
			}	
		}	
	}else{
		// 队列弹出 
		for(var i = 0 ; i < data.length ; i++){
			//尾部添加
			PAGE_INFO.data.push(data[i]);
			// 大于4个的时候弹出一个
			if(PAGE_INFO.data.length>4){
				// 头部弹出
				PAGE_INFO.data.shift();
			}	
		}			
	}
	// 渲染索引数据
	renderIndexInfo();

}

/**
 * 队列操作
 * @param {Array} data
 */
function processDataClean(data){
	delete PAGE_INFO.data;
	PAGE_INFO.data = [];
	// 队列弹出 
	for(var i = 0 ; i < data.length ; i++){
		//尾部添加
		PAGE_INFO.data.push(data[i]);
		// 大于4个的时候弹出一个
		if(PAGE_INFO.data.length>4){
			// 头部弹出
			PAGE_INFO.data.shift();
		}	
	}			
	// 渲染索引数据
	renderIndexInfo();
}

/**
 * 渲染居民信息
 */
function renderPersonInfo(){
	$.each(BASE_DATAS.fields,function(index,val){
		console.dir(val.codeId);
		console.dir(val.codeName);
		buildTr("data_show_table",val.codeId,10,true);
		addCellData(val.codeId,0,[
		    val.codeName,
		    BASE_DATAS.person[val.codeId]
		]);
	});
}

/**
 * 索引数据渲染方法 
 */
function renderIndexInfo(){
	$.each(PAGE_INFO.data,function(idx,val){
		// 匹配度情况
		var mr = val.result;
		var index = val.index;		
		// 渲染表头
		var html = '综合匹配度:'+matchDegreeToPercent(mr.matchDegree)+'<br />';
		if(idx > 0){
			html+= '<a href="#" title="左移" onclick="moveLeftFun('+idx+');">&lt;&lt;</a>&nbsp;&nbsp;';
		}
		html+='<input type="button" value="合并" id="add_to_index_btn_'+index.indexId+'" onclick="addToIndex(\''+index.indexId+'\',\''+index.name+'\')" />';
		if(idx < (PAGE_INFO.data.length-1)){
			html+= '&nbsp;&nbsp;<a href="#" title="右移" onclick="moveRightFun('+idx+');">&gt;&gt;</a>';
		}
		addCellData("index_comp_tr",idx,[html]);
		var fieldDegrees = parseFieldMatchResult(mr.fieldMatDegrees);
		for(var i = 0 ; i < BASE_DATAS.fields.length ; i++){
			var field = BASE_DATAS.fields[i];
			addCellData(field.codeId,(2+idx*2),[
			    matchDegreeToPercent(fieldDegrees[field.codeId]),
			    index[field.codeId]
			]);
		}
	});	
	
	// 清除
	for(var i = PAGE_INFO.data.length ; i< 4 ; i++){
		addCellData("index_comp_tr",i,[""]);
		for(var j = 0 ; j < BASE_DATAS.fields.length ; j++){
			var field = BASE_DATAS.fields[j];
			addCellData(field.codeId,(2+i*2),["",""]);
		}		
	}
}

/**
 * 将字段匹配字符串解析为对象 
 * @param {String} matchStr like:'idCardNum=1.0,nongheCardNum=0.0,...'
 * @returns {Object} like {idCardNum:1.0,nongheCardNum:0.0,...}
 */
function parseFieldMatchResult(matchStr){
	var result = {};
	var group = matchStr.split(',');
	for(var i = 0 ; i<group.length;i++){
		var field = group[i].split("=");
		result[field[0]]=field[1];
	}
	return result;
}

/**
 * 渲染分页信息
 */
function renderPageInfo(){
	var t = '[总计:&nbsp;&nbsp;'+PAGE_INFO.total+'&nbsp;&nbsp;条,当前显示&nbsp;&nbsp;'+PAGE_INFO.start+'&nbsp;&nbsp;-&nbsp;&nbsp;'+PAGE_INFO.end+'&nbsp;&nbsp;条]';
	$("#pageInfo_span").html(t);
}

/**
 * 分页按钮显隐控制
 */
function showHidePageBtn(){
	$("#page_prev_btn").show();
	$("#page_next_btn").show();
	if(PAGE_INFO.start == 1){
		$("#page_prev_btn").hide();
	}
	
	if(PAGE_INFO.end == PAGE_INFO.total){
		$("#page_next_btn").hide();
	}
}

/**
 * 上一页或下一页
 * @param {Integer} plus
 */
function next(plus){
	var st = PAGE_INFO.start;
	var en = PAGE_INFO.end;
	if(st+plus<1){
		return;
	} else {
		PAGE_INFO.start = st+plus;
	}
	
	if(en+plus>PAGE_INFO.total){
		return;
	} else {
		PAGE_INFO.end = en+plus;
	}
	// 渲染分页信息
	renderPageInfo();
	// 翻页按钮显隐控制
	showHidePageBtn();	
	// 查询数据
	if(plus>0)
		forward();
	else
		Backward();

}

function forward(){
	queryData(PAGE_INFO.end,PAGE_INFO.end,processData,false);
}

function Backward(){
	queryData(PAGE_INFO.start,PAGE_INFO.start,processData,true);
}

/**
 * 后台取得索引匹配数据
 * @param {Integer} start 开始
 * @param {Integer} end 结束
 * @param {Function} handler 返回数据处理函数
 * @param {Boolean} headInsert 是否头部插入数据
 */
function queryData(start,end,handler,headInsert){
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        dataType : "json",
        data : {
            "personId":BASE_DATAS.person.FIELD_PK,
            "start":start,
            "end":end
        },
        url : root + '/manual/manual.ac?method=matchList',// 请求的action路径
        error : function() {// 请求失败处理函数
            alert('请求失败');
        },
        success : function(data) {
        	var hi = false;
        	if(headInsert)
        		hi = headInsert;
        	handler(data,hi);
        }
    });
}

/**
 * 后台取得索引匹配数据
 * @param {Function} handler 返回数据处理函数
 * @param {Boolean} headInsert 是否头部插入数据
 */
function queryDataByIndexId(handler){
	if(PAGE_INFO.selectedIndexIds.length == 0){
		handler([]);
		return;
	}
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        dataType : "json",
        traditional:true,
        data : {
            "personid":BASE_DATAS.person.FIELD_PK,
            "idxIds":PAGE_INFO.selectedIndexIds
        },
        url : root + '/manual/manual.ac?method=matchListByIds',// 请求的action路径
        error : function() {// 请求失败处理函数
            alert('请求失败');
        },
        success : function(data) {
        	handler(data);
        }
    });
}

/**
 * 将居民合并至索引
 * @param {String} indexId 索引id
 * @param {String} indexName 索引姓名
 */
function addToIndex(indexId,indexName){
	if(!confirm("确认将居民["+BASE_DATAS.person.name+"]合并到索引["+indexName+"]下么?"))
		return;
	
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        dataType : "json",
        data : {
            "opId":BASE_DATAS.opId,
            "personId":BASE_DATAS.person.personId,
            "indexId":indexId
        },
        url : root + '/manual/manual.ac?method=addToIndex',// 请求的action路径
        error : function() {// 请求失败处理函数
            alert('请求失败');
         // 解锁按钮
            unlockBtn("#add_to_index_btn_"+indexId,addToIndex);
        },
        success : function(data) {
            if (data == null || data == "") {// 未返回任何消息表示添加成功
            	alert("操作成功!");
            	// 关闭本tab 刷新原窗口列表
            	goBackClose();
            } else {// 返回异常信息
            	alert(data);
                // 合并失败 解锁按钮
                unlockBtn("#add_to_index_btn_"+indexId,addToIndex);
            }
        }
    });
}

/**
 * 生成新索引
 */
function addNewIndex(){
	if(!confirm("请确认新建索引"))
		return;
	
    // 锁定按钮防止重复点击
    lockBtn("#add_new_index_btn");
    
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        dataType : "text",
        data : {
            "opId":BASE_DATAS.opId,
            "personId":BASE_DATAS.person.personId
        },
        url : root + '/manual/manual.ac?method=addNewIndex',// 请求的action路径
        error : function() {// 请求失败处理函数
            alert('请求失败');
            // 解锁按钮
            unlockBtn("#add_new_index_btn",addNewIndex);
        },
        success : function(data) {
            if (data == null || data == "") {// 未返回任何消息表示添加成功
            	alert("操作成功!");
            	// 关闭本tab 刷新原窗口列表
            	goBackClose();
            } else {// 返回异常信息
            	alert(data);
                // 合并失败 解锁按钮
                unlockBtn("#add_new_index_btn",addNewIndex);
            }
        }
    });
}

/**
 * 关闭页面并返回
 */
function goBackClose(){
	var tabId = 'tabId_ma';
	var title = '人工审核记录';
	var url = root+'/manual/page/add.jsp';
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
	parent.$('#centerTab').tabs('close','查看匹配结果');
}

/**
 * 转换匹配度为百分数
 * @param {String} degree 匹配度字串
 * @returns {String} 百分比字串 大于1将转化为100%
 */
function matchDegreeToPercent(degree){
	if(degree == undefined || degree == null || degree == '')
		return '';
	if(isNumeric(degree)){
		var degreeNum = parseFloat(degree);
		if(degreeNum>1)
			degreeNum = 1;
		return (Math.round(degreeNum*100*100)/100)+"%";
	}else{
		return degree;
	}
}

/**
 * 判断字符串是否是数字
 */
function isNumeric(str){ 
        return (str.search(/^[\+\-]?\d+\.?\d*$/)==0);
}

//========================一下是窗口定义
/**
 * 定义摘要窗口
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
        draggable:true, // 可拖拽
        resizable:true // 可改变大小        
    });     
}

/**
 * 关闭摘要窗口方法
 */
function closeWindow_view(){
    $("#window_view").window('close');
}

/**
 * 摘要表格刷新方法
 */
function reloadDetailTable() {
	$('#detailTable').datagrid('reload');
}

function openSummaryWin(){	
	// 加载表格
	$('#detailTable').datagrid({
		toolbar :"#detailTable_toolbar",
        singleSelect:false,//单选
        pagination:false,//分页
		loadMsg : '数据加载中,请稍后...',
		onLoadError : function() {
			alert('数据加载失败!');
		},
		queryParams : {// 查询条件
			"personId":BASE_DATAS.person.personId
		},
		onClickRow : function(rowIndex, rowData) {
			// 取消选择某行后高亮
			$('#listTable').datagrid('unselectRow', rowIndex);
		},
		onSelect : rowSelectEvent,
		onUnselect : rowSelectEvent,
		onLoadSuccess : function(data) {
			var value = $('#detailTable').datagrid('getData')['errorMsg'];
			if (value != null) {
				alert("错误消息:" + value);
			}
			// 去掉全选
			$('#detailTable').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("disabled", true);
		}
	}).datagrid('acceptChanges');	

     $("#window_view").window('open');
}

/**
 * 行选事件
 * @param rowIndex
 * @param rowData
 */
function rowSelectEvent(rowIndex, rowData){
	var rows = $('#detailTable').datagrid("getSelections");
	if(rows.length>4){
		alert("最多只能选择4条索引数据进行对比!");
		$('#detailTable').datagrid("unselectRow",rowIndex);
		return;
	}
	delete PAGE_INFO.selectedIndexIds;
	PAGE_INFO.selectedIndexIds = [];
	$.each(rows,function(idx,val){
		PAGE_INFO.selectedIndexIds[idx] = val.INDEX_ID;
	});
}

function closeAndRerender(){
	if(PAGE_INFO.selectedIndexIds.length == 0 && !confirm("未选择任何对比索引,确认关闭窗口么?")){
		return;
	}
	queryDataByIndexId(processDataClean);
	closeWindow_view();
}

function moveLeftFun(idx){
	var tmp = PAGE_INFO.data[idx];
	PAGE_INFO.data[idx] = PAGE_INFO.data[idx-1];
	PAGE_INFO.data[idx-1] = tmp;
	renderIndexInfo();
}

function moveRightFun(idx){
	var tmp = PAGE_INFO.data[idx];
	PAGE_INFO.data[idx] = PAGE_INFO.data[idx+1];
	PAGE_INFO.data[idx+1] = tmp;
	renderIndexInfo();	
}

