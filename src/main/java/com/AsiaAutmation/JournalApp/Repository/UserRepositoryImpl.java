package com.AsiaAutmation.JournalApp.Repository;

import com.AsiaAutmation.JournalApp.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate ;

    public List<Users> getUserForSA(){
        Query query = new Query();
        //query.addCriteria(Criteria.where("userName").is("admin"));
        Criteria criteria = new Criteria();
        query.addCriteria(criteria.andOperator(
                Criteria.where("email").regex("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"),
                Criteria.where("sentimentAnalysis").is(true)
        ));
        List<Users> users =mongoTemplate.find(query,Users.class);
        return  users;
    }
}
