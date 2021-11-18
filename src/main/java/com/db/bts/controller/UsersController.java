package com.db.bts.controller;

import com.db.bts.entity.User;
import com.db.bts.service.impl.UserServiceImpl;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bts")
public class UsersController {

    Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) throws Exception {
        logger.info("GET request for user with id {}", userId);
        User user = userService.findUserById(userId);
        logger.info("user details : {}", user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody @NonNull User user) throws Exception {
        logger.info("POST request for user with details: {}", user);
        User savedUser = userService.addUser(user);
        logger.info("User with id: [{}] signed up successfully", savedUser.getId());
        return ResponseEntity.ok().body(savedUser);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId,
                                           @RequestBody @NonNull User user) throws Exception {
        logger.info("PUT request for user with id: [{}]", userId);
        User updatedUser = userService.updateUser(userId, user);
        logger.info("User details updated successfully");
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") int userId) throws Exception {
        try {
            logger.info("DELETE request for user with id: [{}]", userId);
            userService.deleteUserById(userId);
            logger.info("user with id: [{}] deleted successfully", userId);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete user");
        }
    }
}
