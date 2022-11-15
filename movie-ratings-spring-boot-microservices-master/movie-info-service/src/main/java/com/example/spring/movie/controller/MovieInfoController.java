package com.example.spring.movie.controller;

import com.example.spring.movie.model.Movie;
import com.example.spring.movie.service.MovieService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

	@Autowired
	private MovieService service;

    @GetMapping
    public List<Movie> getAllMovies() {
    	return service.getAll();
    }
    
    @GetMapping("/{name}")
    public Movie getMovieDetails(@PathVariable String name) {
    	return service.findByName(name);
    }
    
    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
    	return service.add(movie);
    }
    
    @DeleteMapping("/{name}")
    public String removeMovie(@PathVariable String name) {
    	return service.remove(name);
    }
}
