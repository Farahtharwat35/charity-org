package com.charity_org.demo.Models.Repository;

import com.charity_org.demo.Models.Model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration,Long> {
}
