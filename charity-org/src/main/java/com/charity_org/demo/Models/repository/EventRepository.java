package com.charity_org.demo.Models.repository;

import com.charity_org.demo.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
