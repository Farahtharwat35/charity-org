package com.charity_org.demo.Models.repository;
import com.charity_org.demo.Enums.Roles;
import com.charity_org.demo.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    @Transactional
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User getUserByEmailAndPassword(String email, String password);

    @Modifying
    @Transactional
    @Query("UPDATE User a SET a.isDeleted = true WHERE a.id = :id")
    int deleteUser(@Param("id") Long id);

    @Query("SELECT u FROM User u JOIN u.role r WHERE r = :role AND u.isDeleted= false")
    List<User> findUsersByRole(@Param("role") Roles role);
    @Modifying
    @Transactional
    @Query("UPDATE User a SET a = :user WHERE a.id = :id")
    int updateUserData(@Param("id") Long id, @Param("user") User user);





}