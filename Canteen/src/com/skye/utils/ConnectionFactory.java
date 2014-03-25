package com.skye.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	private static String USERNAME = "root";
	private static String PASSWORD = "93030800";
	private static String URL = "jdbc:mysql://localhost:3306/canteen";

	// 该类不允许被继承
	private ConnectionFactory() {
	}

	// 注册驱动
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 建立连接并返回
	public static Connection getConnection() {
			try {
				return DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		
	}

	// 释放资源
	public static void close (ResultSet rs, PreparedStatement pst,
			Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(pst!=null)
					pst.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					}
			}
		}
	}
}