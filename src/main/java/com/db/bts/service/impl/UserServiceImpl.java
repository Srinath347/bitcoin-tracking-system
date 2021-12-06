package com.db.bts.service.impl;

import com.db.bts.entity.Account;
import com.db.bts.entity.Admin;
import com.db.bts.entity.Membership;
import com.db.bts.entity.User;
import com.db.bts.model.MembershipNameModel;
import com.db.bts.repository.UserRepository;
import com.db.bts.service.MembershipService;
import com.db.bts.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private AccountServiceImpl accountService;

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
            if (user.getTrader() == null) {
                user.setTrader(adminService.findAdminById(1));
            }
            user = userRepository.save(user);
            Account account = new Account();
            account.setUser(user);
            account.setBalance(100f);
            account.setBitcoin(0f);
            accountService.addAccount(account);
            return user;
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

    @Override
    public User userSignIn(String email, String password) throws Exception {
        if(isEmpty(email) || isEmpty(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email or password cannot be empty");
        }
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found for the given email");
        }
        if (!password.equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user credentials");
        }
        return user;
    }

    @Override
    public void updateMembershipStatus(List<Integer> userIds, String status) throws Exception {
        Membership membership = membershipService.findMembershipByName(status);
        try {
        userRepository.updateMembershipStatus(userIds, membership);
        } catch (Exception e) {
            logger.error("user membership update failed with error message: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not update membership status");
        }
    }

    @Override
    public User updateMembershipStatusById(Integer userId, MembershipNameModel status) throws Exception {
        Membership membership = membershipService.findMembershipByName(status.getName());
        try {
            userRepository.updateMembershipStatusById(userId, membership.getId());
            return (userRepository.findById(userId)).get();
        } catch (Exception e) {
            logger.error("user membership update failed with error message: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not update membership status");
        }
    }

    @Override
    public Admin findTraderByUserId(Integer userId) throws Exception {
        return Optional.ofNullable(userRepository.findTraderByUserId(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not find trader details"));
    }

    @Override
    public List<User> findUsersByTraderId(Integer traderId) throws Exception {
        return Optional.ofNullable(userRepository.findUsersByTraderId(traderId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not find trader details"));
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

        return updatedUser;
    }

    private boolean isEmpty(String value) {
        return (value == null || value.length() == 0);
    }
}
