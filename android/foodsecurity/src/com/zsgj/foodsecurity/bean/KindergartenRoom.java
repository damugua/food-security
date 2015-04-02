package com.zsgj.foodsecurity.bean;
/** 
 * /// 学校房间信息
 * @version 2015-4-2 上午11:12:18
 */
public class KindergartenRoom {
	 private Kindergarten  Kindergarten=new Kindergarten();/// 所属的学校
	 private long Id ; /// 主键
	 private String Name ;/// 房间名称
	 private String NickName ;  /// 别名
	 private int Type ; /// 类别
	 private int Status ; /// 状态；0:未正常,1:正常
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
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	 
     

}
