package org.teamlaika.laikaspetpark.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.JwtGenerator;
import org.teamlaika.laikaspetpark.models.Provider;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;
import org.teamlaika.laikaspetpark.models.dto.LoginFormDTO;
import org.teamlaika.laikaspetpark.models.dto.RegisterFormDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProviderRepository providerRepository;

    // Session-Handling Utilities
    private static final String userSessionKey = "username";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    // Custom response object for Registration
    public static class RegistrationResponse {
        private final String message;
        private final Map<String, Object> args;
        private final Map<String, Object> headers;
        private final String url;

        public RegistrationResponse(String message) {
            this.message = message;
            this.args = new HashMap<>();
            this.headers = new HashMap<>();
            this.url = "";
        }

        public String getMessage() {
            return message;
        }

        public Map<String, Object> getArgs() {
            return args;
        }

        public Map<String, Object> getHeaders() {
            return headers;
        }

        public String getUrl() {
            return url;
        }
    }

    // Registering New Users
    @GetMapping("/api/register")
    public ResponseEntity<?> displayRegistrationForm(Model model) {
        return ResponseEntity.ok(new RegistrationResponse("Registration form"));
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> processRegistrationForm(@RequestBody @Valid RegisterFormDTO registerFormDTO,
                                                     Errors errors, HttpServletRequest request,
                                                     Model model) {
        if (errors.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            for (FieldError error : errors.getFieldErrors()) {
                validationErrors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(validationErrors);
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RegistrationResponse("A user with that username already exists"));
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            return ResponseEntity.badRequest().body(new RegistrationResponse("Passwords do not match"));
        }

        User newUser = new User(registerFormDTO.getName(), registerFormDTO.getUsername(), registerFormDTO.getPassword(), registerFormDTO.getEmail(), registerFormDTO.getAccountType(), registerFormDTO.getZipcode());

        if (registerFormDTO.getAccountType().equals("Owner")) {

            userRepository.save(newUser);
            setUserInSession(request.getSession(), newUser);

            return ResponseEntity.ok(new RegistrationResponse("Registration successful"));
        } else if (registerFormDTO.getAccountType().equals("Provider")) {
            Provider newProvider = new Provider();
            newUser.setProvider(newProvider);

            userRepository.save(newUser);
            setUserInSession(request.getSession(), newUser);

            return ResponseEntity.ok(new RegistrationResponse("Registration successful"));
        } else {
            return ResponseEntity.badRequest().body(new RegistrationResponse("Invalid account type"));
        }
    }

    // Custom response object for Login
    public static class LoginResponse {
        private final String message;
        private final Map<String, Object> args;
        private final Map<String, Object> headers;
        private final String url;

        public LoginResponse(String message) {
            this.message = message;
            this.args = new HashMap<>();
            this.headers = new HashMap<>();
            this.url = "";
        }

        public String getMessage() {
            return message;
        }

        public Map<String, Object> getArgs() {
            return args;
        }

        public Map<String, Object> getHeaders() {
            return headers;
        }

        public String getUrl() {
            return url;
        }
    }

    // Handling User Login
    @GetMapping("/api/login")
    public ResponseEntity<?> displayLoginForm(Model model) {
        return ResponseEntity.ok(new LoginResponse("Login form"));
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> processLoginForm(@RequestBody @Valid LoginFormDTO loginFormDTO,
                                              Errors errors, HttpServletRequest request,
                                              Model model) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new LoginResponse("Validation failed"));
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            return ResponseEntity.notFound().build();
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            return ResponseEntity.badRequest().body(new LoginResponse("Invalid password"));
        }

        String accountType = loginFormDTO.getAccountType();

        if (accountType.equals("Owner") && theUser != null) {
            //setUserInSession(request.getSession(), theUser);
           String token =  JwtGenerator.generateJwt(theUser.getId(), theUser.getAccountType());
            return ResponseEntity.ok(token);
        } else if (accountType.equals("Provider") && theUser.getProvider() != null) {
            //setUserInSession(request.getSession(), theUser);
            String token =  JwtGenerator.generateJwt(theUser.getId(), theUser.getAccountType());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.ok(new LoginResponse("you done messed up"));
    }

    // Custom response object for Logout
    public static class LogoutResponse {
        private final String message;
        private final Map<String, Object> args;
        private final Map<String, Object> headers;
        private final String url;

        public LogoutResponse(String message) {
            this.message = message;
            this.args = new HashMap<>();
            this.headers = new HashMap<>();
            this.url = "";
        }

        public String getMessage() {
            return message;
        }

        public Map<String, Object> getArgs() {
            return args;
        }

        public Map<String, Object> getHeaders() {
            return headers;
        }

        public String getUrl() {
            return url;
        }
    }

    @GetMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok(new LogoutResponse("Logout successful"));
    }
}