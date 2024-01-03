package com.denizyercel.springboot.controller;


import com.denizyercel.springboot.entity.User;
import com.denizyercel.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> userCreate(@RequestBody User user){
        User userSaved = userService.createUser(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,@RequestBody User user){
        user.setId(userId);
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted",HttpStatus.OK);
    }
}
