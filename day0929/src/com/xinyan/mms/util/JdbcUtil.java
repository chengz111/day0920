package com.xinyan.mms.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * jdbc工具类
 * @author Administrator
 *
 */
public class JdbcUtil {
	//私有化
	private JdbcUtil(){
		
	}
	//实例化c3p0连接池对象
	private static DataSource dataSource = new ComboPooledDataSource("jdbcApp");
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 从连接池中获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 释放数据库连接池
	 * @param conn
	 */
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Connection conn = getConnection();
		if(conn != null) {
			System.out.println(conn);
			close(conn);
		}		
	}
	
}
