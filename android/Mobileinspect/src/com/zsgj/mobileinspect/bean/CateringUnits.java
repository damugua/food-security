package com.zsgj.mobileinspect.bean;

import java.util.List;

public class CateringUnits extends DtoBase {
	private List<CateringUnit> CateringUnits ;

	public List<CateringUnit> getCateringUnits() {
		return CateringUnits;
	}

	public void setCateringUnits(List<CateringUnit> cateringUnits) {
		CateringUnits = cateringUnits;
	}
	
}
