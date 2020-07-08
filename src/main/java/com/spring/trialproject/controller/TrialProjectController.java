package com.spring.trialproject.controller;

import com.spring.trialproject.dto.UserDto;
import com.spring.trialproject.model.User;
import com.spring.trialproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// used to define only rest endpoints.
@RestController
public class TrialProjectController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("addUser")
    public User addUser(@RequestBody UserDto userDto) {
        System.out.println(userDto.toString());
        User user = modelMapper.map(userDto, User.class);
        return userService.saveUserDetails(user);
    }

    @GetMapping("Welcome")
    public String welcomeMessage() {
        return "Hello World";
    }

    @GetMapping("getUserDetails/{id}")
    public User getUserDetails(@PathVariable Long id) {
        return userService.getUserDetails(id);
    }

    @GetMapping("getUserDetailsByPhoneNumber/{phoneNumber}")
    public List<User> getUserDetailsByPhoneNumber(@PathVariable Long phoneNumber) {
        return userService.getUserDetailsByPhoneNumber(phoneNumber);
    }

    @GetMapping("getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("getUserDetailsByUserName/{userName}")
    public List<User> getUserDetailsByUserName(@PathVariable String userName) {
        return userService.getUserDetailsByUserName(userName);
    }

    @DeleteMapping("deleteUser/{id}")
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("updateUserDetails/{id}")
    public User updateUserDetails(@RequestBody UserDto userDto, @PathVariable Long id) {
        User user = modelMapper.map(userDto, User.class);
        return userService.updateUserDetails(id, user);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
