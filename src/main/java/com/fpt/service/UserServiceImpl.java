package com.fpt.service;

import com.fpt.entity.User;
import com.fpt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findActiveUser(User.Status.ACTIVE.getValue());
    }

    @Override
    public List<User> search(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        user.setStatus(User.Status.ACTIVE.getValue());
        return userRepository.save(user);
    }

    @Override
    public User update(long id, User user) {
        User existUser = userRepository.findById(id).orElse(null);

        existUser.setUsername(user.getUsername());
        existUser.setPassword(user.getPassword());
        existUser.setAvatar(user.getAvatar());
        existUser.setRole(user.getRole());
        return userRepository.save(existUser);
    }

    @Override
    public boolean delete(User user) {
        user.setStatus(User.Status.DELETED.getValue());
        userRepository.save(user);
        return true;
    }
}
