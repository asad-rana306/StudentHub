package com.Student.Hub.Repository;

import com.Student.Hub.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);

}
