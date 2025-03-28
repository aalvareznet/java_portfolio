package com.bookish.Bookish.repository;

import com.bookish.Bookish.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByGenre(String genre);
    List<Book> findByAuthor(String author);
}
