package com.codehows.cardatabase.domain.repository;

import com.codehows.cardatabase.domain.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
