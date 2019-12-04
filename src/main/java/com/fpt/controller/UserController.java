package com.fpt.controller;

import com.fpt.entity.User;
import com.fpt.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> list() {
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> store(@RequestBody User user) {
        try {
            User createUser = userService.save(user);
            return new ResponseEntity<>(createUser, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<List<User>> search(@RequestParam String name) {
        List<User> userList = userService.search(name);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> detail(@PathVariable long id) {
        User user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User updateUser) {
        User existUser = userService.getById(id);
        if (existUser == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            existUser.setUsername(updateUser.getUsername());
            existUser.setPassword(updateUser.getPassword());
            existUser.setAvatar(updateUser.getAvatar());
            existUser.setRole(updateUser.getRole());
            userService.save(existUser);
            return  new ResponseEntity<>(existUser, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<User> delete(@PathVariable long id) {
        User existUser = userService.getById(id);
        if (existUser == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        userService.delete(existUser);

        return new ResponseEntity<>(existUser, HttpStatus.OK);
    }


}
