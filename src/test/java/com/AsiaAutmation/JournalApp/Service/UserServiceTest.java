package com.AsiaAutmation.JournalApp.Service;

import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("dev")
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testGetUserByUsername(){
        assertNotNull(userRepository.findByUserName("Siddharth"));
    }

    @ParameterizedTest
    @ValueSource(strings ={
        "Siddharth", "admin", "new Admin", "abc"
    })
    public void testGetUserByUsername(String username){
        assertNotNull(userRepository.findByUserName(username).orElse(null), "Failed for username >> "+" "+username);
    }

    @ParameterizedTest
    @CsvSource({
            "10,10,20",
            "10,20,30",
            "10,30,40",
            "10,40,80"
    })
    public void test(int a, int b, int sum){
        assertEquals(sum,a+b);
    }

    @ParameterizedTest
    @ArgumentsSource(userArgumentsProvider.class)
    public void addUserTest(Users user){
        userService.addUser(user);
        assertEquals(userRepository.findByUserName(user.getUserName()).orElse(null),user);
    }


    @Disabled
    @Test
    public void test(){
        assertEquals(40,20+20);
    }
}
