package com.AsiaAutmation.JournalApp.Service;

import com.AsiaAutmation.JournalApp.Entity.JournalEntry;
import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Enums.Exceptions;
import com.AsiaAutmation.JournalApp.Exception.InvalidArgumentException;
import com.AsiaAutmation.JournalApp.Exception.UserNotFoundException;
import com.AsiaAutmation.JournalApp.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Slf4j
public class UserService {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    WeatherService weatherService;

    @Autowired
    UserRepository userRepository;

    public Users addUser(Users user) {
        if (!isValidUsername(user.getUserName())) throw new IllegalArgumentException("Invalid UserName");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        return this.saveUser(user);
    }

    public Users createAdmin(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        return this.saveUser(user);
    }

    public Users saveUser(Users user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Error Occurred while saving user {}", user.getUserName(), e);
        }
        return null;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUser(ObjectId id) {
        userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(Exceptions.USER_NOT_FOUND));
        userRepository.deleteById(id);
        return true;
    }


    public boolean updateUser(Users user, String userName) {
        Users existingUser = getUserByUserName(userName);
        if (existingUser != null) {
            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            if(user.getEmail() != null && !user.getEmail().isBlank()) existingUser.setEmail(user.getEmail());
            if(user.getCity() != null && !user.getCity().isBlank()) existingUser.setCity(user.getCity());
            existingUser.setSentimentAnalysis(user.getSentimentAnalysis() == null ? existingUser.getSentimentAnalysis() : user.getSentimentAnalysis());
            this.addUser( existingUser);
            return true;
        } else return false;
    }

    public boolean updateUser(Users user, String userName, List<String> roles){
        Users existingUser = getUserByUserName(userName);
        if (existingUser != null) {
            if(roles !=null && !roles.isEmpty()) existingUser.setRoles(roles);
            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            if(user.getEmail() != null && !user.getEmail().isBlank()) existingUser.setEmail(user.getEmail());
            if(user.getCity() != null && !user.getCity().isBlank()) existingUser.setCity(user.getCity());
            existingUser.setSentimentAnalysis(user.getSentimentAnalysis() == null ? existingUser.getSentimentAnalysis() : user.getSentimentAnalysis());
            this.saveUser(existingUser);
            return true;
        }
        else return false;
    }



    public Optional<Users> findUser(ObjectId id) {
        return userRepository.findById(id);
    }

    public Users getUserFromOptional(Optional<Users> user) {
        return user.orElseThrow(()-> new UserNotFoundException(Exceptions.USER_NOT_FOUND));
    }

    public List<JournalEntry> getUserEntries(Users user) {
        return user.getEntries();
    }

    public Users getUserByUserName(String username) {
        if(username.isBlank())  throw new InvalidArgumentException(Exceptions.INVALID_ARGS);
        return userRepository.findByUserName(username).orElseThrow(() -> new UserNotFoundException(Exceptions.USER_NOT_FOUND));
    }

    public boolean deleteUser(String username) {
            getUserByUserName(username);
            userRepository.deleteUserByUserName(username);
            return true;
    }

    public boolean isValidUsername(String name) {

        // Regex to check valid username.
        String regex = "^[A-Za-z]\\w{5,29}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the username is empty
        // return false
        if (name.isBlank()) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given username
        // and regular expression.
        Matcher m = p.matcher(name);

        // Return if the username
        // matched the ReGex
        return m.matches();
    }

    public ResponseEntity<?> greetings(String userName) {
        String city = this.getUserByUserName(userName).getCity();
        String response = "";
        if (city != null && !city.isEmpty()) {
            WeatherResponse weatherResponse = weatherService.getResponse(city);
            if (weatherResponse != null) {
                response = response + " today it feels like " + weatherResponse.getCurrent().getFeelslike();
            }
        }
        return ResponseEntity.ok().body("Hello " + userName + response);

    }

}
