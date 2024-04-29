package org.teamlaika.laikaspetpark.controllers;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.JwtGenerator;
import org.teamlaika.laikaspetpark.models.Pet;
import org.teamlaika.laikaspetpark.models.ProviderReview;
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
    public ResponseEntity<?>getUserInfo(@RequestHeader("Authorization") String token, @RequestParam(value = "userId", required = false) String id, ProfileFormDTO profileFormDTO) {

        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        System.out.println(id);

        if (id != null) {

            Optional<User> result = userRepository.findById(Integer.valueOf(id));

            User aUser = result.get();

            List<Pet> pets = aUser.getPets();

            profileFormDTO.setUsername(aUser.getUsername());
            profileFormDTO.setName(aUser.getName());
            profileFormDTO.setEmail(aUser.getEmail());
            profileFormDTO.setAccountType(aUser.getAccountType());
            profileFormDTO.setPets(pets);

        } else {

            Claims claims = JwtGenerator.decodeToken(token);

            String userId = claims.getSubject();

            Optional<User> result = userRepository.findById(Integer.valueOf(userId));

            User aUser = result.get();

            List<Pet> pets = aUser.getPets();
            // List<ProviderReview> reviews = providerReviewRepository.findByProviderId(aUser.getProvider());

            profileFormDTO.setUsername(aUser.getUsername());
            profileFormDTO.setName(aUser.getName());
            profileFormDTO.setEmail(aUser.getEmail());
            profileFormDTO.setAccountType(aUser.getAccountType());
            profileFormDTO.setPets(pets);

        }

        return new ResponseEntity<>(profileFormDTO, HttpStatus.OK);
    }
}
