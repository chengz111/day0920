package com.xinyan.mvc.dao;

import java.util.List;

import com.xinyan.mvc.pojo.Customer;
import com.xinyan.mvc.query.CustomerCondition;

/**
 * 客户管理 数据操作DAO接口
 * @author Administrator
 *
 */
public interface CustomerDao {

	/**
	 * 保存
	 * @param customer
	 */
	public void save(Customer customer);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 修改
	 * @param customer
	 */
	public void update(Customer customer);
	
	/**
	 * 查询列表
	 * @return
	 */
	public List<Customer> getForList();
	
	/**
	 * 条件查询
	 * @param customerCondition
	 * @return
	 */
	public List<Customer> getForList(CustomerCondition customerCondition);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Customer get(int id);
	
	/**
	 * 批量删除
	 * @param idsStr
	 */
	public void batchDelete(String idsStr);
}