package com.HanglyGroup.HanglyBackend.projections;

import java.time.LocalDate;

public interface UserMinProjection {
    Long getUserId();
    String getName();
    String getEmail();
    String getBiography();
    LocalDate getBirthDate();
    double getRate();
}
