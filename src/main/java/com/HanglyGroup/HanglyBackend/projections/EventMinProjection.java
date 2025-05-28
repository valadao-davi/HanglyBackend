package com.HanglyGroup.HanglyBackend.projections;

public interface EventMinProjection {
    Long getEventId();
    String getName();
    String getDescription();
    String getImgUrl();
}
