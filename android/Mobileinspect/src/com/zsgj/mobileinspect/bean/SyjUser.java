package com.zsgj.mobileinspect.bean;

import java.util.List;
/*
 * 食药局用户
 */
public class SyjUser {
	private Long Id;
	//  登录帐号
	private String LoginName;
	//  登录密码
	private String Password;
	//  名称
	private String Name;
	//  电话
	private String Phone;
	// 邮箱
	private String Email;
	//  状态
	private Boolean Status;
	//  所属食药局所
	private Syj Syj;
	//  用户角色列表
	private List<UserRole> UserRoleDtos;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Boolean getStatus() {
		return Status;
	}
	public void setStatus(Boolean status) {
		Status = status;
	}
	public Syj getSyj() {
		return Syj;
	}
	public void setSyj(Syj syj) {
		Syj = syj;
	}
	public List<UserRole> getUserRoleDtos() {
		return UserRoleDtos;
	}
	public void setUserRoleDtos(List<UserRole> userRoleDtos) {
		UserRoleDtos = userRoleDtos;
	}
	

}
