package com.AsiaAutmation.JournalApp.Controller;

import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/Health-Check")
    public String healthCheck(){
        return "ok";
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> createUser(@RequestBody @Valid Users user){
        try{
            userService.addUser(user);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid Username is entered",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user.getUserName()+"saved successfully",HttpStatus.CREATED);
    }
}
