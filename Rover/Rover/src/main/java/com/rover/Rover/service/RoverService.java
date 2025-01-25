package com.rover.Rover.service;

import com.rover.Rover.model.RoverModel;
import com.rover.Rover.repository.RoverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoverService extends BaseService<RoverModel, Long>{
    private final RoverRepository roverRepository;
    @Override
    protected RoverRepository getRepository() {
        return roverRepository;
    }
}
