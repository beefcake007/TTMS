package com.fanye.model;

public class Hall {

	private int hallId;
	private String hallName;
	private int hallRow;
	private int hallColumn;
	private String hallDescription;
	
	
	public Hall() {
		super();
	}
	
	
	public Hall(String hallName, int hallRow, int hallColumn,
			String hallDescription) {
		super();
		this.hallName = hallName;
		this.hallRow = hallRow;
		this.hallColumn = hallColumn;
		this.hallDescription = hallDescription;
	}


	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public int getHallRow() {
		return hallRow;
	}
	public void setHallRow(int hallRow) {
		this.hallRow = hallRow;
	}
	public int getHallColumn() {
		return hallColumn;
	}
	public void setHallColumn(int hallColumn) {
		this.hallColumn = hallColumn;
	}
	public String getHallDescription() {
		return hallDescription;
	}
	public void setHallDescription(String hallDescription) {
		this.hallDescription = hallDescription;
	}
	
}
