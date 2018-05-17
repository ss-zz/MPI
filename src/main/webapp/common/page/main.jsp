<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/page/master.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据共享与交换平台</title>
<style type="text/css">
.topStyle {
	background-image:
		url("${pageContext.request.contextPath}/images/main/banner.gif");
	background-repeat: repeat;
	padding: 0;
	margin: 0;
	border: 0;
	height: 64px;
}

.topStyle div {
	background-image:
		url("${pageContext.request.contextPath}/images/main/top.gif");
	background-repeat: no-repeat;
	background-position: left;
	padding: 0;
	margin: 0;
	border: 0;
	height: 64px;
}

.myMenu {
	font-family: Georgia, Times, serif;
	font-size: 15px;
	margin: 0;
	padding: 0;
}

.myMenu ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

.myMenu ul li {
	margin: 0;
	padding: 0;
}

.myMenu ul li a {
	display: block;
	text-decoration: none;
	color: #000000;
	background-color: #FFFFFF;
	line-height: 30px;
	padding-left: 20px;
	cursor: pointer;
}

.myMenu ul li a:hover {
	background-color: #eee;
}

.myMenu ul li a strong {
	margin-right: 10px;
}
.accordion .accordion-header{
	background-color: #6494bc;
}
.accordion .accordion-header .panel-title{
	color: #fff;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(".easyui-accordion ul li a")[0].click();
});

</script>
</head>
<!-- 设置了class就可在进入页面加载layout -->
<body class="easyui-layout">
	<!-- 正上方panel -->
	<div region="north" class="topStyle">
		<div style="color: #fff; line-height: 64px; text-align: right; padding-right: 10px; font-size: 14px;">
			<c:out value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"></c:out>，欢迎您
			&nbsp;&nbsp;<a style="color: #fff;" href="javascript: void(0);" onclick="logout()">退出</a>
		</div>
	</div>
	<!-- 正左边panel -->
	<div region="west"
		style="width: 180px; overflow: hidden; margin-top: 1px; border-left: 1px solid;">
		<div class="easyui-accordion" data-options="fit:false,border:false">
		
			<div title="系统管理" data-options="collapsed:false,collapsible:false" class="myMenu">
				<ul>
					<li><a
						onclick="addTab('tabId_id','业务系统维护','${pageContext.request.contextPath}/domain/page/domain.jsp');"
						href="#">业务系统维护</a></li>
				</ul>
			</div>
		
			<div title="主索引管理" data-options="collapsed:false,collapsible:false" class="myMenu">
				<ul>
					<li><a
						onclick="addTab('tabId_qi','主索引记录查询','${pageContext.request.contextPath}/query/page/query.jsp');"
						href="#">主索引查询</a></li>
					<li><a
						onclick="addTab('tabId_me','主索引记录合并','${pageContext.request.contextPath}/merge/page/merge.jsp');"
						href="#">主索引人工合并</a></li>
					<%-- <li><a
						onclick="addTab('tabId_field','主索引字段管理','${pageContext.request.contextPath}/cfg/page/field.jsp');"
						href="#">主索引字段管理</a></li> --%>
					<li><a
						onclick="addTab('tabId_bc','主索引初筛规则管理','${pageContext.request.contextPath}/cfg/page/block.jsp');"
						href="#">主索引初筛规则管理</a></li>
					<li><a
						onclick="addTab('tabId_mc','主索引匹配规则管理','${pageContext.request.contextPath}/cfg/page/match.jsp');"
						href="#">主索引匹配规则管理</a></li>
				</ul>
			</div>
			
			<div title="业务管理" data-options="collapsed:false,collapsible:false" class="myMenu">
				<ul>
					<%-- <li><a
						onclick="addTab('tabId_tyzdgl','通用字段配置','${pageContext.request.contextPath}/config/page/common_field_config.jsp');"
						href="#">通用字段配置</a></li>
					<li><a
						onclick="addTab('tabId_biz_config','业务配置','${pageContext.request.contextPath}/config/page/biz_config.jsp');"
						href="#">业务配置</a></li> --%>
					<li><a
						onclick="addTab('tabId_qi_biz','业务查询','${pageContext.request.contextPath}/biz/page/query.jsp');"
						href="#">业务查询</a></li>
					<li><a
						onclick="addTab('tabId_bc_biz','业务初筛规则管理','${pageContext.request.contextPath}/biz/page/block.jsp');"
						href="#">业务初筛规则管理</a></li>
					<li><a
						onclick="addTab('tabId_mc_biz','业务匹配规则管理','${pageContext.request.contextPath}/biz/page/match.jsp');"
						href="#">业务匹配规则管理</a></li>
				</ul>
			</div>
			
			<div title="日志管理" data-options="collapsed:false,collapsible:false" class="myMenu">
				<ul>
					<%-- <li><a
						onclick="addTab('tabId_ma','人工审核记录','${pageContext.request.contextPath}/manual/page/add.jsp');"
						href="#">人工审核记录</a></li> --%>
					<li><a
						onclick="addTab('tabId_il','主索引处理日志','${pageContext.request.contextPath}/indexlog/page/il.jsp');"
						href="#">主索引处理日志</a></li>
					<li><a
						onclick="addTab('tabId_il','主索引处理日志','${pageContext.request.contextPath}/indexlog/page/il.jsp');"
						href="#">业务处理日志</a></li>
					<%-- <li><a
						onclick="addTab('tabId_pl','居民处理日志','${pageContext.request.contextPath}/personlog/page/pl.jsp');"
						href="#">居民处理日志</a></li> --%>
				</ul>
			</div>
			
			<div title="权限管理" data-options="collapsed:false,collapsible:false" class="myMenu">
				<ul>
				 	<li><a
						onclick="addTab('tabId_su','用户管理','${pageContext.request.contextPath}/sysuser/su.ac');"
						href="#">用户管理</a></li>
					<li><a
						onclick="addTab('tabId_sr','角色管理','${pageContext.request.contextPath}/sysrole/page/role.jsp');"
						href="#">角色管理</a></li> 
				</ul>
			</div>
		</div>
	</div>
	<!-- 正中间panel -->
	<div region="center">
		<div class="easyui-tabs" id="centerTab" fit="true" border="false" toolPosition="right">
		</div>
	</div>
</body>
</html>