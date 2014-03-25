package com.skye.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skye.utils.ConnectionFactory;
import com.skye.utils.JsonUtils;

public class UserTools {

	private UserTools() {

	}

	// 登陆
	public static boolean login(String string) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		int i = 0;
		
		String sql = "SELECT u_name FROM user_info WHERE u_name = ? AND u_password = ?;";
		User user = JsonUtils.getUserLoginByJson(string);
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			
			rs = pst.executeQuery();
			
			rs.last();
			if (rs.getRow() != 0) {
				i = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, conn);
		}
		return i == 0 ?false:true;

	}

	// 通过用户名字得到用户信息
	public static String getUserInfoByName(String u_name) {
		
		User user = new User();
		UserInfoByName(u_name, user);
		String string = JsonUtils.createUserToJson(user.getName(),
				user.getSchool(), user.getAddress(), user.getInfo());
		return string;
	}

	private static void UserInfoByName(String u_name, User user) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "select * from user_info where u_name = ?";
		try {
			pst = conn.prepareStatement(sql);
			// 用这种方法可以解决sql语句的复杂问题，并且解决sql注入问题（‘or 1 or’）
			pst.setString(1, u_name);
			rs = pst.executeQuery();

			while (rs.next()) {
				user.setName(rs.getString("u_name").toString());
				user.setaddress(rs.getString("u_address").toString());
				user.setSchool(rs.getString("u_school").toString());
				user.setInfo(rs.getString("u_info").toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, conn);
		}

	}
	
	

	// 创建或者修改用户
	public static String createUser(String string){
		String result = createRealUser(string);
		return JsonUtils.getCreateResult(result);
	}
	
	private static String createRealUser(String string) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		User user = JsonUtils.getUserByJson(string);
		String sql = null;

		int i = 0;// 判断是否创建了用户信息

		// 判断是否输入了info
		String infoString = ",u_info";
		String haveInfo = "?";
		String comma = ",";

		if (user.getInfo().equals("")) {
			infoString = "";
			haveInfo = "";
			comma = "";
		}

		// 判断用户是创建还是修改
		try {
			// 创建用户
			if (user.getIsCreate()) {

				// 判断用户名是否存在
				pst = conn
						.prepareStatement("SELECT u_name FROM user_info WHERE u_name = ?");
				pst.setString(1, user.getName());
				rs = pst.executeQuery();
				// 若用户名不存在
				if (!rs.next()) {
					sql = "insert into user_info (u_name,u_password,u_school,u_address"
							+ infoString
							+ ") values (?,?,?,?"
							+ comma
							+ haveInfo
							+ ");";

					pst = conn.prepareStatement(sql);

					pst.setString(1, user.getName());
					pst.setString(2, user.getPassword());
					pst.setString(3, user.getSchool());
					pst.setString(4, user.getAddress());
					// 判断在需不需要输入info
					if (!haveInfo.equals("")) {
						pst.setString(5, user.getInfo());
					}
					
					i = pst.executeUpdate();
				}
				else{
					return "saved";
				}
			}
			// 修改用户
			else {
				sql = "update user_info set u_password = ? , u_school = ? , u_address = ? , u_info= ? where u_name = ?;";
				pst = conn.prepareStatement(sql);

				pst.setString(1, user.getPassword());
				pst.setString(2, user.getSchool());
				pst.setString(3, user.getAddress());
				pst.setString(5, user.getName());
				if (haveInfo.equals("")){
					haveInfo = " ";
					pst.setString(4, haveInfo);
				}else {
					pst.setString(4, user.getInfo());
				}
	
				i = pst.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, conn);
		}
		return i == 0 ? "false" : "true";
	}
}
