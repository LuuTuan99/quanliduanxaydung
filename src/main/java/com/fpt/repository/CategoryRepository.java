package com.fpt.repository;

import com.fpt.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);

    @Query("select h from Category as h where h.status = :status")
    List<Category> findActiveCategory(@Param("status") int status);
}
