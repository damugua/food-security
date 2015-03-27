package com.zsgj.foodsecurity.bean;

public class UserInfo {
	public String phone;
	public String province;
	public String city;
	public String area;
	public String school;
	public String clazz;
	public String student;
	public String userName;
	public String relation;
	public String address;
	private static UserInfo instance;
	private UserInfo(){}
	
	public static UserInfo getInstance(){
		instance = new UserInfo();
		return instance;
	}
}
