package org.teamlaika.laikaspetpark.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.Owner;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.UserRepository;
import org.teamlaika.laikaspetpark.models.dto.LoginFormDTO;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/profile")
public class ProfileController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<?>getUserInfo(Model model, User user, @PathVariable int userId) {

        Optional<User> result = userRepository.findById(userId);

        User aUser = result.get();

        return ResponseEntity.ok(aUser);
    }
}
