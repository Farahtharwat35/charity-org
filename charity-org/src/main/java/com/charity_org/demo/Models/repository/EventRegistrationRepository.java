package com.charity_org.demo.Models.repository;

import com.charity_org.demo.Models.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration,Long> {
}
