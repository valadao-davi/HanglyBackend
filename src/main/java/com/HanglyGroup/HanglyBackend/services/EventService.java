package com.HanglyGroup.HanglyBackend.services;

import com.HanglyGroup.HanglyBackend.dto.AddressCreateDTO;
import com.HanglyGroup.HanglyBackend.dto.EventCreateDTO;
import com.HanglyGroup.HanglyBackend.dto.EventEditDTO;
import com.HanglyGroup.HanglyBackend.entities.Address;
import com.HanglyGroup.HanglyBackend.entities.Event;
import com.HanglyGroup.HanglyBackend.entities.User;
import com.HanglyGroup.HanglyBackend.exceptions.EventNotFoundException;
import com.HanglyGroup.HanglyBackend.exceptions.UserNotFoundException;
import com.HanglyGroup.HanglyBackend.projections.EventDetailsProjection;
import com.HanglyGroup.HanglyBackend.projections.EventMinProjection;
import com.HanglyGroup.HanglyBackend.repositories.AddressRepository;
import com.HanglyGroup.HanglyBackend.repositories.EventRepository;
import com.HanglyGroup.HanglyBackend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public void createEvent(EventCreateDTO eventCreateDTO){
        AddressCreateDTO informedAddress = new AddressCreateDTO(
                eventCreateDTO.getCep(),
                eventCreateDTO.getCity(),
                eventCreateDTO.getCountry(),
                eventCreateDTO.getState(),
                eventCreateDTO.getStreet(),
                eventCreateDTO.getNumber(),
                eventCreateDTO.getDistrict()
        );
        Address address = addressService.findAddress(informedAddress);
        Optional<User> userFounded = userRepository.findById(eventCreateDTO.getUserId());
        Event event = new Event(eventCreateDTO);
        if(userFounded.isPresent()){
            System.out.println("caindo aqui");
            event.setUser(userFounded.get());
            event.setAddress(address);
            eventRepository.saveAndFlush(event);
        }
    }

    public void deleteEvent(Long eventId){
        Event eventFounded = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        eventRepository.delete(eventFounded);
    }

    public void editEvent(Long eventId, EventEditDTO eventEditDTO){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Address oldAddress = addressRepository.findById(eventEditDTO.addressId()).orElseThrow();
        Address newAddress = new Address(eventEditDTO);
        modelMapper.map(newAddress, oldAddress);
        Event oldEvent = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        Event newEvent = new Event(eventEditDTO);
        modelMapper.map(newEvent, oldEvent);
        eventRepository.saveAndFlush(oldEvent);
        addressRepository.saveAndFlush(oldAddress);
    }

    public EventDetailsProjection getEvent(Long eventId){

        return eventRepository.getDetailsEvent(eventId).orElseThrow(EventNotFoundException::new);
    }

    public List<EventMinProjection> getEvents(int page, int items) {
        return eventRepository.getEvents(PageRequest.of(page, items));
    }

    public List<EventMinProjection> getEventsByCategory(String category){
        return eventRepository.getEventsByCategory(category);
    }

}
