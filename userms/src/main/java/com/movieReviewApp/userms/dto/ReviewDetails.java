package com.movieReviewApp.userms.dto;

import java.util.Objects;

public class ReviewDetails {

	private Integer id;
	private String review;
	private Integer rating;
	private String userName;
	private MovieDetails movie;

	public Integer getId() {
		return id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MovieDetails getMovie() {
		return movie;
	}

	public void setMovie(MovieDetails movie) {
		this.movie = movie;
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
		ReviewDetails other = (ReviewDetails) obj;
		return Objects.equals(id, other.id);
	}

	
}
