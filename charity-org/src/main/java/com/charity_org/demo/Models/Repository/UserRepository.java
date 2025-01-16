package com.charity_org.demo.Models.Repository;
import com.charity_org.demo.Models.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Modifying
    @Transactional
    @Query("UPDATE User a SET a = :user WHERE a.id = :id")
    int updateUserData(@Param("id") Long id, @Param("user") User user);

}