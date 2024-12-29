package com.bookish.Bookish.controller;

import com.bookish.Bookish.model.Bookshelf;
import com.bookish.Bookish.model.User;
import com.bookish.Bookish.service.BookshelfService;
import com.bookish.Bookish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookshelf")
public class BookshelfController {
    @Autowired
    private BookshelfService bookshelfService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Bookshelf>> getBookshelfByUser(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        List<Bookshelf> bookshelf = bookshelfService.findByUser(user.get());
        return ResponseEntity.ok(bookshelf);
    }

    @PostMapping()
    public ResponseEntity<Bookshelf> addBooksToBookshelf(@RequestBody Bookshelf bookshelf){
        Bookshelf createdBookshelf = bookshelfService.create(bookshelf);
        return ResponseEntity.ok(createdBookshelf);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookFromBookshelf(@PathVariable Long id){
        Optional<Bookshelf> bookshelf = bookshelfService.findById(id);
        if (bookshelf.isPresent()){
            bookshelfService.delete(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
