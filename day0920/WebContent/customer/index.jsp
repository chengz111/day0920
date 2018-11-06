<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRM</title>
</head>
<body>
	<h1>CRM</h1>
	<dt>客户管理</dt>
	<dd><a href="${pageContext.request.contextPath }/customer/edit.jsp">添加客户</a></dd>
	<dd><a href="${pageContext.request.contextPath }/CustomerServlet?method=list">客户列表</a></dd>
</body>
</html>