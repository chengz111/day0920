<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入JSTL 标签库 -->
<!-- 核心标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3>库存信息列表</h3>
      </div>
      <div class="modal-body">
       	<table class="table">
					<tr>
           				<th>仓库名称</th>
           				<th>库存警戒值</th>
           				<th>库存数量</th>
           				<th>备注信息</th>
           			</tr>
	           		<c:forEach items="${requestScope.stocks}" var="stock">
	                	<tr>
		                    <td>
		                    	<c:forEach items="${requestScope.warehouses }" var="warehouse">
		                    		<c:if test="${stock.warehouse_id == warehouse.warehouse_id}">
		                    			${warehouse.warehouse_name}
		                    		</c:if>
		                    	</c:forEach>
		                    	
		                    </td>
		                    <td>${stock.stock_value}</td>
		                    <td>${stock.stock_number}</td>                
		                    <td>${stock.stock_comments}</td>
	                	</tr>  
                </c:forEach>          
				</table> 
      	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${pageContext.request.contextPath}/web/js/jquery-3.3.1.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${pageContext.request.contextPath}/web/js/bootstrap.min.js"></script>

</body>
</html>