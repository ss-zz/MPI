$(function() {
	$('#centerTab').tabs({
		tools:[{
			iconCls:'icon-cancel',
			handler: function(){
				$.messager.confirm('关闭提示', '您确定其他标签页吗?', function(r){
					if(r){
						var $tab = $('#centerTab');
						var allTabs = $tab.tabs('tabs');
						var selectTab = $tab.tabs('getSelected');
						var selectTabIndex = $tab.tabs('getTabIndex',selectTab);
						for(var i = allTabs.length - 1; i >= 0 ; i--){
							var tab = allTabs[i];
							var idx = $tab.tabs('getTabIndex',tab);
							if(selectTabIndex !== idx){
								$tab.tabs('close', idx);
							}
						}
					}
				});
			}
		}/*,{
			iconCls:'icon-remove',
			handler: function(){
				$.messager.confirm('关闭提示', '您确定关闭所有标签页吗?', function(r){
					if(r){
						var $tab = $('#centerTab');
						var allTabs = $tab.tabs('tabs');
						var selectTab = $tab.tabs('getSelected');
						var selectTabIndex = $tab.tabs('getTabIndex',selectTab);
						for(var i = allTabs.length - 1; i >= 0 ; i--){
							var tab = allTabs[i];
							var idx = $tab.tabs('getTabIndex',tab);
							$tab.tabs('close', idx);
						}
					}
				});
			}
		}*/]
	});
});

// 退出登录
function logout(){
	$.messager.confirm('退出提示', '您确定退出吗?', function(r){
		if(r){
			window.location = root+'/logout';
		}
	});
}

/**
 * 创建新选项卡
 * @param tabId	选项卡id
 * @param title	选项卡标题
 * @param url	  选项卡远程调用路径
 */
function addTab(tabId,title,url){
	//如果当前id的tab不存在则创建一个tab
	if($("#"+tabId).html()==null){
		var name = 'iframe_'+tabId;
		$('#centerTab').tabs('add',{
			title: title,
			closable:true,
			cache : false,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'" id="'+tabId+'" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}else{
		$('#centerTab').tabs('select',title);
	}
}

/**
 * 不同tab间传递函数调用
 * @param iframeId tab的iframe id
 * @param funName 要调用的函数名
 */
function tabCallPass(iframeName,funName){
	eval('window.frames["'+iframeName+'"].'+funName+'()');
}

