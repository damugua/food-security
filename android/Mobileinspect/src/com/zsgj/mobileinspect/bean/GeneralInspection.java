package com.zsgj.mobileinspect.bean;

import java.util.ArrayList;
import java.util.List;
/**
 *  /// 日常巡查Dto
 * @author Hlen
 *
 */
public class GeneralInspection {

	private SyjUser SyjUser = new SyjUser(); // 食药监局巡查人员
	private CateringUnit CateringUnit = new CateringUnit();// 被巡查餐饮单位
	private List<InspectResultType> InspectResultTypes = new ArrayList<InspectResultType>();
	private long Id;// 主键
	private String InspectTime;// [StringLength(20, ErrorMessage = "长度超过限制")]
								// //巡查时间
	private String Remark;// [StringLength(100, ErrorMessage = "长度超过限制")] //备注
	public SyjUser getSyjUser() {
		return SyjUser;
	}
	public void setSyjUser(SyjUser syjUser) {
		SyjUser = syjUser;
	}
	public CateringUnit getCateringUnit() {
		return CateringUnit;
	}
	public void setCateringUnit(CateringUnit cateringUnit) {
		CateringUnit = cateringUnit;
	}
	public List<InspectResultType> getInspectResultTypes() {
		return InspectResultTypes;
	}
	public void setInspectResultTypes(List<InspectResultType> inspectResultTypes) {
		InspectResultTypes = inspectResultTypes;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getInspectTime() {
		return InspectTime;
	}
	public void setInspectTime(String inspectTime) {
		InspectTime = inspectTime;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}

	
}
