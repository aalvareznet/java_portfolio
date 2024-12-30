package com.bookish.Bookish.controller;

import com.bookish.Bookish.model.Book;
import com.bookish.Bookish.model.Rating;
import com.bookish.Bookish.model.dto.RatingDto;
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
    public ResponseEntity<List<RatingDto>> getRatingByBook(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        if (book.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<Rating> ratingList = ratingService.findByBook(book.get());

        List<RatingDto> ratingDtoList = ratingList.stream().map(rating -> new RatingDto(
                rating.getUser().getId(),
                rating.getBook().getId(),
                rating.getRating(),
                rating.getRatingDescription()
        )).toList();
        return ResponseEntity.ok(ratingDtoList);
    }

    @PostMapping()
    public ResponseEntity<RatingDto> createRating(@RequestBody Rating rating){
        Rating createdRating = ratingService.create(rating);
        RatingDto ratingDto = new RatingDto(rating.getUser().getId()
                                , rating.getBook().getId()
                                , rating.getRating()
                                , rating.getRatingDescription());
        return ResponseEntity.ok(ratingDto);
    }

}
