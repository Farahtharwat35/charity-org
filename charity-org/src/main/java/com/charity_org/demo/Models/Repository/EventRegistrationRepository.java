package com.charity_org.demo.Models.Repository;

import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration,Long> {
    @Query("SELECT r.event FROM EventRegistration r WHERE r.user.id = :userID")
    List<EventRegistration> findRegistrationByUserID(Long userID);
}
