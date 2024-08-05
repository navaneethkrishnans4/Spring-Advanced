package com.bej.muzixservice.controller;

import com.bej.muzixservice.domain.Track;
import com.bej.muzixservice.domain.User;
import com.bej.muzixservice.exception.TrackAlreadyExistsException;
import com.bej.muzixservice.exception.TrackNotFoundException;
import com.bej.muzixservice.exception.UserAlreadyExistsException;
import com.bej.muzixservice.exception.UserNotFoundException;
import com.bej.muzixservice.service.TrackServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v2")
public class TrackController {
    // Auto wire the service layer object
    private final TrackServiceImpl trackService;

    @Autowired
    public TrackController(TrackServiceImpl trackService) {
        this.trackService = trackService;
    }
    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Register a new user and save to db, return 201 status if user is saved else 500 status
        try {
            User savedUser = trackService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("user/track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track, HttpServletRequest request) {
       // add a track to a specific user, return 201 status if track is saved else 500 status
        String userId = extractUserIdFromToken(request);
        try {
            User user = trackService.saveUserTrackToWishList(track, userId) ;
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (TrackAlreadyExistsException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("user/tracks")
    public ResponseEntity<?> getAllTracks(HttpServletRequest request) {
        // display all the tracks of a specific user, extract user id from claims,
        // return 200 status if user is saved else 500 status
        String userId = extractUserIdFromToken(request);
        try {
            List<Track> tracks = trackService.getAllUserTracksFromWishList(userId);
            return ResponseEntity.ok(tracks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("user/track/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable String trackId,HttpServletRequest request) throws TrackNotFoundException {
        // delete a track based on user id and track id, extract user id from claims
        // return 200 status if user is saved else 500 status
        String userId = extractUserIdFromToken(request);
        try {
            User user = trackService.deleteTrack(userId, trackId);
            return ResponseEntity.ok(user);
        } catch (TrackNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("user/track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track, HttpServletRequest request){
        // update a track based on user id and track id, extract user id from claims
        // return 200 status if user is saved else 500 status
        String userId = extractUserIdFromToken(request);
        try {
            User user = trackService.updateUserTrackWishListWithGivenTrack(userId, track);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException | TrackNotFoundException | TrackAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    private String extractUserIdFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Claims claims = Jwts.parser().setSigningKey("secret_key").parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
