package com.db.bts.controller;

import com.db.bts.entity.Admin;
import com.db.bts.service.impl.AdminServiceImpl;
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
public class AdminsController {

    Logger logger = LoggerFactory.getLogger(AdminsController.class);

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/admin/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable(value = "id") int adminId) throws Exception {
        logger.info("GET request for admin with id {}", adminId);
        Admin admin = adminService.findAdminById(adminId);
        logger.info("admin details : {}", admin);
        return ResponseEntity.ok().body(admin);
    }

    @PostMapping("/admin")
    public ResponseEntity<Admin> addAdmin(@RequestBody @NonNull Admin admin) throws Exception {
        logger.info("POST request for user with details: {}", admin);
        Admin savedAdmin = adminService.addAdmin(admin);
        logger.info("Admin with id: [{}] signed up successfully", savedAdmin.getId());
        return ResponseEntity.ok().body(savedAdmin);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable(value = "id") int adminId,
                                             @RequestBody @NonNull Admin admin) throws Exception {
        logger.info("PUT request for admin with id {}", adminId);
        Admin updatedAdmin = adminService.updateAdmin(adminId, admin);
        logger.info("Admin details updated successfully");
        return ResponseEntity.ok().body(updatedAdmin);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity deleteAdmin(@PathVariable(value = "id") int adminId) throws Exception {
        try {
            logger.info("DELETE request for admin with id: [{}]", adminId);
            adminService.deleteAdminById(adminId);
            logger.info("admin with id: [{}] deleted successfully", adminId);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete admin");
        }
    }
}
