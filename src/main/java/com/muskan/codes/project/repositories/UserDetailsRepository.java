package com.muskan.codes.project.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.muskan.codes.project.entities.UserDetails;

public interface UserDetailsRepository extends MongoRepository<UserDetails,String>{
    Optional<UserDetails> findByEmail(String email);
}
