package com.db.bts.service;

import com.db.bts.entity.Admin;

public interface AdminService {

    public Admin findAdminById(int adminId) throws Exception;

    public Admin addAdmin(Admin admin) throws Exception;

    public Admin updateAdmin(int adminId, Admin admin) throws Exception;

    public void deleteAdminById(int adminId) throws Exception;

    public Admin adminSignIn(String email, String password) throws Exception;
}
