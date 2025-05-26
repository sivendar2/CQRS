package com.siven.controller;

import com.siven.entity.User;
import com.siven.producer.EventPublisher;
import com.siven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository repo;
    @Autowired private EventPublisher publisher;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = repo.save(user);
        publisher.publishUserCreated(saved);
        return ResponseEntity.ok(saved);
    }
}
