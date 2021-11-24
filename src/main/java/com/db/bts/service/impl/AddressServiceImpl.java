package com.db.bts.service.impl;

import com.db.bts.entity.Address;
import com.db.bts.repository.AddressRepository;
import com.db.bts.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address findAddressById(int addressId) throws Exception{
        return addressRepository.findById(addressId).orElseThrow(Exception::new);
    }

    @Override
    public Address save(Address address) throws Exception{
        return addressRepository.save(address);
    }
}