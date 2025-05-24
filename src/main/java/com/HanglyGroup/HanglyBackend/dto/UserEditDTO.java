package com.HanglyGroup.HanglyBackend.dto;

import java.time.LocalDate;

public record UserEditDTO(String name, String email, String password, String imgUrl, String biography, LocalDate birthDate) {
}
