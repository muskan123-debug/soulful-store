package com.muskan.codes.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muskan.codes.project.entities.Users;

public interface UsersRepository extends JpaRepository<Users,Long>{
    Optional<Users> findByEmail(String email);
}
