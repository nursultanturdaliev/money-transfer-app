package com.nursultanturdaliev.moneytransferapp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nursultanturdaliev.moneytransferapp.model.User;
import com.nursultanturdaliev.moneytransferapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public @ResponseBody
    User findOne(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("/")
    public @ResponseBody
    Iterable<User> index() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/")
    public User create(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @PutMapping(value = "/{id}")
    public User update(@RequestBody User user, @PathVariable Long id) {

        User savedUser = userRepository.findById(id).get();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            savedUser = objectMapper.updateValue(savedUser, user);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }

        userRepository.save(savedUser);
        return savedUser;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
