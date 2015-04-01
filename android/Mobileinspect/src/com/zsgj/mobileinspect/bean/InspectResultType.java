package com.zsgj.mobileinspect.bean;

/**
 *   日常巡查结果类型
 * @author Hlen
 *
 */
public class InspectResultType {
    private long Id ;
    //名称
    private String Name ;//  [Required(ErrorMessage = "巡查结果名称")] [StringLength(50, ErrorMessage = "长度超过限制")]
    private boolean IsVisible; //是否可见
    private long ParentId ; //上级Id
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
	public boolean isIsVisible() {
		return IsVisible;
	}
	public void setIsVisible(boolean isVisible) {
		IsVisible = isVisible;
	}
	public long getParentId() {
		return ParentId;
	}
	public void setParentId(long parentId) {
		ParentId = parentId;
	}

}
