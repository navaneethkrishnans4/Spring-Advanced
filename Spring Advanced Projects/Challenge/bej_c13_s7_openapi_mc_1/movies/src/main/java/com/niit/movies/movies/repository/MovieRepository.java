package com.niit.movies.movies.repository;


import com.niit.movies.movies.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByGenresContaining(String genre);
    List<Movie> findByDirectorName(String name);
    List<Movie> findByReleaseYear(int year);
}