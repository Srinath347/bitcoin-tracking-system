package com.db.bts.controller;

import com.db.bts.entity.User;
import com.db.bts.model.UserModel;
import com.db.bts.service.impl.UserServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bts")
public class UsersController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping()
    public void print() {
        System.out.println("OK");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) throws Exception {
        User user = userService.findUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody @NonNull User user) throws Exception {
        User user1 = userService.save(user);
        return ResponseEntity.ok().body(user1);
    }
}
