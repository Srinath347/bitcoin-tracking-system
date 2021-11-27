package com.db.bts.service.impl;

import com.db.bts.entity.Address;
import com.db.bts.mapper.AddressModelMapper;
import com.db.bts.model.AddressModel;
import com.db.bts.repository.AddressRepository;
import com.db.bts.service.AddressService;
import com.db.bts.service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    UserService userService;

    @Autowired
    AddressModelMapper addressModelMapper;

    @Override
    public Address findAddressById(int addressId) throws Exception{
        return addressRepository.findById(addressId).orElseThrow(Exception::new);
    }

    @Override
    public Address save(AddressModel addressModel) throws Exception{
        System.out.println("City is "+ addressModel.getCity() + addressModel.getUserId() + addressModel);
        if(isEmpty(addressModel.getCity())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City cannot be empty");
        }
        if(isEmpty(addressModel.getState())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "State cannot be empty");
        }
        if(isEmpty(addressModel.getStreet())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Street cannot be empty");
        }
        if(addressModel.getZipcode() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zipcode cannot be empty");
        }
        if(addressModel.getUserId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserId cannot be empty");
        }
        Address address = addressModelMapper.mapAddressModelToAddress(addressModel);
        return addressRepository.save(address);
    }

    private boolean isEmpty(String value) {
        return (value == null || value.length() == 0);
    }

	@Override
	public Address findAddressByUserId(int userId) throws Exception {
		return Optional.ofNullable(addressRepository.findAddressByUserId(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not fetch address details"));
		
	}
}