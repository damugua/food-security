package com.zsgj.foodsecurity.bean;


/*
 * 食药局所
 */
public class Syj {
	private Long Id;// / 主键
	private String Code;	// / 编码
	private String Name;// / 名称
	private String Address;// / 地址
	private int ParentId;// / 上级局所
	private Prefecture Prefecture;// / 所属区县
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
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
	public int getParentId() {
		return ParentId;
	}
	public void setParentId(int parentId) {
		ParentId = parentId;
	}
	public Prefecture getPrefecture() {
		return Prefecture;
	}
	public void setPrefecture(Prefecture prefecture) {
		Prefecture = prefecture;
	}
	
}
