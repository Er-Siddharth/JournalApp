package com.AsiaAutmation.JournalApp.Service;

import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    //@Autowired
    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

//    //@MockBean
//    @Mock
//    UserRepository userRepository;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUsername(){
        when(userService.getUserByUserName("Sid")).thenReturn(Users.builder().userName("Sid").password("Siddharth").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("abc");
        Assertions.assertNotNull(user);
    }


}
