package com.example.vertx.service.impl;

import com.example.vertx.domain.User;
import com.example.vertx.repository.UserRepository;
import com.example.vertx.service.UserService;

import java.util.List;

/**
 * Created by pushkarmurkute on 25/04/17.
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepository();
    }

    @Override
    public User addUser(User user) throws Exception {
        this.userRepository.addUser(user);
        return user;
    }

    @Override
    public List<User> list() throws Exception {
        return null;
    }
}