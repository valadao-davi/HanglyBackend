package com.HanglyGroup.HanglyBackend.dto;

import java.time.LocalDateTime;

public record EventEditDTO(
        Long addressId,
        String name,
        String description,
        String category,
        LocalDateTime date,
        String cep,
        String city,
        String state,
        String country,
        String street,
        int number,
        String district,
        String imgUrl) {
}
