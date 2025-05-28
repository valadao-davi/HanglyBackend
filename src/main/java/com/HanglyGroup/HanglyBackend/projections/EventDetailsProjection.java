package com.HanglyGroup.HanglyBackend.projections;

import com.HanglyGroup.HanglyBackend.entities.Address;

import java.time.LocalDateTime;

public interface EventDetailsProjection {
    Long getEventId();
    String getImgUrl();
    String getName();
    String getDescription();
    String getCategory();
    LocalDateTime getDate();
    Address getAddress();
    Long getUserId();
}
