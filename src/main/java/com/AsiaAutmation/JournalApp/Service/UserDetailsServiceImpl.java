package com.AsiaAutmation.JournalApp.Service;

import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Users user = userRepository.findByUserName(username).orElse(null);
        Users user = userService.getUserByUserName(username);
        if(user != null){
            return User.withUsername(user.getUserName()).password(user.getPassword()).roles(user.getRoles().toArray(new String[0])).build();
        }
        throw new UsernameNotFoundException("User not found " + username);
    }
}
