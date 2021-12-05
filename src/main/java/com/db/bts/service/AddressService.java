package com.db.bts.service;

import com.db.bts.entity.Address;
import com.db.bts.model.AddressModel;

import java.util.List;

public interface AddressService {

    public Address findAddressById(int addressId) throws Exception;

    public Address save(AddressModel addressModel) throws Exception;
    
    public Address findAddressByUserId(int userId) throws Exception;

    public List<Integer> findUserIdByStreetAddress(String streetAddress) throws Exception;

    public List<Integer> findUserIdByCity(String city) throws Exception;
}
