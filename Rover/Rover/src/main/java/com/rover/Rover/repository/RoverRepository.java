package com.rover.Rover.repository;

import com.rover.Rover.model.RoverModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoverRepository extends JpaRepository<RoverModel, Long> {
}
