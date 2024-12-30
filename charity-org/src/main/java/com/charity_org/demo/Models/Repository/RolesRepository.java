package com.charity_org.demo.Models.Repository;

import com.charity_org.demo.Enums.Roles;
import com.charity_org.demo.Models.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolesRepository extends JpaRepository<Role,Long> {

    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role findByName(String name);
}
