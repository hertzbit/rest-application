package com.hertzbit.restapplication.controller;

import com.hertzbit.restapplication.model.User;
import com.hertzbit.restapplication.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping ("/api/users")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping (produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers (HttpServletRequest request, HttpServletResponse response) {

        Enumeration<String> headerEnumerator = request.getHeaderNames();
        while (headerEnumerator.hasMoreElements()) {
            System.out.println(headerEnumerator.nextElement());
        }
        List<User> userList = this.userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping (value = "/{userId}", produces = "application/json")
    public ResponseEntity<?> getRequestedUser(@PathVariable("userId") Integer userId) {

        User userFromDatabase = this.userService.getSpecificUser(userId);
        return new ResponseEntity<>(userFromDatabase, HttpStatus.OK);
    }

    @PostMapping (produces = "application/json")
    public ResponseEntity<?> addUserToDatabase (@RequestBody User user) {
        LOGGER.info("Request Received : {}", user);
        User savedUserWithUserID = this.userService.createUser(user);
        return new ResponseEntity<>(savedUserWithUserID, HttpStatus.CREATED);
    }

    @PutMapping (value = "/{userId}", produces = "application/json")
    public ResponseEntity<?> updateExistingUser (@PathVariable("userId") Integer userId, @RequestBody User user) {

        User updatedUser = this.userService.updateUser(userId, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping (value = "/{userId}")
    public ResponseEntity<?> deleteUserFromDatabase (@PathVariable("userId") Integer userId) {

        this.userService.deleteUser(userId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
