<!-- 基础页面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
	body{
		margin: 0px;
	}
</style>

<script type="text/javascript">
 var root = "${pageContext.request.contextPath}"; //js中存放当前页面的root路径方便调用

// 统一定义两个函数 用于查看 人员信息和索引信息

/**
 * @param indexId 索引id
 * @param indexName 索引姓名
 */
function unify_viewIndex(indexId,indexName){
	var url = root+'/indexlog/il.ac?method=showIndex&indexId='+indexId;
	var name = 'iframe_tabId_IndexView';	
	//如果当前id的tab不存在则创建一个tab
	if(parent.$("#tabId_IndexView").html()==null){
		parent.$('#centerTab').tabs('add',{
			title: '索引['+indexName+']信息显示',         
			closable:true,
			cache : false,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'" id="tabId_IndexView" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}else{
		var reg = /索引\[.*?\]信息显示/gi;
		var tabs = parent.$('#centerTab').tabs('tabs');
		for(var i = 0 ; i < tabs.length ; i++){
			var tab = tabs[i];
			if(reg.test(tab.panel("options").title)){
				parent.$('#centerTab').tabs('update',{
					tab: tab,
					options:{
						title: '索引['+indexName+']信息显示',
						content : '<iframe name="'+name+'" id="tabId_PersonView" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
					}
				});
				parent.$('#centerTab').tabs('select',tab.panel("options").title);
				break;
			}
		}	
	}
}

function unify_viewPerson(personId,personName){
	
	var url = root+'/indexlog/il.ac?method=showPerson&personId='+personId;
	var name = 'iframe_tabId_PersonView';
	//如果当前id的tab不存在则创建一个tab
	if(parent.$("#tabId_PersonView").html()==null){		
		parent.$('#centerTab').tabs('add',{
			title: '居民['+personName+']信息显示',         
			closable:true,
			cache : false,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'" id="tabId_PersonView" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}else{
		var reg = /居民\[.*?\]信息显示/gi;
		var tabs = parent.$('#centerTab').tabs('tabs');
		for(var i = 0 ; i < tabs.length ; i++){
			var tab = tabs[i];
			if(reg.test(tab.panel("options").title)){
				parent.$('#centerTab').tabs('update',{
					tab: tab,
					options:{
						title: '居民['+personName+']信息显示',
						content : '<iframe name="'+name+'" id="tabId_PersonView" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
					}
				});
				parent.$('#centerTab').tabs('select',tab.panel("options").title);
				break;
			}
		}
	}
}
 
</script>
