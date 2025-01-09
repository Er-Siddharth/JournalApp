package com.AsiaAutmation.JournalApp.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PlatformTransactionManagerConfiguration {
    @Bean
    public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
}
