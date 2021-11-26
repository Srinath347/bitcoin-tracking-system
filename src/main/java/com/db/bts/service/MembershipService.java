package com.db.bts.service;

import com.db.bts.entity.Membership;

public interface MembershipService {

    Membership findMembershipByName(String name);
}
