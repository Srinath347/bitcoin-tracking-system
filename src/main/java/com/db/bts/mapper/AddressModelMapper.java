package com.db.bts.mapper;

import com.db.bts.entity.Address;
import com.db.bts.entity.User;
import com.db.bts.model.AddressModel;
import com.db.bts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressModelMapper {

    @Autowired
    UserService userService;

    public Address mapAddressModelToAddress(AddressModel addressModel) throws Exception{
        Address address = new Address();
        address.setCity(addressModel.getCity());
        address.setState(addressModel.getState());
        address.setStreet(addressModel.getStreet());
        address.setZipcode(addressModel.getZipcode());
        User user = userService.findUserById(addressModel.getUserId());
        address.setUser(user);
        return address;
    }
}
