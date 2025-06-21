package com.HanglyGroup.HanglyBackend.controllers;

import com.HanglyGroup.HanglyBackend.dto.EventCreateDTO;
import com.HanglyGroup.HanglyBackend.dto.EventEditDTO;
import com.HanglyGroup.HanglyBackend.entities.Event;
import com.HanglyGroup.HanglyBackend.projections.EventDetailsProjection;
import com.HanglyGroup.HanglyBackend.projections.EventMinProjection;
import com.HanglyGroup.HanglyBackend.projections.UserMinProjection;
import com.HanglyGroup.HanglyBackend.services.EventService;
import com.HanglyGroup.HanglyBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping(value = "{id}")
    public ResponseEntity<EventDetailsProjection> getDetailEvent(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<EventMinProjection>> getEventsByCategory(String category){
        return ResponseEntity.ok(eventService.getEventsByCategory(category));
    }

    @GetMapping
    public ResponseEntity<List<EventMinProjection>> getEvents(){
        return ResponseEntity.ok(eventService.getEvents());
    }

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventCreateDTO eventCreateDTO){
        UserMinProjection userMinProjection = userService.getProfile();
        eventCreateDTO.setUserId(userMinProjection.getUserId());
        eventService.createEvent(eventCreateDTO);
        return ResponseEntity.ok("Event created");
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<String> editEvent(@RequestBody EventEditDTO eventEditDTO, @PathVariable Long id){
        eventService.editEvent(id, eventEditDTO);
        return ResponseEntity.ok("Event edited");
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted");
    }
}
