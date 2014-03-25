package com.skye.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.skye.utils.ConnectionFactory;
import com.skye.utils.JsonUtils;

public class FoodTools {

	private FoodTools() {
	}

	// 根据学校，按照赞的数量从高到低排名得到菜
	// order是从第几个开始，0代表第一个
	public static String getFoodByGood(String school, int order) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Food> foodList = new ArrayList<Food>();

		String sql = "SELECT f_id ,f_name , f_photo , f_price ,f_date ,f_canteen , f_type ,f_good , f_bad FROM food_menu WHERE f_school = ? order by f_good desc limit ?,6;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, school);
			pst.setInt(2, order);

			rs = pst.executeQuery();
			while (rs.next()) {
				Food food = new Food();
				food.setId(rs.getString("f_id").toString());
				food.setName(rs.getString("f_name").toString());
				food.setPhoto(rs.getString("f_photo").toString());
				food.setPrice(rs.getFloat("f_price"));
				food.setDate(rs.getString("f_date").toString());
				food.setCanteen(rs.getString("f_canteen").toString());
				food.setType(rs.getString("f_type").toString());
				food.setGood(rs.getInt("f_good"));
				food.setBad(rs.getInt("f_bad"));

				foodList.add(food);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, conn);
		}

		return JsonUtils.getJsonFoodByGood(foodList);
	}

	// 增加菜品
	public static boolean addFood(String string,String photoUrl,String PhotoId) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;

		int i = 0;
		// 用来判断创建菜品时是否包含了食堂或者类型
		String canteenString = ",f_canteen";
		String typeString = ",f_type";
		String haveCanteen = ",?";
		String haveType = ",?";
		// 根据参数的json得到food对象
		Food food = JsonUtils.getFoodByJson(string);

		food.setPhoto(photoUrl);
		food.setId(PhotoId);
		
		if (food.getCanteen().equals("")) {
			canteenString = "";
			haveCanteen = "";
		}
		if (food.getType().equals("")) {
			typeString = "";
			haveType = "";
		}

		String sql = "insert into food_menu (f_id,f_name,f_username,f_photo,f_school,f_price"
				+ canteenString
				+ typeString
				+ ") values (?,?,?,?,?,?"
				+ haveCanteen + haveType + ");";

		try {
			pst = conn.prepareStatement(sql);

			pst.setString(1, food.getId());
			pst.setString(2, food.getName());
			pst.setString(3, food.getUserName());
			pst.setString(4, food.getPhoto());
			pst.setString(5, food.getSchool());
			pst.setFloat(6, food.getPrice());

			if (!canteenString.equals("") && !typeString.equals("")) {
				pst.setString(7, food.getCanteen());
				pst.setString(8, food.getType());
			} else if (!canteenString.equals("")) {
				pst.setString(7, food.getCanteen());
			} else if (!typeString.equals("")) {
				pst.setString(7, food.getType());
			}

			pst.executeUpdate();
			i = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, conn);
		}
		return i == 1 ? true : false;
	}

	// 删除菜品
	public static boolean deleteFood(String id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;

		int i = 0;
		String sql = "delete from food_menu where f_id = ?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.executeUpdate();
			i++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, conn);
		}
		return i == 0 ? false : true;
	}

	// 修改菜品
	public static boolean updateFood(String string) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		int i = 0;
		Food food = JsonUtils.getIdFoodByJson(string);

		// 用来判断数据库是否存在该菜品
		try {
			pst = conn
					.prepareStatement("select f_name from food_menu where f_id = ?;");

			pst.setString(1, food.getId());
			rs = pst.executeQuery();
			rs.last();
			if (rs.getRow() == 0) {
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "update food_menu set f_name = ?,f_school = ?,f_photo = ?,f_price = ?,f_canteen = ?,f_type = ?where f_id = ?;";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, food.getName());
			pst.setString(2, food.getSchool());
			pst.setString(3, food.getPhoto());
			pst.setFloat(4, food.getPrice());
			pst.setString(5, food.getCanteen());
			pst.setString(6, food.getType());
			pst.setString(7, food.getId());

			pst.executeUpdate();
			i++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, conn);

		}

		return i == 0 ? false : true;
	}

	// 好评+1
	public static boolean addGood(int id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;

		int i = 0;
		String sql = "update food_menu set f_good = f_good+1 where f_id = ?;";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			i++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, conn);
		}
		return i == 0 ? false : true;

	}

	// 差评+1
	public static boolean addBad(int id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;

		int i = 0;
		String sql = "update food_menu set f_bad = f_bad+1 where f_id = ?;";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			i++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, conn);
		}
		return i == 0 ? false : true;

	}
	
	//根据学校获取 食堂名称
	public static String getCanteenName(String string){
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> schools = new ArrayList<String>();
		String s = null;
		String sql = "select distinct f_canteen from food_menu where f_school = ?;";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, string);
			rs = pst.executeQuery();
			
			while(rs.next()){
				schools.add(rs.getString("f_canteen").toString());
			}
			
			s = JsonUtils.getAllJsonCanteen(schools);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(rs, pst, conn);
		}
		
		return s;
	}
}