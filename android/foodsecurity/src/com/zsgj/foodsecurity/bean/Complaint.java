package com.zsgj.foodsecurity.bean;
/**
 *  投诉举报
 * @author Hlen
 *
 */
public  class Complaint {
    private long Id ;/// 主键
    /// 标题
    private String Title ;
    /// 内容
    private String Content ;
    /// 投诉人
    private String Complainant ;
    /// 被投诉人
    private String ByComplainant ;
    /// 投诉时间
    private String Time ;
    /// 状态，是否处理
    private boolean Status ;
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
	public String getComplainant() {
		return Complainant;
	}
	public void setComplainant(String complainant) {
		Complainant = complainant;
	}
	public String getByComplainant() {
		return ByComplainant;
	}
	public void setByComplainant(String byComplainant) {
		ByComplainant = byComplainant;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
    
    
}
