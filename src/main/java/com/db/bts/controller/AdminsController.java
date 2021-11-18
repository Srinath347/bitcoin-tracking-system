package com.db.bts.controller;

import com.db.bts.entity.Admin;
import com.db.bts.service.impl.AdminServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bts")
public class AdminsController {

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/admin/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable(value = "id") int adminId) throws Exception {
        Admin admin = adminService.findAdminById(adminId);
        return ResponseEntity.ok().body(admin);
    }

    @PostMapping("/admin")
    public ResponseEntity<Admin> addAdmin(@RequestBody @NonNull Admin admin) throws Exception {
        Admin savedAdmin = adminService.addAdmin(admin);
        return ResponseEntity.ok().body(savedAdmin);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable(value = "id") int adminId,
                                            @RequestBody @NonNull Admin admin) throws Exception {
        Admin updatedAdmin = adminService.updateAdmin(adminId, admin);
        return ResponseEntity.ok().body(updatedAdmin);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity deleteAdmin(@PathVariable(value = "id") int adminId) throws Exception {
        try {
            adminService.deleteAdminById(adminId);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete admin");
        }
    }
}
