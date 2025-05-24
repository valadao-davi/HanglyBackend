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

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address findAddress(AddressCreateDTO addressCreateDTO){
        Optional<Long> addressId = addressRepository.findAddressByParams(
                addressCreateDTO.getCep(),
                addressCreateDTO.getCity(),
                addressCreateDTO.getState(),
                addressCreateDTO.getCountry(),
                addressCreateDTO.getStreet(),
                Integer.toString(addressCreateDTO.getNumber()),
                addressCreateDTO.getDistrict()
        );
        if(addressId.isPresent()){
            return addressRepository.findById(addressId.get()).get();
        }
        Address newAddress = new Address(addressCreateDTO);
        return addressRepository.saveAndFlush(newAddress);
    }

    public void createAddress(AddressCreateDTO addressCreateDTO) {
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
