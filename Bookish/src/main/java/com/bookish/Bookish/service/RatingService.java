package com.bookish.Bookish.service;

import com.bookish.Bookish.model.Rating;
import com.bookish.Bookish.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService extends BaseService<Rating, Long>{
    @Autowired
    private RatingRepo repo;

    @Override
    protected RatingRepo getRepository() {
        return repo;
    }
}
