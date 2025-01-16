package com.charity_org.demo.Models.Repository;
import com.charity_org.demo.Models.Model.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Transactional
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role findByName(String name);

    @Transactional
    @Query("SELECT r.name FROM Role r WHERE r.id = :id")
    String getRoleNameById(Long id);
}
