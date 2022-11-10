package com.movieReviewApp.userms.dto;

import java.util.Objects;



public class MovieDetails {

	private Integer id;
	private String title;
	private String moviePosterUrl;
	private Integer year;
	private Double overAllRating;
	private String description;
	private String director;
	private String language;

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMoviePosterUrl() {
		return moviePosterUrl;
	}

	public void setMoviePosterUrl(String moviePosterUrl) {
		this.moviePosterUrl = moviePosterUrl;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Double getOverAllRating() {
		return overAllRating;
	}

	public void setOverAllRating(Double overAllRating) {
		this.overAllRating = overAllRating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieDetails other = (MovieDetails) obj;
		return Objects.equals(id, other.id);
	}


	
	

}
