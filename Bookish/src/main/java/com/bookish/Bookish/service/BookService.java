package com.bookish.Bookish.service;

import com.bookish.Bookish.model.Book;
import com.bookish.Bookish.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService extends BaseService<Book, Long>{
    @Autowired
    private BookRepo repo;


    @Override
    protected BookRepo getRepository() {
        return repo;
    }
}
