package com.app.user.controllers;

import com.app.user.dao.UserDaoService;
import com.app.user.exception.UserNotFoundException;
import com.app.user.pojos.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDaoService userService;
//    @Autowired
//    public UserController(MessageSource messageSource){
//        this.messageSource=messageSource;
//    }
//
//    private UserDaoService userService;
//
//    //@Autowired
//    public UserController(UserDaoService userService){
//        this.userService = userService;
//    }

    //get all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userService.findAll();
    }

    //get single user
    //hateoas implementation
    //EntityModel
    //WebMvcLinkerBuilder
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = userService.findUser(id);
        if (user == null)
            throw new UserNotFoundException("id : " + id);

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
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

    //hello world internalization
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)
    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default message", locale);
        //return "Hello World";
    }







}
