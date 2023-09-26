package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.dto.UserDTO;
import com.appfilrouge.projetfilrouge.entities.User;
import com.appfilrouge.projetfilrouge.services.JwtUtils;
import com.appfilrouge.projetfilrouge.services.UserMapper;
import com.appfilrouge.projetfilrouge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;



    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);
        String encodedPassword = passwordEncoder.encode(user.getPassword()); // Encrypt the password
        user.setPassword(encodedPassword); // Set the encrypted password
        User createdUser = userService.addUserWithBuyerSeller(user);
        UserDTO createdUserDTO = userMapper.convertToDTO(createdUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping()
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }
}
