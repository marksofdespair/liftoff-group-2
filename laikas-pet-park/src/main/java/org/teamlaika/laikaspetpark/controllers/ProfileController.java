package org.teamlaika.laikaspetpark.controllers;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.JwtGenerator;
import org.teamlaika.laikaspetpark.models.Owner;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.UserRepository;
import org.teamlaika.laikaspetpark.models.dto.LoginFormDTO;
import org.teamlaika.laikaspetpark.models.dto.ProfileFormDTO;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?>getUserInfo(@RequestHeader("Authorization") String token) {

        Claims claims = JwtGenerator.decodeToken(token);

        String userId = claims.getSubject();

        System.out.println(userId);

        Optional<User> result = userRepository.findById(Integer.valueOf(userId));
        User aUser = result.get();

        return ResponseEntity.ok(aUser);
    }
}
