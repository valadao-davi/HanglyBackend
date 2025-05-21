package com.HanglyGroup.HanglyBackend.controllers;

import com.HanglyGroup.HanglyBackend.dto.AddressCreateDTO;
import com.HanglyGroup.HanglyBackend.entities.Address;
import com.HanglyGroup.HanglyBackend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<String> registerAddress(@RequestBody AddressCreateDTO addressCreateDTO){
        addressService.createAddress(addressCreateDTO);
        return ResponseEntity.ok("Success");
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<String> editAddress(@RequestBody Address address, @PathVariable Long id) {
        addressService.editAddress(address, id);
        return ResponseEntity.ok("Success");
    }
}
