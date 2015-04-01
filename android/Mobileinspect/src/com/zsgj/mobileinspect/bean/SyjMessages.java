package com.zsgj.mobileinspect.bean;

import java.util.List;

public class SyjMessages extends DtoBase {
	private List<SyjMessage> SyjMessages ;

	public List<SyjMessage> getSyjMessages() {
		return SyjMessages;
	}

	public void setSyjMessages(List<SyjMessage> syjMessages) {
		SyjMessages = syjMessages;
	}
	
}
