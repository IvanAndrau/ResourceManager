package org.example.resourcemanagerwebtask.Repositories;

import org.example.resourcemanagerwebtask.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u.role FROM User u WHERE u.username = :username")
    String findRoleByUsername(String username);
}