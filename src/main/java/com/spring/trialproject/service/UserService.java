package com.spring.trialproject.service;

import com.spring.trialproject.model.User;
import com.spring.trialproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User saveUserDetails(User user) {
        User user1 = null;
        if (Objects.nonNull(user)) {
            user1 = userRepository.save(user);
        }
        return user1;
    }

    public User getUserDetails(Long id) {
        User user = null;
        if (Objects.nonNull(id)) {
            //user= userRepository.findById(id).get();
            //user= Optional.ofNullable(userRepository.findById(id)).orElse(null).get();
            user = userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
        }
        return user;
    }

    //delete user by id
    public User deleteUser(Long id) {
        User user = null;
        if (Objects.nonNull(id) && userRepository.existsById(id)) {
            user = getUserDetails(id);
            userRepository.deleteById(id);
        }
        return user;
    }

    //update user by id
    public User updateUserDetails(Long id, User user) {
        User updatedUser = null;
        if (Objects.nonNull(id) && userRepository.existsById(id)) {
            updatedUser = userRepository.findById(id).get();
            updatedUser.setId(id);
            updatedUser.setUserName(user.getUserName());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(updatedUser);
        }
        return updatedUser;
    }

    //get user by phone number
    public List<User> getUserDetailsByPhoneNumber(Long phoneNumber) {
        List<User> userList = new ArrayList<>();
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            userList = userRepository.findByPhoneNumber(phoneNumber);
        }
        return userList;
    }

    //get all user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get user by user name
    public List<User> getUserDetailsByUserName(String userName) {
        List<User> userList = new ArrayList<>();
        if (userRepository.existsByUserName(userName)) {
            userList = userRepository.findByUserName(userName);
        }
        return userList;
    }
    //read about response entity
    //git push
}
