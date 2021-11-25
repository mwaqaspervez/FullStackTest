package com.demo.assessment.repository;

import com.demo.assessment.model.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findByUsernameAndPassword(String username, String password);
}