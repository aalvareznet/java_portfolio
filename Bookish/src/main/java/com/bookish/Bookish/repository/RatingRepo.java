package com.bookish.Bookish.repository;

import com.bookish.Bookish.model.Book;
import com.bookish.Bookish.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long> {
    List<Rating> findByBook(Book book);
}
