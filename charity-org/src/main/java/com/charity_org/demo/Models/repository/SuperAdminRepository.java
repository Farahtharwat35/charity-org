package com.charity_org.demo.Models.repository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuperAdminRepository extends JpaRepository<SuperAdminRepository,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE SuperAdmin a SET a.name = :name WHERE a.id = :id")
    int updateSuperAdminNameById(@Param("id") Long id, @Param("name") String name);

}
