package com.xinyan.mvc.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xinyan.javaweb.util.JdbcUtils;
import com.xinyan.mvc.dao.CustomerDao;
import com.xinyan.mvc.pojo.Customer;
import com.xinyan.mvc.query.CustomerCondition;

/**
 * 客户管理 数据操作DAO接口实现类
 */
public class CustomerDaoImpl implements CustomerDao {
	
	private QueryRunner qr = new QueryRunner();

	/**
	 * 保存 Customer
	 */
	@Override
	public void save(Customer customer) {
		Connection conn = null;
		
		try {
			conn = JdbcUtils.getConnection();
			
			String sql = "insert into customers (name, gender, birthday, email, phone) "
					+ "values (?,?,?,?,?)";
			qr.update(conn, sql, customer.getName(), customer.getGender(), customer.getBirthday(), 
					customer.getEmail(), customer.getPhone());			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn);
		}
	}

	/**
	 * 查询列表
	 */
	@Override
	public List<Customer> getForList() {
		Connection conn = null;
		
		try {
			conn = JdbcUtils.getConnection();
			
			String sql = "select id, name, gender, birthday, email, phone from customers";
			
			return qr.query(conn, sql, new BeanListHandler<>(Customer.class));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn);
		}
		return null;
	}

	/**
	 * 根据id查询
	 */
	@Override
	public Customer get(int id) {
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select id, name, gender, birthday, email, phone from customers where id = ?";
			
			return qr.query(conn, sql, new BeanHandler<>(Customer.class), id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn);
		}
		return null;
	}

	/**
	 * 修改客户
	 */
	@Override
	public void update(Customer customer) {
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update customers set name = ?, gender = ?, birthday = ?, email = ?, "
					+ "phone = ? where id = ?";
			
			qr.update(conn, sql, customer.getName(), customer.getGender(), 
					customer.getBirthday(), customer.getEmail(), customer.getPhone(),
					customer.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn);
		}
	}
	
	/**
	 * 删除
	 */
	@Override
	public void delete(int id) {
		Connection conn = null;
		
		try {
			conn = JdbcUtils.getConnection();
			
			String sql = "delete from customers where id = ?";
			qr.update(conn, sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn);
		}
	}

	/**
	 * 条件查询
	 */
	@Override
	public List<Customer> getForList(CustomerCondition customerCondition) {
		Connection conn = null;
		
		try {
			conn = JdbcUtils.getConnection();
			
			List<Object> args = new ArrayList<>();
			StringBuffer sql = new StringBuffer("select id, name, gender, birthday, email, phone from customers where 1 = 1");
			if(customerCondition != null) {
				//name 模糊查询
				if(customerCondition.getName() != null && !"".equals(customerCondition.getName())) {
					sql.append(" and name like ?");
					args.add("%" + customerCondition.getName() + "%");
				}
				if(customerCondition.getGender() != null && !"".equals(customerCondition.getGender())) {
					sql.append(" and gender = ?");
					args.add(customerCondition.getGender());
				}
				if(customerCondition.getMinBirthday() != null) {
					sql.append(" and birthday >= ?");
					args.add(customerCondition.getMinBirthday());
				}
				if(customerCondition.getMaxBirthday() != null) {
					sql.append(" and birthday <= ?");
					args.add(customerCondition.getMaxBirthday());
				}
			}
			
			return qr.query(conn, sql.toString(), new BeanListHandler<>(Customer.class), args.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn);
		}
		return null;
	}
	
	/**
	 * 批量删除
	 */
	@Override
	public void batchDelete(String idsStr) {
		Connection conn = null;
		
		try {
			conn = JdbcUtils.getConnection();
			
			String sql = "delete from customers where id = ?";
			
			if(idsStr != null && !"".equals(idsStr)) {
				String[] ids = idsStr.split(",");
				String[][] params = new String[ids.length][1];
				for(int i = 0; i < ids.length; i++) {
					params[i][0] = ids[i];
				}
				
				//{{1},{2},{3}}				
				qr.batch(conn, sql, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(conn);
		}			
	}
}
