package com.HanglyGroup.HanglyBackend.entities;

import com.HanglyGroup.HanglyBackend.dto.AddressCreateDTO;
import com.HanglyGroup.HanglyBackend.dto.EventEditDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "address_info_tab")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String street;
    private int number;
    private String district;
    private String city;
    private String state;
    private String country;
    private String cep;

    public Address(){}

    public Address(EventEditDTO eventEditDTO){
        this.cep = eventEditDTO.cep();
        this.city = eventEditDTO.city();
        this.state = eventEditDTO.state();
        this.country = eventEditDTO.country();
        this.street = eventEditDTO.street();
        this.number = eventEditDTO.number();
        this.district = eventEditDTO.district();
    }

    public Address(String street, int number, String district, String city, String state, String country, String cep) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
        this.cep = cep;

    }

    public Address(AddressCreateDTO addressCreateDTO){
        this.cep = addressCreateDTO.getCep();
        this.city = addressCreateDTO.getCity();
        this.state = addressCreateDTO.getState();
        this.country = addressCreateDTO.getCountry();
        this.street = addressCreateDTO.getStreet();
        this.number = addressCreateDTO.getNumber();
        this.district = addressCreateDTO.getDistrict();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
