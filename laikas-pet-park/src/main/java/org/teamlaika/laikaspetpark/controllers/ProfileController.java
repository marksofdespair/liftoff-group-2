package org.teamlaika.laikaspetpark.controllers;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.JwtGenerator;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.UserRepository;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<User>getUserInfo(@RequestHeader("Authorization") String token) {

        Claims claims = JwtGenerator.decodeToken(token);

        String userId = claims.getSubject();

        System.out.println(userId);

        Optional<User> result = userRepository.findById(Integer.valueOf(userId));
        User aUser = result.get();

        return new ResponseEntity<>(aUser,HttpStatus.OK);
    }
}
