package com.fpt.repository;

import com.fpt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String name);

    @Query("select h from User as h where h.status = :status")
    List<User> findActiveUser(@Param("status") String status);
}
