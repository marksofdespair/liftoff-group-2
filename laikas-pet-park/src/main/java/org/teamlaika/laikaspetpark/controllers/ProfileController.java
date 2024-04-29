package org.teamlaika.laikaspetpark.controllers;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.JwtGenerator;
import org.teamlaika.laikaspetpark.models.Pet;
import org.teamlaika.laikaspetpark.models.ProviderReviews;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.ProviderReviewRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;
import org.teamlaika.laikaspetpark.models.dto.ProfileFormDTO;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    ProviderReviewRepository providerReviewRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?>getUserInfo(@RequestHeader("Authorization") String token, ProfileFormDTO profileFormDTO) {

        Claims claims = JwtGenerator.decodeToken(token);
        System.out.println(claims);

        String userId = claims.getSubject();

        System.out.println(userId);

        Optional<User> result = userRepository.findById(Integer.valueOf(userId));

        User aUser = result.get();
        //List<Pet> pets = aUser.getPets();
        // List<ProviderReview> reviews = providerReviewRepository.findByProviderId(aUser.getProvider());

        profileFormDTO.setUsername(aUser.getUsername());
        profileFormDTO.setName(aUser.getName());
        profileFormDTO.setAccountType(aUser.getAccountType());
        //profileFormDTO.setPets(pets);

        return new ResponseEntity<>(profileFormDTO, HttpStatus.OK);
    }
}