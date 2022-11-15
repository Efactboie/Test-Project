package com.example.spring.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.movie.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String>{

	public Movie findByName(String name);
}
