package com.rover.Rover.model;

import com.rover.Rover.utils.enums.RoverDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rover {
    Integer posX;
    Integer posY;
    RoverDirection roverDirection;
}
