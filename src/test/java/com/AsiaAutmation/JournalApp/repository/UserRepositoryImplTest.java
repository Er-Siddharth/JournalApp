package com.AsiaAutmation.JournalApp.repository;

import com.AsiaAutmation.JournalApp.Repository.UserRepository;
import com.AsiaAutmation.JournalApp.Repository.UserRepositoryImpl;
import com.AsiaAutmation.JournalApp.Service.UserDetailsServiceImpl;
import com.AsiaAutmation.JournalApp.Service.UserDetailsServiceImplTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class UserRepositoryImplTest {

    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @Autowired
    UserRepository userRepository;

    @Test
    public void getUserTest(){
        Assertions.assertNotNull(userRepositoryImpl.getUserForSA());
    }

    @Test
    public void getCity(){
        Assertions.assertEquals("Pune",userRepository.findCityByUserName("Siddharth").get().getCity());
    }

}
