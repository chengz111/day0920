<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL 标签库 -->
<!-- 核心标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRM - 客户列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
</head>
<body>
	<h1>高级查询</h1>
	<form id="queryForm" action="${pageContext.request.contextPath }/CustomerServlet?method=query" method="post">
		<table class="tab-base">
			<tr>
				<th>姓名:</th>
				<td>
					<input class="text-input" type="text" name="name" value="${param.name }" />
				</td>
			</tr>
			<tr>
				<th>性别:</th>
				<td>
					<select id="gender" name="gender" class="text-input">
						<option value="" >--请选择--</option>
						<option value="M" ${param.gender == "M" ? "selected" : "" }>男</option>
						<option value="F" ${param.gender == "F" ? "selected" : "" }>女</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>生日:</th>
				<td>
					<input class="text-input" type="text" name="minBirthday" value="${param.minBirthday }" /> - 
					<input class="text-input" type="text" name="maxBirthday" value="${param.maxBirthday }" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="btn-ok" type="submit" value="查询"  />
					<input class="btn-clear" type="button" value="清空"  />
				</td>
			</tr>
		</table>
	</form>
	<h1>客户列表</h1>
	<a href="${pageContext.request.contextPath }/customer/edit.jsp">新增客户</a>
	<a id="batchDelete" href="#">批量删除</a>
	<table class="tab-base">
		<tr>
			<th></th>
			<th>姓名</th>
			<th>性别</th>
			<th>生日</th>
			<th>邮箱</th>
			<th>电话</th>
			<th>操作</th>
		</tr>
		<%-- 从 request 请求域中 获取 customers 属性值 --%>
		<c:forEach items="${requestScope.customers }" var="customer">
		<tr>
			<td>
				<input type="checkbox" name="customerNos" value="${customer.id }">
			</td>
			<td>${customer.name }</td>
			<td>${customer.gender == "M" ? "男" : "女"}</td>
			<td>${customer.birthday }</td>
			<td>${customer.email }</td>
			<td>${customer.phone }</td>
			<td>
				<a href="${pageContext.request.contextPath }/CustomerServlet?method=toUpdate&id=${customer.id }">Update</a> | 
				<a class="del" href="${pageContext.request.contextPath }/CustomerServlet?method=delete&id=${customer.id }">Delete</a>
			</td>
		</tr>
		</c:forEach>
	</table>
<script type="text/javascript">
	$(function(){
		//清空查询条件
		$(".btn-clear").click(function(){
			//单行文本
			$(":text").val("");
			//下拉框
			//$("#gender option:first").prop("selected", true);
			$("#gender option:selected").prop("selected", false);
			//提交表单
			$("#queryForm").submit();
		});
		
		//单个删除
		$(".del").click(function(){
			if(confirm("确认删除吗?")){
				return true;
			}
			return false;
		});
		
		//批量删除
		$("#batchDelete").click(function(){
			//定义一个数组
			var ids = [];
			//获取复选框选中的id值
			var $customerNos =  $(":checkbox[name=customerNos]:checked");
			if($customerNos != null && $customerNos.length > 0){
				if(confirm("确认要删除吗?")){
					$customerNos.each(function(){
						var value = $(this).val();
						//将id的值添加到数组中
						ids.push(value);
					});
					//删除
					//[1,2,3] => ids=1,2,3
					$("#batchDelete").attr("href", "${pageContext.request.contextPath }/CustomerServlet?method=batchDelete&ids="+ ids.join(","));
					return true;
				}
			}else{
				alert("请至少选择一项进行操作!");
			}
			
			return false;
		});
	});
</script>
</body>
</html>