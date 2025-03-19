package com.muskan.codes.project.service;

import com.muskan.codes.project.entities.SignupRequest;
import com.muskan.codes.project.entities.UserDetails;
import com.muskan.codes.project.entities.Users;
import com.muskan.codes.project.repositories.UserDetailsRepository;
import com.muskan.codes.project.repositories.UsersRepository;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class AuthService {
    private final UsersRepository usersRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsersRepository usersRepository, UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> signup(SignupRequest request) {
        if (usersRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.ok("{\n" + "\"respponse\":\"User already exixtes!\"\n" +  "}");
        }

        // Save user credentials in MySQL
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        usersRepository.save(user);

        // Save user details in MongoDB
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(request.getEmail());
        userDetails.setFullName(request.getName());
        userDetails.setPhoneNumber(request.getPhoneNumber());
        userDetailsRepository.save(userDetails);

        //return "User registered successfully!";
        return ResponseEntity.ok("{\n" + "\"respponse\":\"User registered successfully!\"\n" +  "}");
    }

    public String login(String email, String password) {
        Optional<Users> userOpt = usersRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            if (user.getPassword().equals(password) && user.getEmail().equals(email)) {
                return "Login successful!";
            }
        }
        return "Invalid credentials!";
    }
}