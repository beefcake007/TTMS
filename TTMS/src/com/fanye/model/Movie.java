package com.fanye.model;

public class Movie {

	private int movieId;
	private String movieName;
	private String movieMainActor;
	private String movieDirector;
	private int movieDuration;
	private String movieDescription;
	private String movieImage;
	
	
	public Movie() {
		super();
	}

	
	public Movie(String movieName, String movieMainActor, String movieDirector,
			int movieDuration, String movieDescription) {
		super();
		this.movieName = movieName;
		this.movieMainActor = movieMainActor;
		this.movieDirector = movieDirector;
		this.movieDuration = movieDuration;
		this.movieDescription = movieDescription;
	}


	public String getMovieImage() {
		return movieImage;
	}


	public void setMovieImage(String movieImage) {
		this.movieImage = movieImage;
	}


	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieMainActor() {
		return movieMainActor;
	}
	public void setMovieMainActor(String movieMainActor) {
		this.movieMainActor = movieMainActor;
	}
	public String getMovieDirector() {
		return movieDirector;
	}
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	
	public int getMovieDuration() {
		return movieDuration;
	}


	public void setMovieDuration(int movieDuration) {
		this.movieDuration = movieDuration;
	}


	public String getMovieDescription() {
		return movieDescription;
	}
	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	
}
