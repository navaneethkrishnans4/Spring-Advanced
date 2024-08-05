// MovieController.java
package com.niit.movies.movies.controller;

import com.niit.movies.movies.domain.Movie;
import com.niit.movies.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    @GetMapping("/director/{name}")
    public List<Movie> getMoviesByDirector(@PathVariable String name) {
        return movieService.getMoviesByDirector(name);
    }

    @GetMapping("/year/{year}")
    public List<Movie> getMoviesByReleaseYear(@PathVariable int year) {
        return movieService.getMoviesByReleaseYear(year);
    }
}
