package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.dto.UserDTO;
import com.appfilrouge.projetfilrouge.entities.Buyer;
import com.appfilrouge.projetfilrouge.entities.User;
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

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/api/users")
    public void addUserWithBuyerSeller(@RequestBody User user) {
        userService.addUserWithBuyerSeller(user);
    }

    @PostMapping("/api/createUser")
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);
        User createdUser = userService.addUserWithBuyerSeller(user);
        UserDTO createdUserDTO = userMapper.convertToDTO(createdUser);




        return new ResponseEntity<>( HttpStatus.CREATED);

    }


    @RequestMapping("api/users")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }
}
