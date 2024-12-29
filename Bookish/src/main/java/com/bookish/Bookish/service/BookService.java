package com.bookish.Bookish.service;

import com.bookish.Bookish.model.Book;
import com.bookish.Bookish.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService extends BaseService<Book, Long>{
    @Autowired
    private BookRepo repo;

    @Override
    protected BookRepo getRepository() {
        return repo;
    }

    public List<Book> findByGenre(String genre){
        return repo.findByGenre(genre);
    }
}
