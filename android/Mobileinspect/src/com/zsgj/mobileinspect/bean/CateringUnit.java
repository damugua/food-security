package com.zsgj.mobileinspect.bean;

/**
 * 餐饮单位
 * @author Hlen
 * 
 */
public class CateringUnit {

	private Kindergarten Kindergarten = new Kindergarten();// / 学校
	private CateringUnitCategory CateringUnitCategory = new CateringUnitCategory(); // /
	private CateringUnitType CateringUnitType = new CateringUnitType(); // /
	private Syj Syj = new Syj(); // / 食药局局所
	private Long Id;// / 主键
	private String Name; // / 名称
	private String Address; // / 地址
	private String Phone; // / 电话
	private String CirculationLicense; // / 食品流通许可证号
	private String CirculationLicenseImage;// / 食品流通许可证图片存储路径
	private String Principal; // / 负责人
	private String PrincipalPhone; // / 负责人电话
	private boolean Status;// / 是否有效
	public Kindergarten getKindergarten() {
		return Kindergarten;
	}
	public void setKindergarten(Kindergarten kindergarten) {
		Kindergarten = kindergarten;
	}
	public CateringUnitCategory getCateringUnitCategory() {
		return CateringUnitCategory;
	}
	public void setCateringUnitCategory(CateringUnitCategory cateringUnitCategory) {
		CateringUnitCategory = cateringUnitCategory;
	}
	public CateringUnitType getCateringUnitType() {
		return CateringUnitType;
	}
	public void setCateringUnitType(CateringUnitType cateringUnitType) {
		CateringUnitType = cateringUnitType;
	}
	public Syj getSyj() {
		return Syj;
	}
	public void setSyj(Syj syj) {
		Syj = syj;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getCirculationLicense() {
		return CirculationLicense;
	}
	public void setCirculationLicense(String circulationLicense) {
		CirculationLicense = circulationLicense;
	}
	public String getCirculationLicenseImage() {
		return CirculationLicenseImage;
	}
	public void setCirculationLicenseImage(String circulationLicenseImage) {
		CirculationLicenseImage = circulationLicenseImage;
	}
	public String getPrincipal() {
		return Principal;
	}
	public void setPrincipal(String principal) {
		Principal = principal;
	}
	public String getPrincipalPhone() {
		return PrincipalPhone;
	}
	public void setPrincipalPhone(String principalPhone) {
		PrincipalPhone = principalPhone;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	

}
