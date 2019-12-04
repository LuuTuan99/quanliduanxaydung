package com.fpt.service;

import com.fpt.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    List<User> search(String name);

    User getById(long id);

    User save(User user);

    User update(long id, User user);

    boolean delete(User user);


}
