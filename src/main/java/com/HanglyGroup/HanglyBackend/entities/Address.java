package com.HanglyGroup.HanglyBackend.entities;

import com.HanglyGroup.HanglyBackend.dto.AddressCreateDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "address_info_tab")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;

    private String place;
    private int number;
    private String district;
    private String city;
    private String state;
    private String country;
    private String cep;

    public Address(){}

    public Address(String place, int number, String district, String city, String state, String country, String cep) {
        this.place = place;
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
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
