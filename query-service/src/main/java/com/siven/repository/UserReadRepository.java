package com.siven.repository;

import com.siven.model.UserReadModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserReadRepository extends MongoRepository<UserReadModel, String> {}

