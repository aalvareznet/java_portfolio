package com.rover.Rover.service;

import com.rover.Rover.model.GridModel;
import com.rover.Rover.repository.GridRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GridService extends BaseService<GridModel, Long>{
    private final GridRepository gridRepository;

    @Override
    protected GridRepository getRepository() {
        return gridRepository;
    }
}
