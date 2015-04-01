package com.zsgj.mobileinspect.bean;

import java.util.List;

public class Complaints extends DtoBase {
	 private List<Complaint> Complaints ;

	public List<Complaint> getComplaints() {
		return Complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		Complaints = complaints;
	}
	 
}
