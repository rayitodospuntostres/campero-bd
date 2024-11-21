package com.microservices.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservices.user.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    User findByUsername(String username);
    Boolean existsByUsername(String username);
}
