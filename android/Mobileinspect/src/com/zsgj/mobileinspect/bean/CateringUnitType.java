package com.zsgj.mobileinspect.bean;

/**
 * 餐饮单位种类（幼儿园食堂、小学食堂、普通餐饮单位）
 * @author Hlen
 * 
 */
public class CateringUnitType {
	private long Id;// / 主键
	private String Name;// / 名称
	private String Remark;// / 描述
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	

}
