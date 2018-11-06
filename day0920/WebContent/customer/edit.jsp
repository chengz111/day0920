<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRM - 编辑客户</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">
</head>
<body>
	<h1>Edit Customer</h1>
	<form action='${pageContext.request.contextPath }/CustomerServlet?method=${requestScope.customer.id == null ? "add" : "update" }' method="post">
		<input type="hidden" name="id" value="${requestScope.customer.id }" />
		<table class="tab-base">
			<tr>
				<th>姓名:</th>
				<td>
					<input class="text-input" type="text" name="name"  value="${requestScope.customer.name }" />
				</td>
			</tr>
			<tr>
				<th>性别:</th>
				<td>
					<input type="radio" name="gender" value="M" ${requestScope.customer.gender == "M" ? "checked" : "" } />男
					<input type="radio" name="gender" value="F" ${requestScope.customer.gender == "F" ? "checked" : "" } />女
				</td>
			</tr>
			<tr>
				<th>生日:</th>
				<td>
					<input class="text-input" type="text" name="birthday" value="${requestScope.customer.birthday }"  />
				</td>
			</tr>
			<tr>
				<th>邮箱:</th>
				<td>
					<input class="text-input" type="text" name="email" value="${requestScope.customer.email }" />
				</td>
			</tr>
			<tr>
				<th>手机:</th>
				<td>
					<input class="text-input" type="text" name="phone" value="${requestScope.customer.phone }" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="btn-ok" type="submit" value="提交"  />
				</td>
			</tr>
		</table>	
	</form>
</body>
</html>