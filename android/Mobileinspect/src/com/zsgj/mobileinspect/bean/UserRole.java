package com.zsgj.mobileinspect.bean;

/*
 * 用户角色
 */
public class UserRole {
	private long Id;// 主键
	private long RoleId;// 角色
	private long SyjUserId; // 食药监局用户
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public long getRoleId() {
		return RoleId;
	}
	public void setRoleId(long roleId) {
		RoleId = roleId;
	}
	public long getSyjUserId() {
		return SyjUserId;
	}
	public void setSyjUserId(long syjUserId) {
		SyjUserId = syjUserId;
	}
	
}
