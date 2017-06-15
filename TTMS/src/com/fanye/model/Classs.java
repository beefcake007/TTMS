package com.fanye.model;

public class Classs {

	private int classId;
	private String className;
	private int classDisCount;
	private String classDescription;
	private int classIsActive;
	
	
	public Classs() {
		super();
	}
	
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getClassIsActive() {
		return classIsActive;
	}
	public void setClassIsActive(int classIsActive) {
		this.classIsActive = classIsActive;
	}


	public int getClassDisCount() {
		return classDisCount;
	}


	public void setClassDisCount(int classDisCount) {
		this.classDisCount = classDisCount;
	}


	public String getClassDescription() {
		return classDescription;
	}


	public void setClassDescription(String classDescription) {
		this.classDescription = classDescription;
	}
	
}
