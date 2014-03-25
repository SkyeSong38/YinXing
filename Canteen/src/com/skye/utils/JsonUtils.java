package com.skye.utils;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.skye.jdbc.Comment;
import com.skye.jdbc.Food;
import com.skye.jdbc.User;

public class JsonUtils {

	private JsonUtils() {

	}

	// 从数据库里获得用户数据打包成Json
	public static String createUserToJson(String name, String school,
			String address, String info) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("name", name);
			jsonObject.put("info", info);
			jsonObject.put("school", school);
			jsonObject.put("address", address);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("转换Json出错");
		}
		return jsonObject.toString();
	}

	// 解析Json 得到用名完整信息
	public static User getUserByJson(String string) {
		User user = new User();
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(string);
			user.setName(jsonObject.getString("name"));
			user.setPassword(jsonObject.getString("password"));
			user.setSchool(jsonObject.getString("school"));
			user.setaddress(jsonObject.getString("address"));
			user.setInfo(jsonObject.getString("info"));
			user.setIsCreate(jsonObject.getBoolean("isCreate"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	// 解析Json 得到账号和密码
	public static User getUserLoginByJson(String string) {
		User user = new User();
		try {
			JSONObject jsonObject = new JSONObject(string);
			user.setName(jsonObject.getString("userName"));
			user.setPassword(jsonObject.getString("password"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	// 返回注册登陆时的成功或错误信息
	// error = 0 ; 未知错误
	// error = 1 ; 用户名已经存在

	public static String getCreateResult(String result) {
		JSONObject jsonObject = new JSONObject();
		if (result.equals("true")) {
			try {
				jsonObject.put("isSuccess", true);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (result.equals("false")) {
			try {
				jsonObject.put("isSuccess", false);
				jsonObject.put("error", 0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (result.equals("saved")) {
			try {
				jsonObject.put("isSuccess", false);
				jsonObject.put("error", 1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}

	// 将获得的排序菜变成Json串
	public static String getJsonFoodByGood(List<Food> foodList) {
		JSONArray jsonArray = new JSONArray();

		try {
			for (int i = 0; i < foodList.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				Food food = new Food();
				food = foodList.get(i);
				jsonObject.put("id", food.getId());
				jsonObject.put("name", food.getName());
				jsonObject.put("photo", food.getPhoto());
				jsonObject.put("price", food.getPrice());
				jsonObject.put("date", food.getDate());
				jsonObject.put("canteen", food.getCanteen());
				jsonObject.put("type", food.getType());
				jsonObject.put("good", food.getGood());
				jsonObject.put("bad", food.getBad());

				jsonArray.put(i, jsonObject);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.toString();
	}

	// 将获得菜品的json字符串变成food类
	public static Food getFoodByJson(String string) {
		Food food = new Food();

		try {
			JSONObject jsonObject = new JSONObject(string);
			food.setName(jsonObject.getString("name"));
			food.setUserName(jsonObject.getString("userName"));
			food.setSchool(jsonObject.getString("school"));
			food.setPrice((float) jsonObject.getDouble("price"));

			food.setCanteen(jsonObject.getString("canteen"));

			food.setType(jsonObject.getString("type"));

		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return food;
	}

	// 将获得菜品的json字符串变成food类  json中含有id  用于修改
	public static Food getIdFoodByJson(String string) {
		Food food = new Food();

		try {
			JSONObject jsonObject = new JSONObject(string);
			food.setId(jsonObject.getString("id"));
			food.setName(jsonObject.getString("name"));
			food.setSchool(jsonObject.getString("school"));
			food.setPhoto(jsonObject.getString("photo"));
			food.setPrice((float) jsonObject.getDouble("price"));

			food.setCanteen(jsonObject.getString("canteen"));

			food.setType(jsonObject.getString("type"));

		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return food;
	}

	// 将Json字符串生成一个comment对象
	public static Comment getCommentByJson(String string) {
		Comment comment = new Comment();

		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(string);
			comment.setContent(jsonObject.getString("content").toString());
			comment.setFollowId(jsonObject.getString("followId").toString());
			comment.setUserName(jsonObject.getString("userName").toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comment;
	}

	// 讲获得的评论排序变成Json串
	public static String getJsonComment(List<Comment> comments) {
		JSONArray jsonArray = new JSONArray();

		try {
			for (int i = 0; i < comments.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				Comment comment = new Comment();
				comment = comments.get(i);
				jsonObject.put("id", comment.getId());
				jsonObject.put("content", comment.getContent());
				jsonObject.put("good", comment.getGood());
				jsonObject.put("userName", comment.getUserName());
				jsonObject.put("date", comment.getDate());

				jsonArray.put(i, jsonObject);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.toString();
	}
	
	//返回 学校的 所有食堂
	public static String getAllJsonCanteen(List<String> schools) throws JSONException{
		JSONArray jsonArray = new JSONArray();
		
		for (int i = 0; i < schools.size(); i++) {
//			JSONObject jsonObject = new JSONObject();
			String string = schools.get(i);
//			jsonObject.put("canteen", string);
			jsonArray.put(i, string);
		}
		
		return jsonArray.toString();
	}
	
}
