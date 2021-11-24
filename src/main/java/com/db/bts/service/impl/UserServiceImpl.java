package com.db.bts.service.impl;

import com.db.bts.entity.User;
import com.db.bts.repository.UserRepository;
import com.db.bts.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(final int userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            logger.error("User not found for id: {}", userId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested user not found");
        }
    }

    @Override
    public User addUser(User user) throws Exception {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("user sign up failed with error message: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not save user");
        }
    }

    @Override
    public User updateUser(int userId, User user) throws Exception {
        try {
            User existingUser = findUserById(userId);
            user = validateAndUpdateUserAttributes(existingUser, user);
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("user details update failed with error message: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void deleteUserById(int userId) throws Exception {
        userRepository.deleteById(userId);
    }

    private User validateAndUpdateUserAttributes(User existingUser, User user) throws Exception {
        User updatedUser = existingUser;
        if(!isEmpty(user.getEmail()) && !existingUser.getEmail().equals(user.getEmail())) {
            throw new Exception("Could not update email of user");
        }
        if(!isEmpty(user.getFirstName())) {
            updatedUser.setFirstName(user.getFirstName());
        }
        if(!isEmpty(user.getLastName())) {
            updatedUser.setLastName(user.getLastName());
        }
        if(!isEmpty(user.getCellNumber())) {
            updatedUser.setCellNumber(user.getCellNumber());
        }
        if(!isEmpty(user.getPassword())) {
            updatedUser.setPassword(user.getPassword());
        }
        if(!isEmpty(user.getPhoneNumber())) {
            updatedUser.setPhoneNumber(user.getPhoneNumber());
        }
        if(user.getMemberId() != null) {
            updatedUser.setMemberId(user.getMemberId());
        }

        return updatedUser;
    }

    private boolean isEmpty(String value) {
        return (value == null || value.length() == 0);
    }
}
