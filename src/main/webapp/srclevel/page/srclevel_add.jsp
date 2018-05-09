<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/srclevel/js/srclevel_add.js"></script>
<script type="text/javascript">
	var SELECT_JSON = ${selectJson};
</script>
</head>
<body>
		<div style="padding: 5px; width:80%; height: 300px;"  id="table_add"class="ToolTip_Form" id="table_edit" onkeydown="if(event.keyCode==13){editData();}">
        <input type="button" value="重置" id="resetBtn" onclick="resetAllData()" />&nbsp;&nbsp;
				<input type="button" value="保存" id="saveBtn" onclick="saveMatchCfg()" />&nbsp;&nbsp;
				<input type="button" value="返回" id="gobackBtn" onclick="goBackClose()" /><br/><br/>	
        <ul>
			<li>
				<label for="add_domainSelect">域唯一标识:</label><select id="add_domainSelect"></select>
			    <label for="add_fieldSelect">字段名称:</label><select id="add_fieldSelect"></select>
				<label>字段数据源级别：</label>
				<select id="add_fieldlevel" length="50">
				    <option value="">--请选择--</option>
					<option value="0">0级</option>
					<option value="1">1级</option>
					<option value="2">2级</option>
					<option value="3">3级</option>
					<option value="4">4级</option>
					<option value="5">5级</option>
				</select>
			</li>		
		</ul>
	</div>
</body>
</html>
