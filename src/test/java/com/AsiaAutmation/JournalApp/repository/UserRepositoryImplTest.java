package com.AsiaAutmation.JournalApp.repository;

import com.AsiaAutmation.JournalApp.Repository.UserRepositoryImpl;
import com.AsiaAutmation.JournalApp.Service.UserDetailsServiceImpl;
import com.AsiaAutmation.JournalApp.Service.UserDetailsServiceImplTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    UserRepositoryImpl userRepository;
    @Test
    public void getUserTest(){
        Assertions.assertNotNull(userRepository.getUserForSA());
    }

}
