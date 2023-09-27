package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.dto.UserDTO;
import com.appfilrouge.projetfilrouge.entities.Role;
import com.appfilrouge.projetfilrouge.entities.User;
import com.appfilrouge.projetfilrouge.repositories.RoleRepository;
import com.appfilrouge.projetfilrouge.services.JwtUtils;
import com.appfilrouge.projetfilrouge.services.UserMapper;
import com.appfilrouge.projetfilrouge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

@CrossOrigin("*")
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

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);
        String encodedPassword = passwordEncoder.encode(user.getPassword()); // Encrypt the password
        user.setPassword(encodedPassword);
        Role roleUser = roleRepository.findByName("ROLE_USER");
        user.setRoleList(Arrays.asList(roleUser));
        User createdUser = userService.addUserWithBuyerSeller(user);
        UserDTO createdUserDTO = userMapper.convertToDTO(createdUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping()
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping(value = "/{userid}")
    public User userFindById(@PathVariable Long userid) {
        return userService.findUserById(userid);
    }

    @GetMapping("/data")
    public User getUserDataTkn() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByMail(username);
        return user;
    }

}
