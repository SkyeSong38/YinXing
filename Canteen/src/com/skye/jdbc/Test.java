package com.skye.jdbc;


import org.json.JSONException;
import org.json.JSONObject;

public class Test {

	public static void main(String[] args) {
//		User user = new User("szk4","54321","male","uestc");
//		boolean result = UserTools.createUser(user);
//		System.out.println(result);
		
//		UserTools.getUserInfoByName("szk");
//		JSONObject jsonObject = new JSONObject();
//		try {
//			jsonObject.put("name","wolaile2");
//			jsonObject.put("password","aaa");
//			jsonObject.put("school","aaa");
//			jsonObject.put("address","aaa");
//			jsonObject.put("info","woyidianyebulan");
//			jsonObject.put("isCreate", false);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String string = UserTools.createUser(jsonObject.toString());
//		System.out.println(string);
		
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("name", "haochi44");
			jsonObject.put("userName", "aaa");
			jsonObject.put("school", "uestc");
//			jsonObject.put("photo", "sss");
			jsonObject.put("price", 3.5);
			jsonObject.put("canteen", "d3ewd");
			jsonObject.put("type", "");
			jsonObject.put("id","F0");
		} catch (Exception e) {
			// TODO: handle exception
		}
//		try {
//			jsonObject.put("content","taiTMDbuhaochile" );
//			jsonObject.put("username", "szk");
//			jsonObject.put("followid", "szk001");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		System.out.println(jsonObject.toString());
		Boolean bol = FoodTools.addFood(jsonObject.toString(),"photoURL","id3");
//		 String bool = FoodTools.getFoodByGood("uestc",0);
//		boolean bool = FoodTools.updateFood(jsonObject.toString());
		
		
//		boolean bool = CommentTools.addComment(jsonObject.toString());
//		String bool = CommentTools.getCommentByDate("szk001", 0);
		
//		try {
//			jsonObject.put("id", "C2");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		boolean bool = CommentTools.deleteComment(jsonObject.toString());
//		boolean bool = CommentTools.addGood(jsonObject.toString());
		
		
		String bool = FoodTools.getCanteenName("uestc");
		System.out.println(bool);
	}
	}

