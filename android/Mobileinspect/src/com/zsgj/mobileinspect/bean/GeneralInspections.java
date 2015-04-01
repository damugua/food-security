package com.zsgj.mobileinspect.bean;

import java.util.List;

public class GeneralInspections extends DtoBase{
	private List<GeneralInspection> GeneralInspections ;

	public List<GeneralInspection> getGeneralInspections() {
		return GeneralInspections;
	}
	public void setGeneralInspections(List<GeneralInspection> generalInspections) {
		GeneralInspections = generalInspections;
	}
	

}
