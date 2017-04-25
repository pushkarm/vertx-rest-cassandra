package com.example.vertx.service;

import com.example.vertx.domain.User;

import java.util.List;

/**
 * Created by pushkarmurkute on 25/04/17.
 */
public interface UserService {

    public User addUser(User user) throws Exception;

    public List<User> list() throws Exception;

}
