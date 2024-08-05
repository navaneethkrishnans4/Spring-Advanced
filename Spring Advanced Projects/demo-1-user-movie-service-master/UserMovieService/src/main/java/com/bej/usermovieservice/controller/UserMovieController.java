package com.bej.usermovieservice.controller;

import com.bej.usermovieservice.domain.Movie;
import com.bej.usermovieservice.service.UserMovieService;
import com.bej.usermovieservice.domain.User;
import com.bej.usermovieservice.exception.MovieNotFoundException;
import com.bej.usermovieservice.exception.UserAlreadyExistsException;
import com.bej.usermovieservice.exception.UserNotFoundException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class UserMovieController {
private UserMovieService userMovieService;
private ResponseEntity<?> responseEntity;
@Autowired
    public UserMovieController(UserMovieService userMovieService) {
        this.userMovieService = userMovieService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
    try {
        responseEntity =  new ResponseEntity<>(userMovieService.registerUser(user), HttpStatus.CREATED);
    }
    catch(UserAlreadyExistsException e)
    {
        throw new UserAlreadyExistsException();
    }
    return responseEntity;
    }
    @PostMapping("/user/movie")
    public ResponseEntity<?> saveUserMovieToList(@RequestBody Movie movie, HttpServletRequest request) throws UserNotFoundException {
    try {
        System.out.println("header" +request.getHeader("Authorization"));
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("email from claims :: " + claims.getSubject());
        String email = claims.getSubject();
        System.out.println("email :: "+email);
        responseEntity = new ResponseEntity<>(userMovieService.saveUserMovieToList(movie, email), HttpStatus.CREATED);
    }
    catch (UserNotFoundException e)
    {
        throw new UserNotFoundException();
    }
    return responseEntity;
    }
    @GetMapping("/user/movies")
    public ResponseEntity<?> getAllUserMoviesFromList(HttpServletRequest request) throws UserNotFoundException {
    try{
        System.out.println("header" +request.getHeader("Authorization"));
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("email from claims :: " + claims.getSubject());
        String email = claims.getSubject();
        System.out.println("email :: "+email);
        responseEntity = new ResponseEntity<>(userMovieService.getAllUserMovies(email), HttpStatus.OK);
    }catch(UserNotFoundException e)
    {
        throw new UserNotFoundException();
    }
       return responseEntity;
    }
    @DeleteMapping("/user/{movieId}")
    public ResponseEntity<?> deleteUserProductFromList(@PathVariable String movieId,HttpServletRequest request)
            throws UserNotFoundException, MovieNotFoundException
    {
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("email from claims :: " + claims.getSubject());
        String email = claims.getSubject();
        System.out.println("email :: "+email);
        try {
            responseEntity = new ResponseEntity<>(userMovieService.deleteUserMovieFromList(email, movieId), HttpStatus.OK);
        } catch (UserNotFoundException | MovieNotFoundException m) {
            throw new MovieNotFoundException();
        }
        return responseEntity;
    }
}

