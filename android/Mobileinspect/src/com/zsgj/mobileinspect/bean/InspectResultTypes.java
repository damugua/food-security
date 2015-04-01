package com.zsgj.mobileinspect.bean;

import java.util.List;

public class InspectResultTypes extends DtoBase {
	private List<InspectResultType> InspectResultTypes;

	public List<InspectResultType> getInspectResultTypes() {
		return InspectResultTypes;
	}

	public void setInspectResultTypes(List<InspectResultType> inspectResultTypes) {
		InspectResultTypes = inspectResultTypes;
	}
}
