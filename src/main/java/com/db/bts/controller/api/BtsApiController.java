package com.db.bts.controller.api;

import com.db.bts.entity.Admin;
import com.db.bts.entity.User;
import com.db.bts.service.impl.AdminServiceImpl;
import com.db.bts.service.impl.UserServiceImpl;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bts/api")
public class BtsApiController {

    Logger logger = LoggerFactory.getLogger(BtsApiController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping()
    public ResponseEntity<Object> healthCheck() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/user/sign_in")
    public ResponseEntity<User> userSignIn(@RequestParam("email") @NonNull  String email,
                                           @RequestParam("password") @NonNull  String password) throws Exception {
        User user = userService.userSignIn(email, password);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/admin/sign_in")
    public ResponseEntity<Admin> adminSignIn(@RequestParam("email") @NonNull String email,
                                             @RequestParam("password") @NonNull String password) throws Exception {
        Admin admin = adminService.adminSignIn(email, password);
        return ResponseEntity.ok().body(admin);
    }

}
