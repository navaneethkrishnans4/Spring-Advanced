package com.bej.muzixservice.service;

import com.bej.muzixservice.domain.Track;
import com.bej.muzixservice.domain.User;
import com.bej.muzixservice.exception.TrackAlreadyExistsException;
import com.bej.muzixservice.exception.TrackNotFoundException;
import com.bej.muzixservice.exception.UserAlreadyExistsException;
import com.bej.muzixservice.exception.UserNotFoundException;
import com.bej.muzixservice.proxy.UserProxy;
import com.bej.muzixservice.repository.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements ITrackService{

    // Autowire the UserTrackRepository using constructor autowiring
    private final UserTrackRepository userTrackRepository;
    private UserProxy userProxy;
    @Autowired
    public TrackServiceImpl(UserTrackRepository userTrackRepository, UserProxy userProxy) {
        this.userTrackRepository = userTrackRepository;
        this.userProxy = userProxy;
    }
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        // Register a new user
        if (userTrackRepository.existsById(user.getUserId())) {
            throw new UserAlreadyExistsException();
        }
        User user1 = userTrackRepository.save(user);
        if(!(user1.getUserId().isEmpty()))
        {
            ResponseEntity r = userProxy.saveUser(user);
            System.out.println(r.getBody());
        }
        // Save the user
        return userTrackRepository.save(user);
    }

    @Override
    public User saveUserTrackToWishList(Track track, String userId) throws TrackAlreadyExistsException, UserNotFoundException {
        // Save the tracks to the play list of user.
        Optional<User> optionalUser = userTrackRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Check if track already exists in the wish list
            List<Track> trackList = user.getTrackList();
            if (trackList.stream().anyMatch(t -> t.getTrackId().equals(track.getTrackId()))) {
                throw new TrackAlreadyExistsException();
            }
            // Add track to wish list and save user
            trackList.add(track);
            return userTrackRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<Track> getAllUserTracksFromWishList(String userId) throws Exception {
        // Get all the tracks for a specific user
        Optional<User> optionalUser = userTrackRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getTrackList();
        } else {
            throw new UserNotFoundException();
        }

    }

    @Override
    public User deleteTrack(String userId, String trackId) throws TrackNotFoundException, UserNotFoundException {
      // delete the user details specified
        Optional<User> optionalUser = userTrackRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Check if track exists in the wish list
            List<Track> trackList = user.getTrackList();
            boolean removed = trackList.removeIf(t -> t.getTrackId().equals(trackId));
            if (!removed) {
                throw new TrackNotFoundException();
            }
            // Save user after track removal
            return userTrackRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }


    @Override
    public User updateUserTrackWishListWithGivenTrack(String userId, Track track) throws UserNotFoundException, TrackNotFoundException, TrackAlreadyExistsException {
      // Update the specific details of User
        Optional<User> optionalUser = userTrackRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Check if track exists in the wish list
            List<Track> trackList = user.getTrackList();
            boolean updated = false;
            for (int i = 0; i < trackList.size(); i++) {
                if (trackList.get(i).getTrackId().equals(track.getTrackId())) {
                    // Update track if found
                    trackList.set(i, track);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                throw new TrackNotFoundException();
            }
            // Save user after track update
            return userTrackRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }
}
