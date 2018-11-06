<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入JSTL 标签库 -->
<!-- 核心标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物料信息</title>
</head>
<body>
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/web/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
	<h1>高级查询</h1>
	<div class="container">
		 <div class="row">
	        <div class="col-md-6">
				<form  id="queryFrom" action="${pageContext.request.contextPath}/MaterialServlet?method=query" method="post">
					<table  class="table table-bordered table-striped">
						<tr>
							<th>物料编码</th>
							<td>
								<input type="text" class="form-control" name="material_id" value="${param.material_id}" />
							</td>
						</tr>
						
						<tr>
							<th>物料名称</th>
							<td>
								<input type="text" class="form-control" name="material_name" value="${param.material_name}" />
							</td>
						</tr>
						
						
						<tr>
							<th>物料类别</th>
							<td>
								<select class="form-control" id="class" name="material_class">
									<option value="0">--请选择--</option>
									<c:forEach items="${requestScope.categorys}" var="category" >
	                							<option value="${category.category_id }" <c:if test="${category.category_id == param.material_class}">selected="selected" </c:if>
	                							>${category.category_name }</option>
	                				</c:forEach> 
								</select>			
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input id="btn-ok"  type="submit" value="查询" class="btn btn-primary"/> 
								<input id="btn-clear" type="button" value="清除" class="btn btn-success"/> 
							</td>
						</tr>		
					</table>
				</form>
			</div>
		</div>
	</div>
<hr/>
<h1>物料信息列表</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <a href="${pageContext.request.contextPath}/MaterialServlet?method=load" role="button" class="btn btn-primary">添加</a>
            <a href="#" id="update" role="button" class="btn btn-primary">修改</a>
            <a href="#" id="del" role="button" class="btn btn-danger">删除</a>
            <a href="#" id="start" role="button" class="btn btn-success">启动</a>
            <a href="#" id="stop" role="button" class="btn btn-warning">停用</a>
        </div>
    </div>
    <div style="height: 10px"></div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered table-striped">
                <tr>
                	<td align="center">
	                		<input type="checkbox" id="allchecked" />
	                </td>
                    <th>物料编码</th>
                    <th>物料名称</th>
                    <th>物料类别</th>
                    <th>规格</th>
                    <th>单位</th>
                    <th>状态</th>
                    <th>操作</th>    
                </tr>
                <c:forEach items="${requestScope.materials}" var="material">
                	<tr>
	                	<td align="center">
	                		<input type="checkbox" name="material" value="${material.material_id}"/>
	                	</td>
	                    <td>${material.material_id}</td>
	                    <td>${material.material_name}</td>
	                    <td>
	                    	<c:forEach items="${requestScope.categorys}" var="category" >
		                    	<c:if test="${material.category_id == category.category_id  }">
		                    		${category.category_name} 
		                    	</c:if>
	                    	</c:forEach>
	                    </td>                
	                    <td>${material.material_spec}</td>
	                    <td>
	                    	<c:forEach items="${requestScope.units}" var="unit" >
		                    	<c:if test="${material.unit_id == unit.unit_id  }">
		                    		${unit.unit_name} 
		                    	</c:if>
	                    	</c:forEach>
	                    </td>          
	                    <td id="mstate">${material.material_state == 1 ? "使用中" : "已停用"}</td>
	                    <td>
	                        <a href="${pageContext.request.contextPath}/MaterialServlet?method=listStock&id=${material.material_id}" class="btn btn-primary" role="button" data-toggle="modal" data-target="#myModal">查看库存</a>
	                    </td>
                	</tr>  
                </c:forEach>            
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
			</div>
		</div>
	</div>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${pageContext.request.contextPath}/web/js/jquery-3.3.1.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${pageContext.request.contextPath}/web/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function(){
		$("#myModal").on("hidden.bs.modal", function() {
		    $(this).removeData("bs.modal");
		});
		//全选框
		$("#allchecked").click(function() {
			$("[name=material]").prop("checked",this.checked);
		});
		//清空查询条件
		$("#btn-clear").click(function() {
			//清除单行文本
			$(":text").val("");
			//清除下拉框
			$("#class option:selected").prop("selected", false);
			$("#queryFrom").submit();	
		});
		//批量删除
		$("#del").click(function(){
			//定义一个数组
			var array = [];
			var a = 0;
			//获取复选框的物料id
			var $material = $(":checkbox[name=material]:checked");
			if($material != null && $material.length > 0) {
				$material.each(function() {
					if($(this).parent().siblings("#mstate").text() == "使用中") {
						a++;
						alert("选中该物料在使用中无法删除！");
						return false;
					}
				});
				
				if(a == 0) {
					if(confirm("确定要删除吗？")) {
						$material.each(function() {
							//将id的值添加到数组中
							array.push($(this).val());
						})
						$(this).attr("href", "${pageContext.request.contextPath}/MaterialServlet?method=delete&id=" + array.join(","));
						return true;
					}
					
				}
			} else {
				alert("请至少选择一条数据!");
			}
			return false;			
		});
		//单个修改
		$("#update").click(function(){
			var a = 0;
			var $material = $(":checkbox[name=material]:checked");
			
			if($material.length == 1) {
				if($material.parent().siblings("#mstate").text() == "已停用") {
					if(confirm("确定要修改吗？")) {
						$(this).attr("href", "${pageContext.request.contextPath}/MaterialServlet?method=modify&id=" + $material.val());
						return true;
					}
				}else {
					alert("该物料在使用中无法修改！");
				}	
			} else {
				alert("请选择一条数据!");
			}
			return false;			
		});
		//启动
		$("#start").click(function(){
			//定义一个数组
			var array = [];
			//获取复选框的物料id
			var $material = $(":checkbox[name=material]:checked");
			if($material != null && $material.length > 0) {
				if(confirm("确定要启动吗？")) {
					$material.each(function() {
						//将id的值添加到数组中
						array.push($(this).val());
					})
					$(this).attr("href", "${pageContext.request.contextPath}/MaterialServlet?method=start&id=" + array.join(","));
					return true;
				}
				
			} else {
				alert("请至少选择一条数据!");
			}
			return false;			
		});
		
		//停用
		$("#stop").click(function(){
			//定义一个数组
			var array = [];
			//获取复选框的物料id
			var $material = $(":checkbox[name=material]:checked");
			if($material != null && $material.length > 0) {
				if(confirm("确定要停用吗？")) {
					$material.each(function() {
						//将id的值添加到数组中
						array.push($(this).val());
					})
					$(this).attr("href", "${pageContext.request.contextPath}/MaterialServlet?method=stop&id=" + array.join(","));
					return true;
				}
				
			} else {
				alert("请至少选择一条数据!");
			}
			return false;			
		});
		
	})
</script>
</body>
</html>