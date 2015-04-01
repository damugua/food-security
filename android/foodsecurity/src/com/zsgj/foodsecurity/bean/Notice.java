package com.zsgj.foodsecurity.bean;

public class Notice {
	private Syj Syj = new Syj(); // 所属食药局所
	private long Id;// / 主键
	private String Title; // / 标题
	private String Content; // / 内容
	private String Time; // / 发布时间
	private int Category; // / 类别
	public Syj getSyj() {
		return Syj;
	}
	public void setSyj(Syj syj) {
		Syj = syj;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public int getCategory() {
		return Category;
	}
	public void setCategory(int category) {
		Category = category;
	}
	
	

}
