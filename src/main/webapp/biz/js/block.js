var INUSED_FIELDS={};

$(function() {
	// 加载表格数据
	ajaxTable();
	
	var p = $('#listTable').datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',
		afterPageText: '页	共 {pages} 页',	
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',   
		pageSize:10,
		showPageList:false
		
	});
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

/** --------------table------------------* */
/**
 * 加载表格数据
 */
function ajaxTable() {
	// 加载表格
	$('#listTable').datagrid({
		toolbar:[
		{
			text : '添加初筛配置',
			iconCls : 'icon-add',
			handler : function(){
				openAddPage();	
			}
		}],
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
// 刷新表格
function reloadTable() {
	$('#listTable').datagrid('reload');
}

// 状态 编码转换
function buildState(val,row){
	if(val == '1'){
		return '已生效';
	}else{
		return '未生效';
	}
}

function initDate(val,row){
	return val.substring(0,val.length-2);
}

// 创建操作连接
function buildOptLink(val,row){
	var link = '';
	link +='<a href="#" onclick="openViewPage(\''+row.blockId+'\')">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	if(row.state != "1"){
		var name = row.blockDesc;
		var id = row.blockId;
		link +='<a href="#" onclick="activateCfg(\''+id+'\',\''+name+'\',this)">使用</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	}
	return link;
}

// 打开查看初筛配置页面
function openViewPage(cfgId){
	var tabId = 'tabId_block_cfg_view';
	var title = '初筛配置详情';
	var url = root+'/blockCfgbiz/view?cfgId='+cfgId;
	var name = 'iframe_'+tabId;
	openTab(tabId,title,url,name);
}

function openCurrentPage(){
	var tabId = 'tabId_current_block_view';
	var title = '当前初筛配置详情';
	var url = root+'/blockCfgbiz/current';
	var name = 'iframe_'+tabId;	
	openTab(tabId,title,url,name);
}

function openTab(tabId,title,url,name){
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
			if(tab.panel("options").title==title){
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

// 使配置生效
function activateCfg(cfgId,cfgName,btn){
	if(confirm("确认使用["+cfgName+"]初筛配置么?\n\r(此配置生效后,现使用的配置将失效)")){
		lockBtn(btn);
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "text",
			data : {
				cfgId:cfgId
			},
			url : root + '/blockCfgbiz/effect',// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
				unlockBtn(btn,function(){
					activateCfg(cfgId,cfgName,btn);
				});
			},
			success : function(data) {
				var messgage = "操作成功!";
				if (data != null && data != "") {//返回消息表示失败
					messgage = data;
					unlockBtn(btn,function(){
						activateCfg(cfgId,cfgName,btn);
					});
				} else{
					reloadTable();
				}
				alert(messgage);
			}
		});		
	}
}

function openAddPage(){
	var url = root+'/blockCfgbiz/toAdd';
	var name = 'iframe_tabId_blockCfgAdd';	
	//如果当前id的tab不存在则创建一个tab
	if(parent.$("#tabId_blockCfgAdd").html()==null){
		parent.$('#centerTab').tabs('add',{
			title: '添加初筛配置',	
			closable:true,
			cache : false,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'" id="tabId_blockCfgAdd" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}else{
		parent.$('#centerTab').tabs('select','添加初筛配置');
	}
}
