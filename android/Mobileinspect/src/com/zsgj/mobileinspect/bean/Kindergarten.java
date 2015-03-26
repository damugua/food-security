package com.zsgj.mobileinspect.bean;


public class Kindergarten{

	private Syj Syj = new Syj(); // / 所属食药局所
	// / 主键
	private Long Id;
	// / 名称
	private String Name;
	// / 地址
	private String Address;
	// / 电话
	private String Phone;
	// / 状态：1、正常营业；2、暂停；3、关闭
	private int Status;
	public Syj getSyj() {
		return Syj;
	}
	public void setSyj(Syj syj) {
		Syj = syj;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}

}
