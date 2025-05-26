package com.siven.repository;

import com.siven.model.UserReadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserReadRepository extends JpaRepository<UserReadModel, Long> {}

