<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/page/master.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/query/js/query.js"></script>
<style>
.myTable {
	border-collapse: collapse;
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc; 
	color: #333;
}

.myTable caption {
	font-size: 1.1em;
	font-weight: bold;
	letter-spacing: -1px;
	margin-bottom: 10px;
	padding: 5px;
	text-align: left;
}

.myTable a {
	text-decoration: none;
	border-bottom: 1px dotted #f60;
	color: #f60;
	font-weight: bold;
}

.myTable a:hover {
	text-decoration: none;
	color: #fff;
	background: #f60;
}

.myTable tr th a {
	color: #369;
	border-bottom: 1px dotted #369;
}

.myTable tr th a:hover {
	color: #fff;
	background: #369;
}

.myTable thead tr th {
	text-transform: uppercase;
	background: #e2e2e2;
}

.myTable td, table th {
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding: 5px;
	line-height: 1.8em;
	font-size: 0.8em;
	vertical-align: top;
}

.myTable tr.odd th, table tr.odd td {
	background: #efefef;
}

.r{position:fixed; bottom:0; right:20px;padding:10px;}
</style>
<script type="text/javascript">
	var retiredText = '${retiredMap.fields[3].personValue}';
	var survivingText = '${survivingMap.fields[3].personValue}';
	//调换索引位置
	function changeIndex(){
		var retiredTab = document.getElementById( "retiredTab" ).style.float;
		 if(retiredTab == 'left'){
			document.getElementById( "retiredTab" ).style.float = 'right'
			document.getElementById( "survivingTab" ).style.float = 'left';
			$('#retired').html('目标主索引['+ retiredText+']');
			$('#surviving').html('原主索引['+survivingText+']');
		}else{
			document.getElementById( "retiredTab" ).style.float = 'left'
			document.getElementById( "survivingTab" ).style.float = 'right';
			
			$('#retired').html(retiredText);
			
			$('#retired').html('原主索引['+ retiredText+']');
			$('#surviving').html('目标主索引['+survivingText+']');
		} 
	}
	//合并主索引
	function mergeIndex(){
		var retiredTab = document.getElementById( "retiredTab" ).style.float;
		var retiredPK;
		var survivingPK;
		var text;
		if(retiredTab == 'left'){
			retiredPK = '${retiredMap.fields[0].personValue}';
			survivingPK = '${survivingMap.fields[0].personValue}';
			text = '确定合并主索引？   原主索引：【'+'${retiredMap.fields[3].personValue}'+'】    目标主索引：【'+'${survivingMap.fields[3].personValue}'+'】';
		}else{
			retiredPK = '${survivingMap.fields[0].personValue}';
			survivingPK = '${retiredMap.fields[0].personValue}';
			text = '确定合并主索引？    原索引：【'+'${survivingMap.fields[3].personValue}'+'】    目标主索引：【'+'${retiredMap.fields[3].personValue}'+'】';
		}
		
		$.messager.confirm('消息 ',text,function(r){
		    if (r){
		     	$.ajax({
			        async : false,
			        cache : false,
			        type : 'POST',
			        dataType : "json",
			        data : {
						"retiredPk":retiredPK,
						"survivingPk":survivingPK
			        },
			        url : root + '/index/index.ac?method=merge',// 请求的action路径
			        error : function() {// 请求失败处理函数
			            alert('请求失败');
			        },
			        success : function(data) {
			            var messgage = "主索引合并成功!";
			            if (data == null) {// 未返回任何消息表示添加成功
			                // 刷新表格
			            	 window.parent.d_close();
			            } else if (data.errorMsg != null) {// 返回异常信息
			                messgage = data.errorMsg;
			            }
			            $.messager.alert('消息',messgage);
			        }
    			});	 
		    }
		});
	}
	
	
</script>
</head>
<body>
	<div style="padding-bottom: 15px;"><strong style="color: red; font-size: 15px;">* 注意：主索引由左向右进行合并！单击调换按钮更换主索引位置，最终合并结果信息为目标主索引！</strong></div>
	<!-- 对比情况 所有均显示 -->
	<table id="retiredTab" class="myTable" style="float: left;width: 49%;">
		<caption id="retired">原主索引[${retiredMap.fields[3].personValue}]</caption>
		<thead>
			<tr>
				<th></th>
				<th>信息</th>
				<th>信息值</th>
			</tr>
		</thead>
		<tbody>
			<!-- loop -->
			<c:forEach items="${retiredMap.fields }" var="field" varStatus="s">
			<tr>
				<td>${s.count}</td>
				<td>${field.fieldCnName}</td>
				<td>${field.personValue}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
		
	<table id="survivingTab" class="myTable"  style="float: right;width: 49%">
		<caption id="surviving">目标主索引[${survivingMap.fields[3].personValue}]</caption>
		<thead>
			<tr>
				<th></th>
				<th>信息</th>
				<th>信息值</th>
			</tr>
		</thead>
		<tbody>
			<!-- loop -->
			<c:forEach items="${survivingMap.fields }" var="field" varStatus="s">
			<tr>
				<td>${s.count}</td>
				<td>${field.fieldCnName}</td>
				<td>${field.personValue}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/><br/>	
	<div>
    <div class="r">
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="changeIndex()">调换</a>
       	<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="mergeIndex()">确认</a>
    </div>
</div>
</body>
</html>