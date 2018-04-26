<!-- 基础页面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
body {
	margin: 0px;
}
</style>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	var root = "${pageContext.request.contextPath}"; //js中存放当前页面的root路径方便调用
	
	$(function(){
		// 覆盖默认easyui配置
		$.fn.datagrid.defaults.method = 'get';
		$.fn.datagrid.defaults.singleSelect = true;
		$.fn.datagrid.defaults.fitColumns = true;
		$.fn.datagrid.defaults.striped = true;
	})

	// 公共方法

	/**
	 * 查看主索引
	 * @param indexId 索引id
	 * @param indexName 索引姓名
	 */
	function unify_viewIndex(indexId, indexName) {
		var url = root + '/indexlog/il.ac?method=showIndex&indexId=' + indexId;
		//如果当前id的tab不存在则创建一个tab
		if (parent.$("#tabId_IndexView").html() == null) {
			parent.$('#centerTab').tabs('add',{
				title : '索引[' + indexName + ']信息显示',
				closable : true,
				cache : false,
				//注：使用iframe即可防止同一个页面出现js和css冲突的问题
				content : createTabContent('tabId_IndexView', url)
			});
		} else {
			var reg = /索引\[.*?\]信息显示/gi;
			var tabs = parent.$('#centerTab').tabs('tabs');
			for (var i = 0; i < tabs.length; i++) {
				var tab = tabs[i];
				if (reg.test(tab.panel("options").title)) {
					parent.$('#centerTab').tabs('update',{
						tab : tab,
						options : {
							title : '索引[' + indexName + ']信息显示',
							content : createTabContent('tabId_PersonView', url)
						}
					});
					parent.$('#centerTab').tabs('select', tab.panel("options").title);
					break;
				}
			}
		}
	}

	// 查看人员信息
	function unify_viewPerson(personId, personName) {

		var url = root + '/indexlog/il.ac?method=showPerson&personId=' + personId;
		//如果当前id的tab不存在则创建一个tab
		if (parent.$("#tabId_PersonView").html() == null) {
			parent.$('#centerTab').tabs('add',{
				title : '居民[' + personName + ']信息显示',
				closable : true,
				cache : false,
				//注：使用iframe即可防止同一个页面出现js和css冲突的问题
				content : createTabContent('tabId_PersonView', url)
			});
		} else {
			var reg = /居民\[.*?\]信息显示/gi;
			var tabs = parent.$('#centerTab').tabs('tabs');
			for (var i = 0; i < tabs.length; i++) {
				var tab = tabs[i];
				if (reg.test(tab.panel("options").title)) {
					parent.$('#centerTab').tabs('update',{
						tab : tab,
						options : {
							title : '居民[' + personName + ']信息显示',
							content : createTabContent('tabId_PersonView', url)
						}
					});
					parent.$('#centerTab').tabs('select', tab.panel("options").title);
					break;
				}
			}
		}
	}

	// 打开标签页
	function openTab(tabId, title, url) {
		//如果当前id的tab不存在则创建一个tab
		if (parent.$("#" + tabId).html() == null) {
			parent.$('#centerTab').tabs('add',{
				title : title,
				closable : true,
				cache : false,
				content : createTabContent(tabId, url)
			});
		} else {
			var tabs = parent.$('#centerTab').tabs('tabs');
			for (var i = 0; i < tabs.length; i++) {
				var tab = tabs[i];
				if (tab.panel("options").title == title) {
					parent.$('#centerTab').tabs('update', {
							tab : tab,
							options : {
								content : createTabContent(tabId, url)
							}
						});
					parent.$('#centerTab').tabs('select', title);
					break;
				}
			}
		}
	}
	
	// 返回到标签页
	function backTab(tabId, title, url, cb) {
		//如果当前id的tab不存在则创建一个tab
		if (parent.$("#" + tabId).html() == null) {
			parent.$('#centerTab').tabs('add',{
				title : title,
				closable : true,
				cache : false,
				content : createTabContent(tabId, url)
			});
		} else {
			var tabs = parent.$('#centerTab').tabs('tabs');
			for (var i = 0; i < tabs.length; i++) {
				var tab = tabs[i];
				if (tab.panel("options").title == title) {
					parent.$('#centerTab').tabs('select', title);
					if(cb) cb();
					break;
				}
			}
		}
	}
	
	// 创建tab内容
	function createTabContent(tabId, url){
		return '<iframe name="'+ ('iframe_' + tabId) + '" id="'+ tabId + '" src="' + url + '" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>';
	}
	
</script>
