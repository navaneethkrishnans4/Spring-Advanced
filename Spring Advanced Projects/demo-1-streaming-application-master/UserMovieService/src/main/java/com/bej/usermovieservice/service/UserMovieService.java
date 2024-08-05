package com.bej.usermovieservice.service;

import com.bej.usermovieservice.exception.UserAlreadyExistsException;
import com.bej.usermovieservice.domain.Movie;
import com.bej.usermovieservice.domain.User;
import com.bej.usermovieservice.exception.MovieNotFoundException;
import com.bej.usermovieservice.exception.UserNotFoundException;

import java.util.List;

public interface UserMovieService {
User registerUser(User user) throws UserAlreadyExistsException;
User saveUserMovieToList(Movie movie,String email) throws UserNotFoundException;
User deleteUserMovieFromList(String email,String movieId) throws UserNotFoundException, MovieNotFoundException;
List<Movie> getAllUserMovies(String email) throws UserNotFoundException;
List<Movie> getAllMoviesByGenre(String genre);
List<Movie> getAllMovies();
}
