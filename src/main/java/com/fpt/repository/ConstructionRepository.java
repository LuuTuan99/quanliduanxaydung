package com.fpt.repository;

import com.fpt.entity.Construction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConstructionRepository extends JpaRepository<Construction, Long> {
    List<Construction> findByName(String name);

    @Query("select h from Construction as h where h.status = :status")
    List<Construction> findActiveConstruction(@Param("status") int status);
}
