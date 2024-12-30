package com.bookish.Bookish.service;

import com.bookish.Bookish.model.Book;
import com.bookish.Bookish.model.Rating;
import com.bookish.Bookish.repository.BookRepo;
import com.bookish.Bookish.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends BaseService<Book, Long>{
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private RatingRepo ratingRepository;

    @Override
    protected BookRepo getRepository() {
        return bookRepo;
    }

    public List<Book> findByGenre(String genre){
        return bookRepo.findByGenre(genre);
    }

    public List<Book> findByAuthor(String author) { return bookRepo.findByAuthor(author); }

    public void updateAverageRating(Book book) {
        List<Rating> ratings = ratingRepository.findByBook(book);
        double average = ratings.stream()
                .mapToInt(Rating::getRating)
                .average()
                .orElse(0.0);
        book.setAverageRating(average);
        bookRepo.save(book);
    }
}
