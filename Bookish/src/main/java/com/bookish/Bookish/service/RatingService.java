package com.bookish.Bookish.service;

import com.bookish.Bookish.model.Book;
import com.bookish.Bookish.model.Rating;
import com.bookish.Bookish.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService extends BaseService<Rating, Long>{
    @Autowired
    private RatingRepo repo;

    @Override
    protected RatingRepo getRepository() {
        return repo;
    }

    public List<Rating> findByBook(Book book){
        return repo.findByBook(book);
    }
}
