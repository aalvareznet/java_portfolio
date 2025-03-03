package com.bookish.Bookish.service;

import com.bookish.Bookish.model.Bookshelf;
import com.bookish.Bookish.model.User;
import com.bookish.Bookish.repository.BookshelfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookshelfService extends BaseService<Bookshelf, Long>{
    @Autowired
    private BookshelfRepo repo;

    @Override
    protected BookshelfRepo getRepository() {
        return repo;
    }

    public List<Bookshelf> findByUser(User user){return repo.findByUser(user);}
}
