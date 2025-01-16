package com.charity_org.demo.Models.Repository;

import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration,Long> {
    @Query("SELECT r.event FROM EventRegistration r WHERE r.user.id = :userID")
    List<EventRegistration> findRegistrationByUserID(Long userID);

    @Query("SELECT er FROM EventRegistration er WHERE er.user.id = :userId AND er.event.id = :eventId")
    EventRegistration findByUserIdAndEventId(@Param("userId") Long userId, @Param("eventId") Long eventId);
}
