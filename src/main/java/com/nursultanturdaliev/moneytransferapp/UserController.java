package com.nursultanturdaliev.moneytransferapp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import com.nursultanturdaliev.moneytransferapp.model.User;
import com.nursultanturdaliev.moneytransferapp.repository.TransactionRepository;
import com.nursultanturdaliev.moneytransferapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<Iterable<Transaction>> findTransactionsByUserId(@PathVariable Long id) {
        Iterable<Transaction> transactions = transactionRepository.findByUserId(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<User>> index() {
        return ResponseEntity.ok()
                .header("Request-Id", "request-id")
                .body(userRepository.findAll());
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

//http://localhost:8080/api/users/search?firstName=Nursultan

    @GetMapping("/search")
    public ResponseEntity<Iterable<User>> search(@RequestParam(required = false, name = "firstName") String firstName,
                                                 @RequestParam(required = false, name = "lastName") String lastName) {
        Iterable<User> iterable;
        if (firstName != null && lastName != null) {
            iterable = userRepository.findTop10ByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null) {
            iterable = userRepository.findTop1ByFirstName(firstName);
        } else if (lastName != null) {
            iterable = userRepository.findTop10ByLastName(lastName);
        } else {
            iterable = userRepository.findAllTopTen();
        }

        return ResponseEntity.ok(iterable);
    }
}
