package com.AsiaAutmation.JournalApp.Repository;

import com.AsiaAutmation.JournalApp.Entity.ConfigJournalApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalApp, ObjectId>{

}
