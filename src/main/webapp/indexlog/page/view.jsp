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
</head>
<body>
	<div>
		<input type="hidden" id="log_opType" value="${log.opType }" />
		<input type="hidden" id="log_opStyle" value="${log.opStyle }" />
	</div>
	<!-- 顶头表格 所有类型均有 -->
	<table class="myTable">
		<caption>主索引日志处理详情</caption>
		<tr>
			<td><b>处理类型：</b><span id="show_log_opType"></span></td>
			<td><b>处理方式：</b><span id="show_log_opStyle"></span></td>
			<td><b>综合匹配度：</b>${matchResult.matchDegree }</td>
		</tr>
		<tr>
			<td><b>处理时间：</b>${log.opTime }</td>
			<td><b>信息来源：</b>${log.infoSour }</td>
			<td><b>处理人：</b>${log.opUserId }</td>
		</tr>
	</table>
	<br />
	<c:if test="${log.opType == 2 }">
		<c:if test="${log.opStyle == 3 || log.opStyle == 6 }">
			<h3>
				主索引记录:
				<a href="javascript:Void(0);" onclick="unify_viewIndex('${index.mpiPk }','${index.nameCn }')">${index.nameCn}</a>
			</h3>
			<h3>
				拆分的个人信息记录:
				<a href="javascript:Void(0);" onclick="unify_viewPerson('${person.fieldPk }','${person.nameCn }')">${person.nameCn}</a>
			</h3>
		</c:if>
		<c:if test="${log.opStyle == 1 || log.opStyle == 4 }">
			<h3>
				合并后的主索引记录:
				<a href="javascript:Void(0);" onclick="unify_viewIndex('${index.mpiPk }','${index.nameCn }')">${index.nameCn}</a>
			</h3>
			<h3>
				合并的个人信息记录: 
				<a href="javascript:Void(0);" onclick="unify_viewPerson('${person.fieldPk }','${person.nameCn }')">${person.nameCn}</a>
			</h3>
		</c:if>
		<c:if test="${log.opStyle == 2 || log.opStyle == 5 }">
			<h3>
				新建的主索引记录:
				<a href="javascript:Void(0);" onclick="unify_viewIndex('${index.mpiPk }','${index.nameCn }')">${index.nameCn}</a>
			</h3>
		</c:if>
	</c:if>

	<!-- 对比情况 所有均显示 -->
	<table class="myTable">
		<caption>
			主索引信息对比情况(处理方式:<span id="show_log_opStyle_1"></span>)
			<span style="float: right;">
				<c:if test="${log.opType == 1 }">
				最终形成的主索引:
				<a href="javascript:Void(0);" onclick="unify_viewIndex('${index.mpiPk }','${index.nameCn }')">${index.nameCn}</a>
				</c:if>
			</span>
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
				<tr style="${field.indexValue != field.personValue ? 'color:red;' : ''}">
					<td>${s.count}</td>
					<td>${field.fieldCnName}</td>
					<td>${field.indexValue}</td>
					<td>${field.personValue}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
</body>
</html>