package com.db.bts.controller;

import com.db.bts.entity.Address;
import com.db.bts.model.AddressModel;
import com.db.bts.service.impl.AddressServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bts")

public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressbyId(@PathVariable(value = "id") int addressId) throws Exception{
        Address address= addressService.findAddressById(addressId);
        return ResponseEntity.ok().body(address);
    }

    @PostMapping("/address")
    public ResponseEntity<Address> save(@RequestBody @NonNull Address address) throws Exception{
        Address address1=addressService.save(address);
        return ResponseEntity.ok().body(address1);
    }
}
