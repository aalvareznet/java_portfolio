package com.bookish.Bookish.service;

import com.bookish.Bookish.model.User;
import com.bookish.Bookish.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long>{
    @Autowired
    private UserRepo repo;

    @Override
    protected UserRepo getRepository() {
        return repo;
    }

    //Rest of methods
}
