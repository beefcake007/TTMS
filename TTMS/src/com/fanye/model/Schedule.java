package com.fanye.model;

import java.sql.Date;
import java.sql.Timestamp;


public class Schedule {

	private int scheduleId;
	private int movieId;
	private int hallId=-1;
	private float schedulePrice;
	private Timestamp scheduleBeginDate;
	private String movieName;
	private String hallName;
	private int movieDuration;
	private int hallRow;
	private int hallColumn;
	
	public Schedule() {
		super();
	}
	
	
	public Schedule(int movieId, int hallId, float schedulePrice,
			Timestamp scheduleBeginDate) {
		super();
		this.movieId = movieId;
		this.hallId = hallId;
		this.schedulePrice = schedulePrice;
		this.scheduleBeginDate = scheduleBeginDate;
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


	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public String getHallName() {
		return hallName;
	}


	public void setHallName(String hallName) {
		this.hallName = hallName;
	}


	public int getMovieDuration() {
		return movieDuration;
	}


	public void setMovieDuration(int movieDuration) {
		this.movieDuration = movieDuration;
	}


	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	public float getSchedulePrice() {
		return schedulePrice;
	}
	public void setSchedulePrice(float schedulePrice) {
		this.schedulePrice = schedulePrice;
	}
	public Timestamp getScheduleBeginDate() {
		return scheduleBeginDate;
	}
	public void setScheduleBeginDate(Timestamp scheduleBeginDate) {
		this.scheduleBeginDate = scheduleBeginDate;
	}
	
}
