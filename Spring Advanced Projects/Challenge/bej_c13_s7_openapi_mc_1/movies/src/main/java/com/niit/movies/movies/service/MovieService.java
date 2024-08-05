package com.niit.movies.movies.service;

import com.niit.movies.movies.domain.Movie;

import java.util.List;

public interface MovieService {
    Movie addMovie(Movie movie);
    List<Movie> getAllMovies();
    List<Movie> getMoviesByGenre(String genre);
    List<Movie> getMoviesByDirector(String name);
    List<Movie> getMoviesByReleaseYear(int year);
}