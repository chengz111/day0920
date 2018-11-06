<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入JSTL 标签库 -->
<!-- 核心标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/web/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/web/css/bootstrapValidator.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/web/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/web/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/web/js/bootstrapValidator.js"></script>
<title>添加物料信息</title>
</head>
<body>
	
	 <div class="container">
        <div class="row">
            <!-- form: -->
            <section>
                <div class="col-lg-8 col-lg-offset-2">
                    <div class="page-header">
                        <h2>物料信息</h2>
                    </div>

                    <form id="defaultForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath }/MaterialServlet?method=${requestScope.material.material_id == null ? 'add' : 'update'}">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">物料编码</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" id="mid" name="mid" placeholder="物料编码" value="${requestScope.material.material_id}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">物料名称</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" name="mname"  value="${requestScope.material.material_name}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">物料类别</label>
                            <div class="col-lg-5">
                                <select class="form-control" id="class" name="category_id">
                                		<option value="">--请选择--</option>
	                                	<c:forEach items="${requestScope.categorys}" var="category" >
	                							<option value="${category.category_id }" <c:if test="${category.category_id == requestScope.material.category_id}">selected="selected" </c:if>
	                							 >${category.category_name }</option>
	                					</c:forEach> 
                					
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">规格</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" name="mspec" value="${requestScope.material.material_spec}" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">单位</label>
                            <div class="col-lg-5">
                                <select class="form-control" id="unit" name="unit_id">
                                	<option value="">--请选择--</option>
                                	<c:forEach items="${requestScope.units}" var="unit" >
	                							<option value="${unit.unit_id }" <c:if test="${unit.unit_id == requestScope.material.unit_id}">selected="selected" </c:if>
	                							>${unit.unit_name }</option>
	                				</c:forEach> 
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">备注</label>
                            <div class="col-lg-5">
                            	<input type="text" class="form-control" name="mnotes" value="${requestScope.material.material_notes}"/>
                            	<%-- <input type="text" name="mstate" value="${requestScope.material.material_id == null ? 1  :  requestScope.material.material_state }"/> --%>
                            </div>
                        </div>
						
						<div class="form-group">
                            <label class="col-lg-3 control-label">物料状态</label>
                            <div class="col-lg-5">
                                <select class="form-control" id="state" name="mstate">
                                	<option value="1" <c:if test="${requestScope.material.material_state == 1}">selected="selected" </c:if>
                                	>使用中</option>
                                	<option value="0" <c:if test="${requestScope.material.material_state == 0}">selected="selected" </c:if>
                                	>已停用</option>
                                </select>
                            </div>
                        </div>
						
                        <div class="form-group">          
                        	 	<h3>库存信息</h3>      
                        </div>

                        <div class="form-group">
							<table class="table">
									<tr>
                          				<th>仓库名称</th>
                          				<th>库存警戒值</th>
                          				<th>库存数量</th>
                          				<th>备注信息</th>
                          			</tr>
                          			
                          			<tr>
                          				<td >
                          					<input type="hidden" class="form-control" name="sname1" value="${requestScope.warehouse1.warehouse_id}"/>${requestScope.warehouse1.warehouse_name}
                          				</td>
                          				<td>
                          					<input  type="text" class="form-control" name="svalue1"  value="${requestScope.stocks1.stock_value }"/>
                          				</td>
                          				<td>
                          					<input  type="text" class="form-control" name="snumber1"  value="${requestScope.stocks1.stock_number }"/>
                          				</td>
                          				<td>
                          					<input type="text" class="form-control" name="scomments1" value="${requestScope.stocks1.stock_comments }"/>
                          				</td>
                          			</tr>
                          			<tr >
                          				<td>
                          					<input type="hidden" class="form-control" name="sname2" value="${requestScope.warehouse2.warehouse_id}"/>${requestScope.warehouse2.warehouse_name}
                          				</td>
                          				<td>
                          					<input type="text" class="form-control" name="svalue2" value="${requestScope.stocks2.stock_value }" />
                          				</td>
                          				<td>
                          					<input type="text" class="form-control" name="snumber2" value="${requestScope.stocks2.stock_number }"/>
                          				</td>
                          				<td>
                          					<input type="text" class="form-control" name="scomments2" value="${requestScope.stocks2.stock_comments }"/>
                          				</td>
                          			</tr>
                          			<tr >
                          				<td>
                          					<input type="hidden" class="form-control" name="sname3" value="${requestScope.warehouse3.warehouse_id}"/>${requestScope.warehouse3.warehouse_name}
                          				</td>
                          				<td>
                          					<input pl type="text" class="form-control" name="svalue3" value="${requestScope.stocks3.stock_value }" />
                          				</td>
                          				<td>
                          					<input type="text" class="form-control" name="snumber3" value="${requestScope.stocks3.stock_number }"/>
                          				</td>
                          				<td>
                          					<input type="text" class="form-control" name="scomments3" value="${requestScope.stocks3.stock_comments }"/>
                          				</td>
                          			</tr>
						    </table>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-6 col-lg-offset-6">
                                <button type="submit" id="sub" class="btn btn-primary" value="提交">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
            <!-- :form -->
        </div>
    </div>
<script type="text/javascript">
	$(function(){
		if("${requestScope.material.material_id }" != "") {
			$("#mid").prop("readonly", "readonly");
	
		}else {
			 $("#mid").blur(function(){
				   $.ajax({
						//请求的url地址
						url : "${pageContext.request.contextPath }/MaterialServlet?method=queryid",
						//请求方法， 不指定默认是GET
						//type : "POST",
						//发送的请求数据
						data : {id : $("#mid").val(), time : new Date()},
						//返回数据格式
						dataType : "json",
						//回调函数
						//回调函数里面的参数data来绑定请求响应的数据
						success : function(data){
							console.log(data);
							if(data == '1'){
								 alert("此物料编码已存在");
								 /* setTimeout(function(){ */
									 $("#mid").focus();							 
								 /* },1) ; */
							}
							
						}
					})
			   }) 
		}
  		 $('#defaultForm').bootstrapValidator({
//	        live: 'disabled',
			//submitButtons: '#submit', 
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },	        
	        fields: {
	        	mid: {
	                validators: {
	                    notEmpty: {
	                        message: '物料编码不能为空'
	                    }
	                }
	            },
				 mname: {
	                validators: {
	                	notEmpty: {
	                        message: '物料名称不能为空'
	                    }
	                }
	            },
	            category_id: {
	                validators: {
	                    notEmpty: {
	                        message: '物料类别不能为空'
	                    }
                     
	                }
	            },
	        
	            mspec: {
	                validators: {
	                	 notEmpty: {
		                        message: '规格不能为空'
		                    }
	                }
	            },
	            unit_id: {
	            	validators: {
	                    notEmpty: {
	                        message: '单位不能为空'
	                    }
                      
	                }
	            },
	            mnotes: {
	                validators: {
	                	notEmpty: {
	                    	message: '备注不能为空'
	                    }
	                }
	            }/*,
	             svalue1: {
	                validators: {
	                	notEmpty: {
	                    	message: '此字段必填'
	                    }
	                }
	            },
	            snumber1: {
	                validators: {
	                	notEmpty: {
	                    	message: '此字段必填'
	                    }
	                }
	            },
	            scomments1: {
	                validators: {
	                	notEmpty: {
	                    	message: '此字段必填'
	                    }
	                }
	            },
	            svalue2: {
	                validators: {
	                	notEmpty: {
	                    	message: '此字段必填'
	                    }
	                }
	            },
	            snumber2: {
	                validators: {
	                	notEmpty: {
	                    	message: '此字段必填'
	                    }
	                }
	            },
	            scomments2: {
	                validators: {
	                	notEmpty: {
	                    	message: '此字段必填'
	                    }
	                }
	            },
	            svalue3: {
	                validators: {
	                	notEmpty: {
	                    	message: '此字段必填'
	                    }
	                }
	            },
	            snumber3: {
	                validators: {
	                	notEmpty: {
	                    	message: '此字段必填'
	                    }
	                }
	            },
	            scomments3: {
	                validators: {
	                	notEmpty: {
	                    	message: '此字段必填'
	                    }
	                }
	            }*/
	            
	        } 
	    }) 
	})
</script>	
</body>
</html>