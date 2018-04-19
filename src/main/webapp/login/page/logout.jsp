<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<!-- cas退出 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.invalidate();
response.sendRedirect(application.getInitParameter("casServerLogoutUrl")+"?service="+basePath);
%>  
</body>
</html>