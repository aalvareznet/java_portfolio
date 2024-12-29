package com.bookish.Bookish.controller;

import com.bookish.Bookish.model.Book;
import com.bookish.Bookish.model.Rating;
import com.bookish.Bookish.service.BookService;
import com.bookish.Bookish.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Rating>> getRatingByBook(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        List<Rating> ratingList = ratingService.findByBook(book.get());
        return ResponseEntity.ok(ratingList);
    }

    @PostMapping()
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating createdRating = ratingService.create(rating);
        return ResponseEntity.ok(rating);
    }

}
