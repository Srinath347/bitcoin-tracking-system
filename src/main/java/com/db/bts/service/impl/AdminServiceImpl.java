package com.db.bts.service.impl;

import com.db.bts.entity.Admin;
import com.db.bts.repository.AdminRepository;
import com.db.bts.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin findAdminById(int adminId) throws Exception {
        Optional<Admin> admin = adminRepository.findById(adminId);
        if (admin.isPresent()) {
            return admin.get();
        } else {
            logger.error("Admin not found for id: {}", adminId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested admin not found");
        }
    }

    @Override
    public Admin addAdmin(Admin admin) throws Exception {
        try {
            return adminRepository.save(admin);
        } catch (Exception e) {
            logger.error("admin sign up failed with error message: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not save admin");
        }
    }

    @Override
    public Admin updateAdmin(int adminId, Admin admin) throws Exception {
        try {
            Admin existingAdmin = findAdminById(adminId);
            admin = validateAndUpdateAdminAttributes(existingAdmin, admin);
            return adminRepository.save(admin);
        } catch (Exception e) {
            logger.error("admin details update failed with error message: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void deleteAdminById(final int adminId) throws Exception {
        adminRepository.deleteById(adminId);
    }

    @Override
    public Admin adminSignIn(String email, String password) throws Exception {
        if(isEmpty(email) || isEmpty(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email or password cannot be empty");
        }
        Admin admin = adminRepository.findAdminByEmail(email);
        if(admin == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "admin not found for the given email");
        }
        if (!password.equals(admin.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user credentials");
        }
        return admin;
    }

    private Admin validateAndUpdateAdminAttributes(Admin existingAdmin, Admin admin) throws Exception {
        Admin updatedAdmin = existingAdmin;
        if(!isEmpty(admin.getEmail()) && !existingAdmin.getEmail().equals(admin.getEmail())) {
            throw new Exception("Could not update admin email");
        }
        if(!isEmpty(admin.getFirstName())) {
            updatedAdmin.setFirstName(admin.getFirstName());
        }
        if(!isEmpty(admin.getLastName())) {
            updatedAdmin.setLastName(admin.getLastName());
        }
        if(!isEmpty(admin.getCellNumber())) {
            updatedAdmin.setCellNumber(admin.getCellNumber());
        }
        if(!isEmpty(admin.getPassword())) {
            updatedAdmin.setPassword(admin.getPassword());
        }
        if(!isEmpty(admin.getPhoneNumber())) {
            updatedAdmin.setPhoneNumber(admin.getPhoneNumber());
        }
        return updatedAdmin;
    }

    private boolean isEmpty(String value) {
        return (value == null || value.length() == 0);
    }
}
