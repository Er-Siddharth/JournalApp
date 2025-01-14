package com.AsiaAutmation.JournalApp.Controller;

import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Service.Current;
import com.AsiaAutmation.JournalApp.Service.UserService;
import com.AsiaAutmation.JournalApp.Service.WeatherResponse;
import com.AsiaAutmation.JournalApp.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody Users user){
        String userName  = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userService.updateUser(user, userName)){
            return ResponseEntity.ok().body(null);
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(){
        String userName  = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userService.deleteUser(userName)){
            return ResponseEntity.ok().body(null);
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/greetings")
    public ResponseEntity<?> greetings(){
        String userName  = SecurityContextHolder.getContext().getAuthentication().getName();
        return   userService.greetings(userName);
    }

}
