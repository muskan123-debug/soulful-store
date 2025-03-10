package com.muskan.codes.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.muskan.codes.project.entities.SignupRequest;
import com.muskan.codes.project.entities.Users;
import com.muskan.codes.project.repositories.UsersRepository;
import com.muskan.codes.project.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsersRepository userRepository;


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }

    @PostMapping("/test/signup")
    public ResponseEntity<String> signuptest(@RequestBody SignupRequest request) {
    if (request.getName() == null || request.getName().isEmpty()) {
        return ResponseEntity.badRequest().body("Name is required!");
    }

    Users user = new Users();
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setName(request.getName());  // Ensure 'name' is set
    user.setRole(request.getRole());
    //user.setPhoneNumber(request.getPhoneNumber());

    userRepository.save(user);

    return ResponseEntity.ok("User registered successfully!");
}

    
}
