<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/merge/js/comp.js"></script>
<title>
</title>
<script type="text/javascript">
	var BASE_DATAS = ${datas};
</script>
</head>
<body>	
<div>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="合并" id="merge_person_btn" onclick="mergePerson();" />
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="返回" onclick="goBackClose();" />
</div>
<br/>
<table class="myTable">
	<caption>数据来源:[<span id="dataFromSpan"></span>]</caption>
	<thead>
	<tr>
		<th>字段名</th>
		<th>目标居民</th>
		<th>被合并居民</th>
	</tr>
	</thead>
	<tbody id="data_show_table">
	</tbody>
</table>
</body>
</html>