package com.charity_org.demo.Models.repository;

import com.charity_org.demo.Models.Assigments;
import com.charity_org.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssigmentRepository extends JpaRepository<Assigments, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Assigments a SET a.isDeleted = true WHERE a.id = :id")
    boolean deleteAssigment(@Param("id") Long id);

    @Query("SELECT a FROM Assigments a WHERE a.courier.id = :courier")
    List<Assigments> getAllByCourier(@Param("courier") long courier);
}
