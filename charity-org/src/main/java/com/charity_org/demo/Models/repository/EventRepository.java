package com.charity_org.demo.Models.repository;
import com.charity_org.demo.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface EventRepository extends JpaRepository<Event,Long> {

    @Query("SELECT COUNT(e) FROM Event e")
    long countEvents();


}
