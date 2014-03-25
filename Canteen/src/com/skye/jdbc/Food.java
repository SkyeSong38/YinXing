package com.skye.jdbc;


public class Food {

	private String id;
	private String name;
	private String userName;
	private String photo;
	private String school;
	private float price;
	private String date;
	private String canteen = "";
	private String type = "";
	
	private int good;
	private int bad;

	
	public Food() {
	}

	public Food(String name, String userName, String photo, String school,
			float price, String date, String canteen, String type, int good,
			int bad) {
		this.name = name;
		this.userName = userName;
		this.photo = photo;
		this.school = school;
		this.price = price;
		this.date = date;
		this.canteen = canteen;
		this.type = type;
		this.good = good;
		this.bad = bad;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCanteen() {
		return canteen;
	}

	public void setCanteen(String canteen) {
		this.canteen = canteen;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}
}
