package com.HanglyGroup.HanglyBackend.entities;


import com.HanglyGroup.HanglyBackend.dto.EventCreateDTO;
import com.HanglyGroup.HanglyBackend.dto.EventEditDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_tab")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String name;
    @Column(length = 1000)
    private String description;
    private LocalDateTime date;
    private String category;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(length = 1000)
    private String imgUrl;

    public Event(){

    }



    public Event(EventEditDTO eventEditDTO){
        this.name = eventEditDTO.name();
        this.description = eventEditDTO.description();
        this.date = eventEditDTO.date();
        this.category = eventEditDTO.category();
        this.imgUrl = eventEditDTO.imgUrl();
    }

    public Long getEventId() {
        return eventId;
    }

    public Event(Long eventId, String name, String description, LocalDateTime date, Address address, User user, String category, String imgUrl) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.date = date;
        this.address = address;
        this.user = user;
        this.category = category;
        this.imgUrl = imgUrl;
    }

    public Event(EventCreateDTO eventCreateDTO) {
        this.name = eventCreateDTO.getName();
        this.description = eventCreateDTO.getDescription();
        this.date = eventCreateDTO.getDate();
        this.category = eventCreateDTO.getCategory();
        this.imgUrl = eventCreateDTO.getImgUrl();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getUserId(){
        return getUser().getUserId();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
