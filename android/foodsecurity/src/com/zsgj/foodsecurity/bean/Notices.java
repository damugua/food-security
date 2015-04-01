package com.zsgj.foodsecurity.bean;

import java.util.List;

public class Notices extends DtoBase {
	private List<Notice> Notices ;

	public List<Notice> getNotices() {
		return Notices;
	}

	public void setNotices(List<Notice> notices) {
		Notices = notices;
	}
	

}
