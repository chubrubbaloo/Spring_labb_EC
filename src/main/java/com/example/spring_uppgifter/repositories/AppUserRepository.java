package com.example.spring_uppgifter.repositories;

import com.example.spring_uppgifter.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository <AppUser, Integer> {
}
