package com.rover.Rover.repository;

import com.rover.Rover.model.GridModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridRepository extends JpaRepository<GridModel, Long> {
}
