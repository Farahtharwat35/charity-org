package com.charity_org.demo.Models.Repository;
import com.charity_org.demo.Models.Model.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    @Transactional
    @Query("SELECT ur FROM UserRole ur WHERE ur.user.id=:id")
    List<UserRole> getUserRolesByUser(@Param("id") long userId);

    @Query("SELECT ur FROM UserRole ur WHERE ur.role.id=:id AND ur.isDeleted = false ")
    List<UserRole> getUserRolesByRole(@Param("id") long roleId );

    @Modifying
    @Transactional
    @Query("UPDATE UserRole ur SET ur.isDeleted = true WHERE ur.user.id = :id AND ur.role.name = :name")
    int deleteUserRoleByUserIdAndRole(@Param("id") long userId, @Param("name") String name);
}
