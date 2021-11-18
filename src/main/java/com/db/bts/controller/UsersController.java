package com.db.bts.controller;

import com.db.bts.entity.User;
import com.db.bts.service.impl.UserServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bts")
public class UsersController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) throws Exception {
        User user = userService.findUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody @NonNull User user) throws Exception {
        User savedUser = userService.addUser(user);
        return ResponseEntity.ok().body(savedUser);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId,
                                           @RequestBody @NonNull User user) throws Exception {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") int userId) throws Exception {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete user");
        }
    }
}
