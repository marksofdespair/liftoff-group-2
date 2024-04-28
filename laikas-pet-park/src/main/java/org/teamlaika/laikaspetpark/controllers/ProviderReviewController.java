package org.teamlaika.laikaspetpark.controllers;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.JwtGenerator;
import org.teamlaika.laikaspetpark.models.ProviderReviews;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.ProviderReviewRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;

import java.util.List;
import java.util.Optional;

// REST Controller to handle incoming requests for managing provider reviews.
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/provider-review")
public class ProviderReviewController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProviderReviewRepository providerReviewRepository;

    @GetMapping
    public ResponseEntity<List<ProviderReviews>> getAllProviderReviews() {
        List<ProviderReviews> reviews = providerReviewRepository.findAll();
        return new ResponseEntity(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ProviderReviews addProviderReview(@RequestBody ProviderReviews review) {
        return providerReviewRepository.save(review);
    }
    // TODO Get individual reviews on Provider profile page
//    @GetMapping("/individual")
//        public ResponseEntity<List<ProviderReviews>> getIndividualReviews(@RequestHeader("Authorization") String token) {
//        Claims claims = JwtGenerator.decodeToken(token);
//        System.out.println(claims);
//
//        String userId = claims.getSubject();
//
//        Optional<User> result = userRepository.findById(Integer.valueOf(userId));
//        User aUser = result.get();
//        List<ProviderReviews> reviews = aUser.getProvider();
//
//        return new ResponseEntity<List<ProviderReviews>>(reviews, HttpStatus.OK);
//        }

    }

