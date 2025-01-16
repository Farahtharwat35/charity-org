package com.charity_org.demo.Models.Repository;
import com.charity_org.demo.Models.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EventRepository extends JpaRepository<Event,Long> {

    @Query("SELECT COUNT(e) FROM Event e")
    long countEvents();

    @Modifying
    @Transactional
    @Query("UPDATE Event a SET a.isDeleted = true , a.status = 'CANCELLED' WHERE a.id = :id")
    int deleteEventById(@Param("id") Long id);
}
