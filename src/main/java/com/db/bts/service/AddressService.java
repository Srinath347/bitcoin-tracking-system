package com.db.bts.service;

import com.db.bts.entity.User;
import com.db.bts.entity.Address;

public interface AddressService {

    public Address findAddressById(int addressId) throws Exception;

    public Address save(Address address) throws Exception;
}
