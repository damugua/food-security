package com.zsgj.mobileinspect.bean;

public class SyjMessage {
	private Syj Syj = new Syj();//  所属的食药局所
	private SyjUser SyjUser = new SyjUser(); //  处理该条消息的食药局所用户
	private long Id;//  主键
	private long SenderId; //  发送方（食药局人员、学校、家长）编号
	private String Content; //  内容
	private int Type; //  发送类型/// 1、食药局所人员发送,2、家长发送,3、学校发送
	private String DateTime;//  发送时间
	private boolean Dispose; //  是否处理
	private String Remark; //  备注
	public Syj getSyj() {
		return Syj;
	}
	public void setSyj(Syj syj) {
		Syj = syj;
	}
	public SyjUser getSyjUser() {
		return SyjUser;
	}
	public void setSyjUser(SyjUser syjUser) {
		SyjUser = syjUser;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public long getSenderId() {
		return SenderId;
	}
	public void setSenderId(long senderId) {
		SenderId = senderId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	public boolean isDispose() {
		return Dispose;
	}
	public void setDispose(boolean dispose) {
		Dispose = dispose;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	

}
