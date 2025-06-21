package com.HanglyGroup.HanglyBackend.repositories;

import com.HanglyGroup.HanglyBackend.entities.Event;
import com.HanglyGroup.HanglyBackend.projections.EventDetailsProjection;
import com.HanglyGroup.HanglyBackend.projections.EventMinProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.eventId = :eventId")
    Optional<EventDetailsProjection> getDetailsEvent(Long eventId);

    @Query("SELECT e FROM Event e WHERE e.category = :category")
    List<EventMinProjection> getEventsByCategory(String category);

    @Query("SELECT e FROM Event e")
    List<EventMinProjection> getEvents(Pageable pageable);
}
