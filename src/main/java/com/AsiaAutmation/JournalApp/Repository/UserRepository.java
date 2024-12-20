package com.AsiaAutmation.JournalApp.Repository;

import com.AsiaAutmation.JournalApp.Entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, ObjectId> {
    Optional<Users> findByUserName(String userName);
    void deleteUserByUserName(String userName);
}
