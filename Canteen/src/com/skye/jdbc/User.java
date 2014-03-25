package com.skye.jdbc;

public class User {

	private String name;
	private String password;
	private String school;
	private String address;
	private String info;
	private Boolean isCreate;

	public Boolean getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Boolean isCreate) {
		this.isCreate = isCreate;
	}

	public User() {
	}

	public User(String name, String password, String school, String address) {
		this.name = name;
		this.password = password;
		this.school = school;
		this.address = address;
	}

	public User(String name, String password, String school, String address,
			String info) {
		this.name = name;
		this.password = password;
		this.school = school;
		this.address = address;
		this.info = info;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public void setName(String object) {
		this.name = object;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
