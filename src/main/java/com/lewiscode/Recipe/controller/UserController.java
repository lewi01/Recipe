package com.lewiscode.Recipe.controller;

import com.lewiscode.Recipe.entity.User;
import com.lewiscode.Recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/register")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user){
        Optional<User> user1 = userService.getUserByEmail(user.getEmail());

        if (user1.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
}
