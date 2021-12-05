package com.db.bts.service;

import com.db.bts.entity.User;
import com.db.bts.model.MembershipNameModel;

import java.util.List;

public interface UserService {

    public User findUserById(int userId) throws Exception;

    public User addUser(User user) throws Exception;

    public User updateUser(int userId, User user) throws Exception;

    public void deleteUserById(int userId) throws Exception;

    public User userSignIn(String email, String password) throws Exception;

    public void updateMembershipStatus(List<Integer> userIds, String status) throws Exception;

    public User updateMembershipStatusById(Integer userId, MembershipNameModel status) throws Exception;

    public Integer findTraderByUserId(Integer userId) throws Exception;
}
