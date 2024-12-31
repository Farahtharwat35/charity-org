package com.charity_org.demo.Models.Repository;
import com.charity_org.demo.Models.Model.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.charity_org.demo.Models.Model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    @Transactional
    @Query("SELECT ur FROM UserRole ur WHERE ur.user.id=:id")
    List<UserRole> getUserRolesByUser(@Param("id") long userId);
}
