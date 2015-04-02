package com.zsgj.foodsecurity.bean;

/**
 * @author Homer
 * @version 2015-4-2 上午11:16:04 
 * /// 摄像机
 */
public class Camera {
	private Kindergarten Kindergarten = new Kindergarten();// / 所属学校
	private long Id; // / 主键
	private String Name; // / 摄像头名称
	private int CameraNo; // / 通道号
	private String SerialNumber;// / 序列号
	private String Remark; // / 备注
	private int Status;// / 状态
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
	public int getCameraNo() {
		return CameraNo;
	}
	public void setCameraNo(int cameraNo) {
		CameraNo = cameraNo;
	}
	public String getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	

}
