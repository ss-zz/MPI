<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/query/js/query.js"></script>
<style>
.r{position:fixed; bottom:0; right:20px;padding:10px;}
</style>
<script type="text/javascript">
	function spile_Sub(){
		//被拆分MPI_PK
		var be_Mpi_pk = $("input[type='radio']:checked").val();
		//原主键MPI_PK
		var former_Mpi_pk = '${MPI_PK}';
		if(be_Mpi_pk == null){
			$.messager.alert("提示","请选择拆分项！","Info");
			return;
		}
		if(former_Mpi_pk != null && be_Mpi_pk != null){
			$.ajax({
				url : url = root+'/index/index.ac?method=execSlipt&splitPK='+be_Mpi_pk+'&formerPK='+former_Mpi_pk,
				dataType : 'json',
				type : 'post',
				success : function(data) {
					showMessage("主索引拆分成功");
					window.parent.split_close();
				}
			});
		}
	}
</script>
</head>
<body>
	<!-- 对比情况 所有均显示 -->
	<table id="beSplitTab" class="myTable">
		<caption id="retired">主索引[${splitIndex[0].fields[3].personValue}] &nbsp;&nbsp;&nbsp; <span style="color: red; font-size: 15px;">* 注意：如果存在多条主索引，一次只能拆分一条，单击拆分项按钮选择主索引</span></caption>
		<thead>
			<tr id="thead_th">
				<th></th>
				<th>信息</th>
				<c:forEach begin="0" end="${fn:length(splitIndex)-1}" step="1" varStatus="s">
					<c:if test="${s.index>0 }">
						<th>信息值(<input name="Index" type="radio" value="${splitIndex[s.index].fields[0].personValue}" onclick="radio_Clikc('${splitIndex[s.index].fields[0].personValue}')"/>拆分项)</th>
					</c:if>
					<c:if test="${s.index<1 }">
						<th>信息值</th>
					</c:if>
				</c:forEach>
				
			</tr>
		</thead>
		<tbody id="tab_body">
			<!-- loop -->
			<c:forEach items="${splitIndex[0].fields}" var="field" varStatus="s">
			<tr id="tab_tr">
				<td>${s.count}</td>
				<td>${field.fieldCnName}</td>
				<c:forEach begin="0" end="${fn:length(splitIndex)-1}" varStatus="i">
							<td>${splitIndex[i.index].fields[s.index].personValue}</td>
				</c:forEach>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
	<div class="r">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="spile_Sub()">确认</a>
	</div>
</div>
</body>
</html>