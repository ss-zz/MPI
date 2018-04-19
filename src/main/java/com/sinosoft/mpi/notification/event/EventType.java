package com.sinosoft.mpi.notification.event;

/**
 * 事件类型
 */
public enum EventType {
	
	ADD_PERSON("addPersonInfo"), 
	UPDATE_PERSON("updatePersonInfo"), 
	MERGE_PERSON("mergePersonInfo"), 
	SPLIT_PERSON("splitPersonInfo");

	private String name;

	private EventType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
