package com.bookish.Bookish.service;

import com.bookish.Bookish.model.Bookshelf;
import com.bookish.Bookish.repository.BookshelfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookshelfService extends BaseService<Bookshelf, Long>{
    @Autowired
    private BookshelfRepo repo;


    @Override
    protected BookshelfRepo getRepository() {
        return repo;
    }
}
