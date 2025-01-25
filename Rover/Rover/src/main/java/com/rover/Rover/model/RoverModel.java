package com.rover.Rover.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class RoverModel {
    Long id;
    Integer x;
    Integer y;
    Character direction;
}
