package com.charity_org.demo.Models.repository;

import com.charity_org.demo.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface EventRepository extends JpaRepository<Event,Long> {
    @Modifying
    @Query("UPDATE Event e SET e.isDeleted = true WHERE e.id = :id")
    boolean deleteEvent(@Param("id") Long id);

    @Query("SELECT e FROM Event e WHERE e.isDeleted = false")
    ArrayList<Event> findAll();
}
