package com.xinyan.mvc.service.impl;

import java.util.List;

import com.xinyan.mvc.dao.CustomerDao;
import com.xinyan.mvc.dao.impl.CustomerDaoImpl;
import com.xinyan.mvc.pojo.Customer;
import com.xinyan.mvc.query.CustomerCondition;
import com.xinyan.mvc.service.CustomerService;

/**
 * 客户管理 业务接口实现类
 * @author Administrator
 *
 */
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao = new CustomerDaoImpl();

	/**
	 * 添加 Customer
	 */
	@Override
	public void addCustomer(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerDao.getForList();
	}

	@Override
	public Customer getCustomerById(int id) {
		return customerDao.get(id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public void deleteCustomer(int id) {
		customerDao.delete(id);
	}

	@Override
	public List<Customer> getAllCustomer(CustomerCondition customerCondition) {
		return customerDao.getForList(customerCondition);
	}

	@Override
	public void batchDeleteCustomer(String idsStr) {
		customerDao.batchDelete(idsStr);	
	}

}
