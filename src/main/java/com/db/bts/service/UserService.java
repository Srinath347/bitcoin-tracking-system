package com.db.bts.service;

import com.db.bts.entity.User;

public interface UserService {

    public User findUserById(int userId) throws Exception;

    public User addUser(User user) throws Exception;

    public User updateUser(int userId, User user) throws Exception;

    public void deleteUserById(int userId) throws Exception;

    public User userSignIn(String email, String password) throws Exception;
}
