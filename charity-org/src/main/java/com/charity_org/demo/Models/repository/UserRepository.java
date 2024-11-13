package com.charity_org.demo.Models.repository;
import com.charity_org.demo.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE User a SET a.name = :name WHERE a.id = :id")
    int updateUserNameById(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Transactional
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("SELECT u FROM User u WHERE u.password = :password")
    User getUserByPassword(@Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User getUserByEmailAndPassword(String email, String password);

    @Modifying
    @Query("UPDATE User a SET a.isDeleted = true WHERE a.id = :id")
    boolean deleteUser(@Param("id") Long id);
}