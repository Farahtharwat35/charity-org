package com.charity_org.demo.Middlware.cookies;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, String> {

    @Transactional
    @Query("SELECT s FROM Session s WHERE s.sessionId = :value")
    Session findBySessionId(String value);
}