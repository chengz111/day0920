package com.xinyan.mvc.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinyan.mvc.pojo.Customer;
import com.xinyan.mvc.query.CustomerCondition;
import com.xinyan.mvc.service.CustomerService;
import com.xinyan.mvc.service.impl.CustomerServiceImpl;

/**
 * 处理 Customer 的 Servlet
 */
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CustomerService customerService = new CustomerServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding(request.getServletContext().getInitParameter("encoding"));
		
		String method = request.getParameter("method");
		if("list".equals(method)) {
			list(request, response);
		}else if("add".equals(method)) {
			add(request, response);
		}else if("toUpdate".equals(method)) {
			toUpdate(request, response);
		}else if("update".equals(method)) {
			update(request, response);
		}else if("delete".equals(method)) {
			delete(request, response);
		}else if("query".equals(method)) {
			query(request, response);
		}else if("batchDelete".equals(method)) {
			batchDelete(request,response);
		}
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void batchDelete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取id值
		String idsStr = request.getParameter("ids");
		
		//调用业务接口CustomerService
		customerService.batchDeleteCustomer(idsStr);
		
		//重定向到list页面 
		response.sendRedirect(request.getContextPath() + "/CustomerServlet?method=list");
		
	}

	/**
	 * 组合条件查询客户列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void query(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取查询条件参数
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String minBirthday = request.getParameter("minBirthday");
		String maxBirthday = request.getParameter("maxBirthday");
		
		//封装查询对象
		CustomerCondition customerCondition = new CustomerCondition();
		customerCondition.setName(name);
		customerCondition.setGender(gender);
		if(!"".equals(minBirthday)) {
			customerCondition.setMinBirthday(toDate(minBirthday));
		}
		if(!"".equals(maxBirthday)) {
			customerCondition.setMaxBirthday(toDate(maxBirthday));
		}
		
		//调用 业务接口 CustomerService
		List<Customer> customers = customerService.getAllCustomer(customerCondition);
		
		//将 customers 集合放入到 request 请求域中
		request.setAttribute("customers", customers);
		
		//请求转发到 list 页面
		request.getRequestDispatcher("/customer/list.jsp").forward(request, response);
		
	}
	
	/**
	 * 删除客户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取id值
		int id = Integer.parseInt(request.getParameter("id"));
		
		//调用 业务接口 CustomerService
		customerService.deleteCustomer(id);
		
		//重定向到 list 页面
		response.sendRedirect(request.getContextPath() + "/CustomerServlet?method=list");
		
	}
	
	/**
	 * 修改客户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取id值
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		//封装为一个 Customer 对象
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(name);
		customer.setGender(gender);
		customer.setBirthday(toDate(birthday));
		customer.setEmail(email);
		customer.setPhone(phone);
		
		//调用 业务接口 CustomerService
		customerService.updateCustomer(customer);
		
		//重定向到 list 页面
		response.sendRedirect(request.getContextPath() + "/CustomerServlet?method=list");
		
	}
	
	/**
	 * 去修改页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toUpdate(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取id值
		int id = Integer.parseInt(request.getParameter("id"));
		
		//调用 业务接口 CustomerService
		Customer customer = customerService.getCustomerById(id);
		
		//将 customers 集合放入到 request 请求域中
		request.setAttribute("customer", customer);
		
		//转发到 edit 页面
		request.getRequestDispatcher("/customer/edit.jsp").forward(request, response);
		
	}
	
	/**
	 * 添加客户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//添加客户
		//获取表单数据
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		//封装为一个 Customer 对象
		Customer customer = new Customer();
		customer.setName(name);
		customer.setGender(gender);
		customer.setBirthday(toDate(birthday));
		customer.setEmail(email);
		customer.setPhone(phone);
		
		
		//调用业务接口 CustomerService
		customerService.addCustomer(customer);
		
		//重定向到 list 页面，显示添加的数据
		response.sendRedirect(request.getContextPath() + "/CustomerServlet?method=list");
	}
	
	/**
	 * 查询客户列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//调用 业务接口 CustomerService
		List<Customer> customers = customerService.getAllCustomer();
		
		//将 customers 集合放入到 request 请求域中
		request.setAttribute("customers", customers);
		
		//请求转发到 list 页面
		request.getRequestDispatcher("/customer/list.jsp").forward(request, response);
		
	}

	private Date toDate(String str) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
