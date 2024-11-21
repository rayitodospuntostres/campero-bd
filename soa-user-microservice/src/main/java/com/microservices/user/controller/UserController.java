package com.microservices.user.controller;

import com.microservices.user.models.User;
import com.microservices.user.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }

    @PostMapping("/update")
    public User update(User user){

        User user1 = userService.findById(user.getId_user());
        user1.setName(user.getName());
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        return userService.save(user1);
    }


    @GetMapping("/getUserByUsername/{username}")
    public User getUserByUsername(@PathVariable String username){
        logger.info("Getting user by username: {}", username);
        return userService.findByUsername(username);
    }



    @GetMapping("/example")
    public String example(){
        return "Hello world";
    }
}
