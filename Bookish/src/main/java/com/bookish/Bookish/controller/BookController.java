package com.bookish.Bookish.controller;

import com.bookish.Bookish.model.Book;
import com.bookish.Bookish.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = service.findAll();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = service.findById(id);
        if(book.isPresent()){
            return ResponseEntity.ok(book.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book createdBook = service.create(book);
        return ResponseEntity.ok(createdBook);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails){
        Optional<Book> bookOptional = service.findById(id);
        if (bookOptional.isPresent()){
            Book bookToUpdate = bookOptional.get();
            bookToUpdate.setTitle(bookDetails.getTitle());
            bookToUpdate.setAuthor(bookDetails.getAuthor());
            bookToUpdate.setGenre(bookDetails.getGenre());
            bookToUpdate.setDescription(bookToUpdate.getDescription());
            bookToUpdate.setAverageRating(bookDetails.getAverageRating());
            bookToUpdate.setImageUrl(bookToUpdate.getImageUrl());
            Book updatedBook = service.update(bookToUpdate);
            return ResponseEntity.ok(updatedBook);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        Optional<Book> book = service.findById(id);
        if (book.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre){
        List<Book> books = service.findByGenre(genre);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author){
        List<Book> books = service.findByAuthor(author);
        return ResponseEntity.ok(books);
    }
}
