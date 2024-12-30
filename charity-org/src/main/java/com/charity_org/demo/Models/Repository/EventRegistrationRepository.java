package com.charity_org.demo.Models.Repository;

import com.charity_org.demo.Models.Model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface EventRegistrationRepository extends JpaRepository<EventRegistration,Long> {

    @Query("SELECT r FROM EventRegistration r WHERE r.user.id = :id")
    List<EventRegistration> findRegistrationByUserID(@Param("id") Long id);
}
