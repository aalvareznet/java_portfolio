package com.bookish.Bookish.repository;

import com.bookish.Bookish.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepo extends JpaRepository<Rating, Long> {
}
