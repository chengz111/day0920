package com.xinyan.mvc.service;

import java.util.List;

import com.xinyan.mvc.pojo.Customer;
import com.xinyan.mvc.query.CustomerCondition;

/**
 * 客户管理 业务接口
 * @author Administrator
 *
 */
public interface CustomerService {

	/**
	 * 添加 Customer
	 * @param customer
	 */
	public void addCustomer(Customer customer);
	
	/**
	 * 删除客户
	 * @param id
	 */
	public void deleteCustomer(int id);
	
	/**
	 * 查询客户列表
	 * @return
	 */
	public List<Customer> getAllCustomer();
	
	/**
	 * 组合条件查询客户列表
	 * @param customerCondition
	 * @return
	 */
	public List<Customer> getAllCustomer(CustomerCondition customerCondition);
	
	/**
	 * 根据id查询客户
	 * @param id
	 * @return
	 */
	public Customer getCustomerById(int id);
	
	/**
	 * 修改客户
	 * @param customer
	 */
	public void updateCustomer(Customer customer);

	/**
	 * 批量删除客户
	 * @param idsStr
	 */
	public void batchDeleteCustomer(String idsStr);
}
