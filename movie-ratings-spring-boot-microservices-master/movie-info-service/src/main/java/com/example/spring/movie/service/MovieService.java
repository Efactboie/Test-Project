package com.example.spring.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.movie.exception.MovieAlreadyExistException;
import com.example.spring.movie.exception.MovieNotFoundException;
import com.example.spring.movie.model.Movie;
import com.example.spring.movie.repository.MovieRepository;


@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	public List<Movie> getAll(){		
		
		return repository.findAll();	
	
	}
	
	public Movie findByName(String name) {
		
		Movie movie = repository.findByName(name);
		if (movie.equals(null))
			throw new MovieNotFoundException("No movie found with the name of " + name);
		return movie;
	
	}
	
	public Movie add(Movie movie) {
		
		Movie existingMovie = repository.findByName(movie.getName());
		if (!existingMovie.equals(null))
			throw new MovieAlreadyExistException(movie.getName() + " already exist!!!");
		return repository.save(movie);
	
	}
	
	public String remove(String name) {
		
		Movie movie = repository.findByName(name);
		if (movie.equals(null))
			throw new MovieNotFoundException("No movie found with the name of " + name);
		repository.delete(movie);
		return name+" is deleted";
	
	}
}
