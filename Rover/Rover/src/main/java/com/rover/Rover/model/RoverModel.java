package com.rover.Rover.model;

import com.rover.Rover.model.enums.Cardinal;
import com.rover.Rover.model.enums.Direction;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    Direction direction;
    @Enumerated(EnumType.STRING)
    Cardinal cardinal;
}
