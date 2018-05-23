<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/query/js/query.js"></script>
<style>
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
			text = '确定合并主索引？   原主索引：【'+'${retiredMap.fields[3].personValue}'+'】	目标主索引：【'+'${survivingMap.fields[3].personValue}'+'】';
		}else{
			retiredPK = '${survivingMap.fields[0].personValue}';
			survivingPK = '${retiredMap.fields[0].personValue}';
			text = '确定合并主索引？	原索引：【'+'${survivingMap.fields[3].personValue}'+'】	目标主索引：【'+'${retiredMap.fields[3].personValue}'+'】';
		}
		
		$.messager.confirm('消息 ',text,function(r){
			if (r){
			 	$.ajax({
					url : root + '/index/index.ac?method=merge',
					type : 'POST',
					data : {
						"retiredPk":retiredPK,
						"survivingPk":survivingPK
					},
					success : function(data) {
						showMessage("主索引合并成功");
						window.parent.d_close();
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