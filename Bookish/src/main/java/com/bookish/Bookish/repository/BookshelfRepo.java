package com.bookish.Bookish.repository;

import com.bookish.Bookish.model.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookshelfRepo extends JpaRepository<Bookshelf, Long > {
}
