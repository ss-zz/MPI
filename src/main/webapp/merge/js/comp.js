$(function() {
	// 初始化
	init_fun();
});

/**
 * 初始化
 */
function init_fun(){
	$("#dataFromSpan").text(BASE_DATAS.survive.domainDesc);
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
		if($.type(val) === "string"){
			textVal = val;
		}else{
			textVal = val;
		}
		tr.children().get(startCell+index).innerHTML=textVal;
	});
}

/**
 * 合并用户
 */
function mergePerson(){
	confirm("确认要合并么?", function(){
		$.ajax({
			url : root + '/merge/merge.ac?method=merge',
			type : 'POST',
			data : {
				"survivePersonId":BASE_DATAS.survive.fieldPk,
				"retiredPersonId":BASE_DATAS.retired.fieldPk
			},
			success : function(data) {
				showMessage("操作成功");
				goBackClose(true);
			}
		});
	})
	
}

/**
 * 关闭并返回
 * @param {Boolean} refresh 是否刷新原窗口
 */
function goBackClose(refresh){
	backTab('tabId_me', '合并居民信息', root+'/merge/page/merge.jsp', function(){
		parent.tabCallPass("iframe_tabId_me", "reloadTable");
	});
	parent.$('#centerTab').tabs('close','合并居民详情比较');
}
