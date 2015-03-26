package com.zsgj.mobileinspect.bean;

/*
 * 地市
 */
public class City {
	private Long Id; // / 主键
	private String Name;// / 名称
	private String Code; // / 编码
	private Province Province= new Province() ; // / 所属省份
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
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public Province getProvince() {
		return Province;
	}
	public void setProvince(Province province) {
		Province = province;
	}
	
}
