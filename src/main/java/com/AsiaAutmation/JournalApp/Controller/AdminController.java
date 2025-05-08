package com.AsiaAutmation.JournalApp.Controller;

import com.AsiaAutmation.JournalApp.Dto.UserRightsUpdateRequest;
import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Mapper.UserMapper;
import com.AsiaAutmation.JournalApp.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        if (users.isEmpty()) return ResponseEntity.badRequest().body(null);
        else return ResponseEntity.ok().body(users);
    }

    @PostMapping("/add-user-admin")
    public ResponseEntity<?> addUserAdmin(@RequestBody @Valid Users user) {
        if (user != null) return ResponseEntity.ok().body(userService.createAdmin(user));
        else return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "userName") String userName) {
        if (userService.deleteUser(userName)) return ResponseEntity.ok().body(null);
        else return ResponseEntity.internalServerError().body(null);
    }

    @DeleteMapping("/delete-admin")
    public ResponseEntity<?> deleteAdmin(@RequestBody String userName) {
        if (userService.deleteUser(userName)) return ResponseEntity.ok().body(null);
        else return ResponseEntity.internalServerError().body(null);
    }

    @GetMapping("/get-user-details")
    public ResponseEntity<?> getUserDetails(@RequestParam(value = "userName") String userName) {
        Users user = userService.getUserByUserName(userName);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/update-admin")
    public ResponseEntity<?> updateAdmin(@RequestBody @Valid Users user) {
        if (userService.updateUser(user, user.getUserName()))
            return ResponseEntity.ok().body("User updated successfully");
        else return ResponseEntity.internalServerError().body(null);
    }

    @PatchMapping("/change-user-rights")
    public ResponseEntity<?> changeRights(
            @RequestParam(value = "userName") String userName,
            @RequestBody UserRightsUpdateRequest request) {
        Users user = userService.updateRights(userName, request);
         if (user != null) return ResponseEntity.ok().body(userMapper.toDto(user));
         else return ResponseEntity.internalServerError().body("Failed to update rights");
    }
}
