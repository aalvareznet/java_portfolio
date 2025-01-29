package com.rover.Rover.controller;

import com.rover.Rover.model.Rover;
import com.rover.Rover.service.RoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class RoverController {
    @PostMapping("/{directions}")
    public ResponseEntity<Rover> setGame(@RequestBody Rover rover
                                        , @PathVariable String directions){
        RoverService service = new RoverService(rover);
        service.setRover();
        Rover response = service.setRoverMovement(directions);
        return ResponseEntity.ok(response);
    }

}
