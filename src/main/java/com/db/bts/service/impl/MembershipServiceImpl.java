package com.db.bts.service.impl;

import com.db.bts.entity.Membership;
import com.db.bts.repository.MembershipRepository;
import com.db.bts.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public Membership findMembershipByName(String name) {
        return Optional.ofNullable(membershipRepository.findByName(name))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not fetch membership details"));
    }
}
