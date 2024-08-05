// TaskServiceImpl.java
package com.niit.movies.movies.service;

import com.niit.movies.movies.domain.Movie;
import com.niit.movies.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }



    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenresContaining(genre);
    }

    @Override
    public List<Movie> getMoviesByDirector(String name) {
        return movieRepository.findByDirectorName(name);
    }

    @Override
    public List<Movie> getMoviesByReleaseYear(int year) {
        return movieRepository.findByReleaseYear(year);
    }
}