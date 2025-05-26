package com.siven.controller;

import com.siven.model.UserReadModel;
import com.siven.repository.UserReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserQueryController {

    @Autowired
    private UserReadRepository repo;

    @GetMapping("/{id}")
    public ResponseEntity<UserReadModel> getUser(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

