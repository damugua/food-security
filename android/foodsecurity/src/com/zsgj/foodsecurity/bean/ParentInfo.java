package com.zsgj.foodsecurity.bean;
/**
 * // 家长信息
 */
public class ParentInfo {
	private String AccreditTime;
	private String Address;
	private String Code;
	private boolean Enable;
	private int Id;
	private String Name;
	private String Password;
	private String Phone;
	private String Relation;
	private boolean Status;
	public String getAccreditTime() {
		return AccreditTime;
	}
	public void setAccreditTime(String accreditTime) {
		AccreditTime = accreditTime;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public boolean isEnable() {
		return Enable;
	}
	public void setEnable(boolean enable) {
		Enable = enable;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getRelation() {
		return Relation;
	}
	public void setRelation(String relation) {
		Relation = relation;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	
}
