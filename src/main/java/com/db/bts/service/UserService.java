package com.db.bts.service;

import com.db.bts.entity.User;

public interface UserService {

    public User findUserById(int userId) throws Exception;

    public User save(User user) throws Exception;
}
