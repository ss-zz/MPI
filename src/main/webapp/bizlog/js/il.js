//为date类添加一个format方法  
//yyyy 年  
//MM 月  
//dd 日  
//hh 小时  
//mm 分  
//ss 秒  
//qq 季度  
//S  毫秒  
//author: meizz  
Date.prototype.formatDate = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, //month  
		"d+" : this.getDate(), //day  
		"h+" : this.getHours(), //hour  
		"m+" : this.getMinutes(), //minute  
		"s+" : this.getSeconds(), //second  
		"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter  
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

$(function() {
	// 搜索框初始化
	$('#listTable_tb').combobox({
		width : 150
	});
	$('#search_blType').combobox({
		width : 150
	});
	//初始化时间输入框
	init_timeInput();
	// 加载表格数据
	ajaxTable();

});

function bindCombobox(){
	 $("#blInfoSour").combobox({  
		  url:root +"/bizLog/bindCombox",
		  method : "post",  
		  valueField: 'CODE',  
		  textField: 'NAME',   
	  });
}

//初始化列 --日志类型
function initType(val, row){
	if(val == '1'){
		return '匹配';
	}
}
//初始化列 --百分比
function initMatched(val, row){
	if(val != null){
		return val+'%'
	}
}

/**
 * 初始化时间选择框
 */
function init_timeInput(){
	var option = {  
			showSeconds:true,
			editable:false,
			formatter:function(date){
				return date.formatDate("yyyy-MM-dd hh:mm:ss");
			},
			parser:function(dateStr){
				if(dateStr == undefined || dateStr==null || dateStr=="")
					return new Date();
				var regexDT = /(\d{4})-?(\d{2})?-?(\d{2})?\s?(\d{2})?:?(\d{2})?:?(\d{2})?/g;  
				var matchs = regexDT.exec(dateStr);  
				var date = new Array();  
				for (var i = 1; i < matchs.length; i++) {  
					if (matchs[i]!=undefined) {  
						date[i] = matchs[i];  
					} else {  
						if (i<=3) {  
							date[i] = '01';  
						} else {  
							date[i] = '00';  
						}  
					}  
				}  
				return new Date(date[1], date[2]-1, date[3], date[4], date[5],date[6]);
			}
		};
	
	$('#blTime_begin').datetimebox(option); 	
	$('#blTime_begin').attr("readonly","readonly");
	
	$('#blTime_end').datetimebox(option); 	
	$('#blTime_end').attr("readonly","readonly");
}

/** --------------table------------------* */
/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').datagrid({
		toolbar : "#listTable_tb",
		singleSelect : true,//单选
		pagination : true,//分页
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

function timestampToTime(timestamp) {
        var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        return Y+M+D+h+m+s;
}

function initDate(val, row){
	var time1 = new Date(val).formatDate("yyyy-MM-dd hh:mm:ss");
	return time1;
}

//构建索引查看连接
function buildMatchUrl(val, row) {
	var logId = row.BLIDXLOGID;
	return '<a href="#" onclick="viewMatch(\'' + logId + '\');">查看</a>';
}

function viewMatch(logId){
	var url = root+'/bizLog/view?logId='+logId;
	var tabId = "tabId_logDetailView";
	var title = "索引日志处理详情";
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
}

// 构建操作类型描述
function buildTypeStr(val, row) {
	switch (val) {
	case "1":
		return "匹配";
		break;
	default:
		return "";
		break;
	}
}

//构建操作类型描述
function buildStyleStr(val, row) {
	switch (val) {
	case "1":
		return "自动合并";
		break;
	case "2":
		return "自动新建";
		break;
	case "3":
		return "自动拆分";
		break;
	case "4":
		return "人工合并";
		break;
	case "5":
		return "人工新建";
		break;
	case "6":
		return "人工拆分";
		break;
	default:
		return "";
		break;
	}
}

// 查询匹配详情
function searchListTable() {

	// 查询的时候重置回第一页
	$("#listTable").datagrid("options").pageNumber = 1;
	var blType = $('#blType option:selected').val();
	var blInfoSour = $('#blInfoSour').val();
	var blTime_begin = $("#search_optime_begin").datetimebox('getValue');
	var blTime_end = $("#search_optime_end").datetimebox('getValue');
	var blUserId = $("#blUserId").val();
	var blMatched_end = $('#blMatched_end').val();
	var blMatched_begin = $('#blMatched_begin').val();
	$("#listTable").datagrid({
		queryParams : {
			"blType" : blType,
			"blInfoSour" : blInfoSour,
			"blUserId" : blUserId,
			"blTime_begin":blTime_begin,
			"blTime_end":blTime_end,
			"blMatched_begin":blMatched_begin,
			"blMatched_end":blMatched_end
		},
		url:root +"/bizLog/query"
	});
}

// 搜索条件重置
function searchReset() {
	$("#blMatched_begin").val('');
	$("#blMatched_end").val('');
	
	$("#blInfoSour").combobox('setValue', '');	
	$("#search_optime_begin").datetimebox('setValue', '');
	$("#search_optime_end").datetimebox('setValue', '');
	$("#blUserId").val('');
	$("#blType").combobox('setValue', '');	

}

// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 将匹配度转化为百分数
function matchDegreeToPercent(degree) {
	if (isNumeric(degree)) {
		var degreeNum = parseFloat(degree);
		return (Math.round(degreeNum * 100 * 10000) / 10000) + "%";
	} else {
		return degree;
	}
}

// 判断字符串是否是数字
function isNumeric(str) {
	return (str.search(/^[\+\-]?\d+\.?\d*$/) == 0);
}
