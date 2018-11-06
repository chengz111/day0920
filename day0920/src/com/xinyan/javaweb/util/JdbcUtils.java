package com.xinyan.javaweb.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
		
	private static DataSource dataSource = new ComboPooledDataSource("jdbcApp");
	
	private JdbcUtils() {
		
	}
	
	/**
	 * 从连接池获取数据库连接
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
	 * 释放数据库连接到连接池中
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
}
