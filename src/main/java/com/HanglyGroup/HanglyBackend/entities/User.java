package com.HanglyGroup.HanglyBackend.entities;

import com.HanglyGroup.HanglyBackend.dto.UserCreateDTO;
import com.HanglyGroup.HanglyBackend.dto.UserEditDTO;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "user_tab")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    @Column(length = 4000)
    private String biography;
    private double rate;
    @Column(length = 1000)
    private String imgUrl;

    public User(){}

    public User(UserEditDTO userEditDTO){
        this.name = userEditDTO.name();
        this.password = userEditDTO.password();
        this.imgUrl = userEditDTO.imgUrl();
        this.biography = userEditDTO.biography();
        this.birthDate = userEditDTO.birthDate();
    }

    public User(String name, String password, LocalDate birthDate, String biography, String imgUrl){
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.biography = biography;
        this.imgUrl = imgUrl;
    }


    public User(UserCreateDTO userCreateDTO) {
        this.name = userCreateDTO.getName();
        this.email = userCreateDTO.getEmail();
        this.password = userCreateDTO.getPassword();
        this.imgUrl = userCreateDTO.getImgUrl();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
