package com.db.bts.service;

import com.db.bts.entity.Address;
import com.db.bts.model.AddressModel;

public interface AddressService {

    public Address findAddressById(int addressId) throws Exception;

    public Address save(AddressModel addressModel) throws Exception;
}
