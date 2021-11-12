package com.db.bts.service.impl;

import com.db.bts.entity.User;
import com.db.bts.repository.UserRepository;
import com.db.bts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(int userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(Exception::new);
    }

    @Override
    public User save(User user) throws Exception {
        System.out.println("here2");
        return userRepository.save(user);
    }
}
