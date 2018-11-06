package com.xinyan.mms.material.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinyan.mms.material.pojo.Category;
import com.xinyan.mms.material.pojo.Material;
import com.xinyan.mms.material.pojo.Stock;
import com.xinyan.mms.material.pojo.Unit;
import com.xinyan.mms.material.pojo.Warehouse;
import com.xinyan.mms.material.query.MaterialCondition;
import com.xinyan.mms.material.service.MaterialService;
import com.xinyan.mms.material.service.impl.MaterialServiceImpl;

/**
 * 处理 material 的 Servlet
 */
public class MaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MaterialService materialService = new MaterialServiceImpl();

	/**
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if ("list".equals(method)) {
			list(request, response);

		} else if ("load".equals(method)) {
			load(request, response);

		} else if ("add".equals(method)) {
			add(request, response);

		} else if ("delete".equals(method)) {
			delete(request, response);

		} else if ("modify".equals(method)) {
			modify(request, response);

		} else if ("query".equals(method)) {
			query(request, response);

		} else if ("start".equals(method)) {
			start(request, response);

		} else if ("stop".equals(method)) {
			stop(request, response);

		} else if ("listStock".equals(method)) {
			listStock(request, response);
			
		} else if ("update".equals(method)) {
			update(request, response);
			
		} else if("queryid".equals(method)) {
			queryid(request, response);
		}
		
	}
	/**
	 * 根据物料编码查询物料信息
	 * @param request
	 * @param response
	 */
	private void queryid(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取提交查询的数据
		String material_id = request.getParameter("id").trim().toUpperCase();
		//调用接口业务materialService进行查询
		Material materials = materialService.getMaterial(material_id);	
		if(materials != null) {
			//响应到list页面
			response.getWriter().write('1');
			
		}
	}
	
	
	/**
	 * 修改物料和仓库信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取表单物料信息数据
		String mid = request.getParameter("mid").toUpperCase();
		String mname = request.getParameter("mname");
		int category_id = Integer.parseInt(request.getParameter("category_id"));  
		String mspec = request.getParameter("mspec").toUpperCase();
		int unit_id = Integer.parseInt(request.getParameter("unit_id"));
		String mnotes = request.getParameter("mnotes");
		int mstate =Integer.parseInt(request.getParameter("mstate"));
		//封装成一个material对象
		Material material = new Material(mid, category_id, unit_id, mname, mspec, mnotes, mstate);
		
		List<Stock> stocks = new ArrayList<>();
		
		//获取表单仓库数据
		Stock stock1 = new Stock();
		int sname1 = Integer.parseInt(request.getParameter("sname1"));
		if(request.getParameter("svalue1").equals("") || request.getParameter("svalue1") == null ) {
			int svalue1 = 0;
			stock1.setStock_value(svalue1);
			
		}else {
			int svalue1 = Integer.parseInt(request.getParameter("svalue1"));
			stock1.setStock_value(svalue1);
		}
		if(request.getParameter("snumber1").equals("") || request.getParameter("snumber1") == null ) {
			int snumber1 = 0;
			stock1.setStock_number(snumber1);
			
		}else {
			int snumber1 = Integer.parseInt(request.getParameter("snumber1"));
			stock1.setStock_number(snumber1);
		}
		
		
		String scomments1 = request.getParameter("scomments1");
		stock1.setMaterial_id(mid);
		stock1.setWarehouse_id(sname1);
		stock1.setStock_comments(scomments1);
		stocks.add(stock1);
			
		
		Stock stock2 = new Stock();
		int sname2 = Integer.parseInt(request.getParameter("sname2"));
		if(request.getParameter("svalue2").equals("") || request.getParameter("svalue2") == null ) {
			int svalue2 = 0;
			stock2.setStock_value(svalue2);
			
		}else {
			int svalue2 = Integer.parseInt(request.getParameter("svalue2"));
			stock2.setStock_value(svalue2);
		}
		if(request.getParameter("snumber2").equals("") || request.getParameter("snumber2") == null ) {
			int snumber2 = 0;
			stock2.setStock_number(snumber2);
			
		}else {
			int snumber2 = Integer.parseInt(request.getParameter("snumber2"));
			stock2.setStock_number(snumber2);
		}
		String scomments2 = request.getParameter("scomments2");
		stock2.setMaterial_id(mid);
		stock2.setWarehouse_id(sname2);
		stock2.setStock_comments(scomments2);
		stocks.add(stock2);
		
		Stock stock3 = new Stock();
		int sname3 = Integer.parseInt(request.getParameter("sname3"));
		if(request.getParameter("svalue3").equals("") || request.getParameter("svalue3") == null ) {
			int svalue3 = 0;
			stock3.setStock_value(svalue3);
			
		}else {
			int svalue3 = Integer.parseInt(request.getParameter("svalue3"));
			stock3.setStock_value(svalue3);
		}
		if(request.getParameter("snumber3").equals("") || request.getParameter("snumber3") == null ) {
			int snumber3 = 0;
			stock3.setStock_number(snumber3);
			
		}else {
			int snumber3 = Integer.parseInt(request.getParameter("snumber3"));
			stock3.setStock_number(snumber3);
		}
		String scomments3 = request.getParameter("scomments3");
		stock3.setMaterial_id(mid);
		stock3.setWarehouse_id(sname3);
		stock3.setStock_comments(scomments3);
		stocks.add(stock3);
		
		//调用业务接口MaterialService
		materialService.updateMaterial(material,stocks);
		//重定向到list页面
		response.sendRedirect(request.getContextPath()+"/MaterialServlet?method=list");
		
		
	}
	/**
	 * 库存信息查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listStock(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//查询字典表
		// 调用业务接口MaterialService
		List<Object> dictionaryItems = materialService.getDictionaryItem();
		List<Warehouse> warehouses = (List<Warehouse>) dictionaryItems.get(2);
		//将materials集合放到request请求域中
		request.setAttribute("warehouses", warehouses);
		//获取表单的物料编码
		String id = request.getParameter("id");
		//调用materialService的接口业务查询库存
		List<Stock> stocks = materialService.getStock(id);
		//添加到request请求域中
		request.setAttribute("stocks", stocks);
		
		
		//请求转发到stocklist.jsp页面
		request.getRequestDispatcher("/material/stocklist.jsp").forward(request, response);	
		
	}

	/**
	 * 停用
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void stop(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取表单数据
		String idstr = request.getParameter("id");
		//调用业务接口materialService
		materialService.getStop(idstr);
		//重定向到list页面
		response.sendRedirect(request.getContextPath()+"/MaterialServlet?method=list");
	}

	/**
	 * 启用
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void start(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取表单数据
		String idstr = request.getParameter("id");
		//调用业务接口materialService
		materialService.getStart(idstr);
		//重定向到list页面
		response.sendRedirect(request.getContextPath()+"/MaterialServlet?method=list");
		

	}

	/**
	 * 组合查询物料信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void query(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取提交查询的数据
		String material_id = request.getParameter("material_id").trim().toUpperCase();
		String material_name = request.getParameter("material_name").trim();
		int material_class = Integer.parseInt(request.getParameter("material_class"));
		//封装到MaterialCondition对象中
		MaterialCondition materialCondition = new MaterialCondition();
		materialCondition.setId(material_id);
		materialCondition.setName(material_name);
		materialCondition.setMcategory_id(material_class);
		
		
		//调用接口业务materialService进行查询
		List<Material> materials = materialService.getMaterialCondition(materialCondition);	
		//将数据放到request请求域中
		request.setAttribute("materials", materials);
		
		// 调用业务接口MaterialService
		List<Object> dictionaryItems = materialService.getDictionaryItem();
		
		List<Category> categorys = (List<Category>) dictionaryItems.get(0);
		//将materials集合放到request请求域中
		request.setAttribute("categorys", categorys);
		
		List<Unit> units = (List<Unit>) dictionaryItems.get(1);
		//将materials集合放到request请求域中
		request.setAttribute("units", units);
		
		//请求转发到list页面
		request.getRequestDispatcher("/material/list.jsp").forward(request, response);
		
		

	}

	/**
	 * 修改物料信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modify(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//查询字典表显示下拉框
		// 调用业务接口MaterialService
		List<Object> dictionaryItems = materialService.getDictionaryItem();
		
		List<Category> categorys = (List<Category>) dictionaryItems.get(0);
		//将materials集合放到request请求域中
		request.setAttribute("categorys", categorys);
		
		List<Unit> units = (List<Unit>) dictionaryItems.get(1);
		//将materials集合放到request请求域中
		request.setAttribute("units", units);
		
		List<Warehouse> warehouses = (List<Warehouse>) dictionaryItems.get(2);
		//将materials集合放到request请求域中
		Warehouse warehouse1 = warehouses.get(0);
		Warehouse warehouse2 = warehouses.get(1);
		Warehouse warehouse3 = warehouses.get(2);
		
		request.setAttribute("warehouse1", warehouse1);
		request.setAttribute("warehouse2", warehouse2);
		request.setAttribute("warehouse3", warehouse3);
				
		//获取id
		String id = request.getParameter("id");
		//调用materialServlet接口返回数据
		List<Object> lists = materialService.getMaterials(id);
		if(lists != null && lists.size() > 0) {
			Material material = (Material)lists.get(0);
			//将Material stock添加到request请求域中
			request.setAttribute("material", material);
			List<Stock> stocks = (List<Stock>)lists.get(1);
			
			//取出list集合的stock的对象
			for(int i = 0; i < stocks.size(); i++) {
				Stock stock = stocks.get(i);
				if(stock.getWarehouse_id() == 1) {
					request.setAttribute("stocks1", stock);
					
				} else if (stock.getWarehouse_id() == 2) {
					request.setAttribute("stocks2", stock);
					
				} else if (stock.getWarehouse_id() == 3) {
					request.setAttribute("stocks3", stock);
				}
			}
			//转发到edit页面
			request.getRequestDispatcher("material/edit.jsp").forward(request, response);			
		}else {
			//重定向到list方法
			response.sendRedirect(request.getContextPath()+ "/MaterialServlet?method=list");
		}
		
		
	}

	/**
	 * 删除物料信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取id值
		String ids = request.getParameter("id");
		//调用materialServlet接口业务
		materialService.deleteMaterial(ids);
		//重定向到list方法
		response.sendRedirect(request.getContextPath()+ "/MaterialServlet?method=list");

	}

	/**
	 * 添加物料信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//获取表单物料信息数据
		String mid = request.getParameter("mid").toUpperCase();
		String mname = request.getParameter("mname");
		int category_id = Integer.parseInt(request.getParameter("category_id"));  
		String mspec = request.getParameter("mspec").toUpperCase();
		int unit_id = Integer.parseInt(request.getParameter("unit_id"));
		String mnotes = request.getParameter("mnotes");
		int mstate =Integer.parseInt(request.getParameter("mstate"));
		//封装成一个material对象
		Material material = new Material(mid, category_id, unit_id, mname, mspec, mnotes, mstate);
		
		List<Stock> stocks = new ArrayList<>();
		
		//获取表单仓库数据
		Stock stock1 = new Stock();
		int sname1 = Integer.parseInt(request.getParameter("sname1"));
		if(request.getParameter("svalue1").equals("") || request.getParameter("svalue1") == null ) {
			int svalue1 = 0;
			stock1.setStock_value(svalue1);
			
		}else {
			int svalue1 = Integer.parseInt(request.getParameter("svalue1"));
			stock1.setStock_value(svalue1);
		}
		if(request.getParameter("snumber1").equals("") || request.getParameter("snumber1") == null ) {
			int snumber1 = 0;
			stock1.setStock_number(snumber1);
			
		}else {
			int snumber1 = Integer.parseInt(request.getParameter("snumber1"));
			stock1.setStock_number(snumber1);
		}
		
		
		String scomments1 = request.getParameter("scomments1");
		stock1.setMaterial_id(mid);
		stock1.setWarehouse_id(sname1);
		stock1.setStock_comments(scomments1);
		stocks.add(stock1);
			
		
		Stock stock2 = new Stock();
		int sname2 = Integer.parseInt(request.getParameter("sname2"));
		if(request.getParameter("svalue2").equals("") || request.getParameter("svalue2") == null ) {
			int svalue2 = 0;
			stock2.setStock_value(svalue2);
			
		}else {
			int svalue2 = Integer.parseInt(request.getParameter("svalue2"));
			stock2.setStock_value(svalue2);
		}
		if(request.getParameter("snumber2").equals("") || request.getParameter("snumber2") == null ) {
			int snumber2 = 0;
			stock2.setStock_number(snumber2);
			
		}else {
			int snumber2 = Integer.parseInt(request.getParameter("snumber2"));
			stock2.setStock_number(snumber2);
		}
		String scomments2 = request.getParameter("scomments2");
		stock2.setMaterial_id(mid);
		stock2.setWarehouse_id(sname2);
		stock2.setStock_comments(scomments2);
		stocks.add(stock2);
		
		Stock stock3 = new Stock();
		int sname3 = Integer.parseInt(request.getParameter("sname3"));
		if(request.getParameter("svalue3").equals("") || request.getParameter("svalue3") == null ) {
			int svalue3 = 0;
			stock3.setStock_value(svalue3);
			
		}else {
			int svalue3 = Integer.parseInt(request.getParameter("svalue3"));
			stock3.setStock_value(svalue3);
		}
		if(request.getParameter("snumber3").equals("") || request.getParameter("snumber3") == null ) {
			int snumber3 = 0;
			stock3.setStock_number(snumber3);
			
		}else {
			int snumber3 = Integer.parseInt(request.getParameter("snumber3"));
			stock3.setStock_number(snumber3);
		}
		String scomments3 = request.getParameter("scomments3");
		stock3.setMaterial_id(mid);
		stock3.setWarehouse_id(sname3);
		stock3.setStock_comments(scomments3);
		stocks.add(stock3);
		
		
		//调用业务接口MaterialService
		materialService.addMaterial(material,stocks);
		
		//重定向到list页面
		response.sendRedirect(request.getContextPath()+"/MaterialServlet?method=list");
	}

	/**
	 * 加载添加页面信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void load(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 调用业务接口MaterialService
		List<Object> dictionaryItems = materialService.getDictionaryItem();
		
		List<Category> categorys = (List<Category>) dictionaryItems.get(0);
		//将materials集合放到request请求域中
		request.setAttribute("categorys", categorys);
		
		List<Unit> units = (List<Unit>) dictionaryItems.get(1);
		//将materials集合放到request请求域中
		request.setAttribute("units", units);
		
		List<Warehouse> warehouses = (List<Warehouse>) dictionaryItems.get(2);
		//将materials集合放到request请求域中
		Warehouse warehouse1 = warehouses.get(0);
		Warehouse warehouse2 = warehouses.get(1);
		Warehouse warehouse3 = warehouses.get(2);
		
		request.setAttribute("warehouse1", warehouse1);
		request.setAttribute("warehouse2", warehouse2);
		request.setAttribute("warehouse3", warehouse3);
		
		//请求转发
		request.getRequestDispatcher("/material/edit.jsp").forward(request, response);
		
 
	}
	
	/**
	 * 查询物料信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//调用业务接口MaterialService
		List<Material> materials = materialService.getAllMaterial();
		//将materials集合放到request请求域中
		request.setAttribute("materials", materials);
		
		// 调用业务接口MaterialService
		List<Object> dictionaryItems = materialService.getDictionaryItem();
		
		List<Category> categorys = (List<Category>) dictionaryItems.get(0);
		//将materials集合放到request请求域中
		request.setAttribute("categorys", categorys);
		
		List<Unit> units = (List<Unit>) dictionaryItems.get(1);
		//将materials集合放到request请求域中
		request.setAttribute("units", units);
		//请求转发到list页面
		request.getRequestDispatcher("/material/list.jsp").forward(request, response);
	}

}
