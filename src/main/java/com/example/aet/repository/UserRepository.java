package com.example.aet.repository;

import com.example.aet.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String userName);
    boolean existsByUsername(String userName);
}
