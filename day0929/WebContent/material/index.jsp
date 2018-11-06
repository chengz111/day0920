<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SSM</title>
</head>
<body>
	<h1>SSM</h1>
	<dl>
		<dt>物料管理系统</dt>
		<dd><a href="${pageContext.request.contextPath}/MaterialServlet?method=load">添加物料</a><dd>
		<dd><a href="${pageContext.request.contextPath}/MaterialServlet?method=list">物料信息列表</a><dd>	
	</dl>
</body>
</html>