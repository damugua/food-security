package com.zsgj.mobileinspect.bean;


/*
 * 区县
 */
public class Prefecture{
    private Long Id ; /// 主键
    private String Code ;/// 编码
    private String Name ; /// 名称
    private City City ;/// 所属地市
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
	public City getCity() {
		return City;
	}
	public void setCity(City city) {
		City = city;
	}
    
}
