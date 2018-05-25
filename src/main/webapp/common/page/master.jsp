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
	src="${pageContext.request.contextPath}/js/easyui/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/themes/material-teal/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	var root = "${pageContext.request.contextPath}"; //js中存放当前页面的root路径方便调用
	
	// 显示ajax错误信息
	function showAjaxErrorMsg(msg){
		if (self != top) {
			parent.window.$.messager.alert('消息', msg, 'error');
		}else{
			window.$.messager.alert('消息', msg, 'error');
		}
	}
	
	// jquery全局异常处理
	$.ajaxSetup({
		cache: false,
		error: function(jqXHR, textStatus, errorThrown){
			var status = 0;
			var message;
			if(jqXHR.responseJSON && jqXHR.responseJSON.message){
				message = jqXHR.responseJSON.message;
			}
			switch (jqXHR.status) {
				case (500):
					showAjaxErrorMsg(message || '服务器异常');
					break;
				case (400):
					showAjaxErrorMsg(message || '参数异常');
					break;
				case (401):
					showAjaxErrorMsg(message || '未授权');
					break;
				case (403):
					showAjaxErrorMsg(message || '无权访问');
					break;
				case (404):
					showAjaxErrorMsg(message || '请求资源不存在');
					break;
				case (408):
					showAjaxErrorMsg(message || '请求超时');
					break;
				case (0):
					break;
				default:
					showAjaxErrorMsg(message || '操作异常');
			}
		},
		complete: function(jqXHR, textStatus){
			switch (jqXHR.status) {
				case (302):
					showAjaxErrorMsg('登录超时，请重新登录');
					if (self != top) {
						parent.window.location.reload();
					}else{
						window.location.reload();
					}
					break;
				default:
			}
		},
		dataFilter: function(data, type){// 结果数据过滤
			if(!data) return data;
			var jsonData = eval('(' + data + ')');
			if(jsonData && jsonData.ERROR_MSG){// 显示公共错误信息
				showAjaxErrorMsg(jsonData.ERROR_MSG);
			}else{
				return data;
			}
		}
	})
	
	$(function(){
		// 覆盖默认easyui配置
		$.fn.datagrid.defaults.method = 'get';
		$.fn.datagrid.defaults.singleSelect = true;
		$.fn.datagrid.defaults.fitColumns = true;
		$.fn.datagrid.defaults.striped = true;
		$.fn.datagrid.defaults.showFooter = false;
		$.fn.datagrid.defaults.pagination = true;
		$.fn.datagrid.defaults.loadMsg = '数据加载中,请稍后...';
		
		$.fn.treegrid.defaults.pagination = true;
		
		$.fn.form.defaults.iframe = false;
		$.fn.form.defaults.ajax = true;
		
		$.fn.dialog.defaults.modal = true;
		
		$.fn.window.defaults.modal = true;
		$.fn.window.defaults.maximizable = false;
		$.fn.window.defaults.minimizable = false;
		$.fn.window.defaults.collapsible = false;
		
	})
	
	// 扩展easyui校验
	$.extend($.fn.validatebox.defaults.rules, {
		key: {// 唯一标识
			validator: function(value, param){
				var reg = /^[a-zA-Z0-9_-]*$/;
				return reg.test(value);
			},
			message: '请输入字母、数字、下划线、连接号组合'
		},
		integer: {// 整数
			validator: function(value, param){
				var reg = /^-?\d+$/;
				return reg.test(value);
			},
			message: '请输入整数'
		},
		decimal: {// 小数
			validator: function(value, param){
				var reg = /^[-\+]?\d+(\.\d+)?$/;
				return reg.test(value);
			},
			message: '请输入整数或小数'
		},
	});

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
	
	/**
	 * 创建下拉框
	 * @param id select ID
	 * @param data 数据
	 * @param textStr 文本标志
	 * @param valStr 值标志
	 * @param inused 使用中的数据
	 */
	function createSelect(id,data,textStr,valStr,inused){
		var sel = $("#"+id);
		sel.empty();
		sel.prepend('<option value="">请选择</option>');
		for(var i = 0 ; i < data.length ; i++){
			var obj = data[i];
			var text = obj[textStr];
			var val = obj[valStr];
			if(inused!=undefined && inused!=null && inused[val]!=null)
				continue;
			sel.append('<option value="'+val+'">'+text+'</option>');
		}
	}
	
	// 覆盖默认的alert弹出框
	function alert(info){
		$.messager.alert('提示', info);
	}
	function showMessage(message){
		$.messager.show({title: '提示', msg: message});
		
	}
	
	// 覆盖默认的confirm
	function confirm(message, cb){
		$.messager.confirm({
			title: '确认',
			msg: message,
			fn: function(r){
				if (r){
					if(cb) cb();
				}
			}
		});
	}
	
	//为date类添加一个format方法  
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
	
	// 转换函数数据
	var SELECT_FUNS = [{
		clazz: 'com.sinosoft.block.fun.ID15Function',
		desc: '身份证18位转15位'
	},{
		clazz: 'com.sinosoft.block.fun.ID18Function',
		desc: '身份证15位转18位'
	}];
	
</script>
