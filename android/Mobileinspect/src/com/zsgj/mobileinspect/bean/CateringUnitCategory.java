package com.zsgj.mobileinspect.bean;

/**
 * 餐饮单位类别：特大、大、中、小
 * @author Hlen
 *
 */
public class CateringUnitCategory {
    /// 主键
    private long Id ;
    /// 名称
    private String Name ;
    /// 描述
    private String Description ;
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
    

}
