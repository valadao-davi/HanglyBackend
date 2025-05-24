package com.HanglyGroup.HanglyBackend.dto;

public class AddressCreateDTO {
    private String cep;
    private String city;
    private String state;
    private String country;
    private String street;
    private int number;
    private String district;

    public AddressCreateDTO(){

    }

    public AddressCreateDTO(String cep, String city, String country, String state, String street, int number, String district) {
        this.cep = cep;
        this.city = city;
        this.country = country;
        this.state = state;
        this.street = street;
        this.number = number;
        this.district = district;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
