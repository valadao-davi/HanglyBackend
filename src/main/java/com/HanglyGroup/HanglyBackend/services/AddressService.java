package com.HanglyGroup.HanglyBackend.services;

import com.HanglyGroup.HanglyBackend.dto.AddressCreateDTO;
import com.HanglyGroup.HanglyBackend.entities.Address;
import com.HanglyGroup.HanglyBackend.entities.User;
import com.HanglyGroup.HanglyBackend.exceptions.UserNotFoundException;
import com.HanglyGroup.HanglyBackend.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void createAddress(AddressCreateDTO addressCreateDTO) {
         System.out.println(addressCreateDTO.getCity());
         addressRepository.saveAndFlush(new Address(addressCreateDTO));
    }

    @Transactional
    public void editAddress(Address addressEdit, Long addresId){
        Address address = addressRepository.findById(addresId).orElseThrow(UserNotFoundException::new);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(addressEdit, address);
        addressRepository.save(address);
    }
}
