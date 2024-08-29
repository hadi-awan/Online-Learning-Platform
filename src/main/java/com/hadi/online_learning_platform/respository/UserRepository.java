package com.hadi.online_learning_platform.respository;

import com.hadi.online_learning_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /*
    Defining a repository interface to perform database operations
     */
    Optional<User> findByUsername(String username);
}
