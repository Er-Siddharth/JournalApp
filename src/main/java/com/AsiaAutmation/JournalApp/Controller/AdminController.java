package com.AsiaAutmation.JournalApp.Controller;

import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers(){
       List<Users> users =  userService.getAllUsers();
       if(users.isEmpty()) return ResponseEntity.badRequest().body(null);
       else return ResponseEntity.ok().body(users);
    }

    @PostMapping("/add-user-admin")
    public ResponseEntity<?> addUserAdmin(@RequestBody Users user){
        if(user != null) return ResponseEntity.ok().body(userService.createAdmin(user));
        else return ResponseEntity.badRequest().body(null);
    }
}
