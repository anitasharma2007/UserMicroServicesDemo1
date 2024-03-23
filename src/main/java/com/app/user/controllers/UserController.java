package com.app.user.controllers;

import com.app.user.dao.UserDaoService;
import com.app.user.pojos.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private UserDaoService userService;

    @Autowired
    public UserController(UserDaoService userService){
        this.userService = userService;
    }

    //get all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userService.findAll();
    }

    //get single user
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        return userService.findUser(id);
    }

    //post a user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = userService.save(user);
        // current request => /users/{id} -> user.getId
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        //return uri of new created user , use specific HTTP header - location header
        return ResponseEntity.created(location).build();
    }
}
