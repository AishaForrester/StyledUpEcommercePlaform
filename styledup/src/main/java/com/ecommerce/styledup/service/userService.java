package com.ecommerce.styledup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.styledup.model.User;
import com.ecommerce.styledup.repository.UserRepository;

@Service
public class userService {
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticate(String username, String password) throws Exception {
        User user = userRepo.findByUsername(username);
        System.out.println("user in service: " + user.getRole());
       

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    private boolean userUnique (String username) throws Exception {  //helper function to check if user is unique
        User user = userRepo.findByUsername(username);
        return user == null; 
    }

    public User findUserByUsername(String username) throws Exception {
        return userRepo.findByUsername(username);
    }
    public boolean registerUser(String username, String password, String email, String role) throws Exception {  //register user method
        if (userUnique(username)) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password)); // HASH here
            newUser.setEmail(email);
            newUser.setRole(role);
            System.out.println("user role is: " + newUser.getRole());
            userRepo.AddUserToDataBase(newUser);  //add user to database
            return true;
        }
        return false;
    }
}
