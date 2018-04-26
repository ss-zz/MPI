<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/indexlog/js/view.js"></script>
<title>索引日志明细</title>
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
</style>
</head>
<body>
	<div>
		<input type="hidden" id="log_opType" value="${log.opType }" /> <input
			type="hidden" id="log_opStyle" value="${log.opStyle }" />
	</div>
	<!-- 顶头表格 所有类型均有 -->
	<table class="myTable">
		<tr>
			<td>处理类型:<span id="show_log_opType"></span></td>
			<td>处理方式:<span id="show_log_opStyle"></span></td>
			<td>综合匹配度:${matchResult.matchDegree }</td>
		</tr>
		<tr>
			<td>处理时间:${log.opTime }</td>
			<td>信息来源:${log.infoSour }</td>
			<td>处理人:${log.opUserId }</td>
		</tr>
	</table>
	<hr />
	<c:if test="${log.opType == 2 }">
		<c:if test="${log.opStyle == 3 || log.opStyle == 6 }">
			<h3>
				主索引记录:<a href="javascript:Void(0);"
					onclick="unify_viewIndex('${index.MPI_PK }','${index.NAME_CN }')">${index.NAME_CN
					}</a>
			</h3>
			<h3>
				拆分的个人信息记录:<a href="javascript:Void(0);"
					onclick="unify_viewPerson('${person.FIELD_PK }','${person.NAME_CN }')">${person.NAME_CN
					}</a>
			</h3>
		</c:if>
		<c:if test="${log.opStyle == 1 || log.opStyle == 4 }">
			<h3>
				合并后的主索引记录:<a href="javascript:Void(0);"
					onclick="unify_viewIndex('${index.MPI_PK }','${index.NAME_CN }')">${index.NAME_CN
					}</a>
			</h3>
			<h3>
				合并的个人信息记录:<a href="javascript:Void(0);"
					onclick="unify_viewPerson('${person.FIELD_PK }','${person.NAME_CN }')">${person.NAME_CN
					}</a>
			</h3>
		</c:if>
		<c:if test="${log.opStyle == 2 || log.opStyle == 5 }">
			<h3>
				新建的主索引记录:<a href="javascript:Void(0);"
					onclick="unify_viewIndex('${index.MPI_PK }','${index.NAME_CN }')">${index.NAME_CN
					}</a>
			</h3>
		</c:if>
	</c:if>

	<%-- <c:if test="${matchField!=null && log.opType == 1 }">
		<!-- 匹配度表格 需要判断 -->
		<table class="myTable">
			<caption>匹配度情况(综合匹配度:${matchResult.matchDegree })</caption>
			<thead>
				<tr>
					<th></th>
					<th>匹配项</th>
					<th>匹配度</th>
					<th>信息-匹配的主索引记录</th>
					<th>信息-提交匹配的记录</th>
				</tr>
			</thead>
			<tbody>
				<!-- loop -->
				<c:forEach items="${matchField }" var="field" varStatus="s">
					<tr>
						<td>${s.count}</td>
						<td>${field.fieldCnName}</td>
						<td>${field.matchDegree}</td>
						<td>${field.indexValue}</td>
						<td>${field.personValue}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br />
		<br />
	</c:if> --%>
	<!-- 对比情况 所有均显示 -->
	<table class="myTable">
		<caption>
			主索引信息对比情况(匹配结果:<span id="show_log_opStyle_1"></span>)
		</caption>
		<thead>
			<tr>
				<th></th>
				<th>信息</th>
				<th>匹配的主索引记录</th>
				<th>提交匹配的记录</th>
			</tr>
		</thead>
		<tbody>
			<!-- loop -->
			<c:forEach items="${fullField }" var="field" varStatus="s">
				<tr>
					<td>${s.count}</td>
					<td>${field.fieldCnName}</td>
					<td>${field.indexValue}</td>
					<td>${field.personValue}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
	<br />
	<c:if test="${log.opType == 1 }">
		<h3>
			最终形成的主索引:<a href="javascript:Void(0);"
				onclick="unify_viewIndex('${index.MPI_PK }','${index.NAME_CN }')">${index.NAME_CN
				}</a>
		</h3>
	</c:if>
</body>
</html>