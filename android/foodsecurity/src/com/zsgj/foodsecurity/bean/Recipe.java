package com.zsgj.foodsecurity.bean;
/** 
 * /// 食谱
 * @author Homer
 * @version 2015-4-2 上午11:20:35
 */
public class Recipe {
	 private Kindergarten    Kindergarten=new Kindergarten();/// 所属学校
     private long Id ;     /// 主键
     private String Explain ;   /// 说明
     private String SubmitTime ;  /// 提交时间
     private String ImageUrl ; /// 图片存储地址
	public Kindergarten getKindergarten() {
		return Kindergarten;
	}
	public void setKindergarten(Kindergarten kindergarten) {
		Kindergarten = kindergarten;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getExplain() {
		return Explain;
	}
	public void setExplain(String explain) {
		Explain = explain;
	}
	public String getSubmitTime() {
		return SubmitTime;
	}
	public void setSubmitTime(String submitTime) {
		SubmitTime = submitTime;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
     

}
