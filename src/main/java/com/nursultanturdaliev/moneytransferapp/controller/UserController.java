package com.nursultanturdaliev.moneytransferapp.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import com.nursultanturdaliev.moneytransferapp.model.User;
import com.nursultanturdaliev.moneytransferapp.repository.TransactionRepository;
import com.nursultanturdaliev.moneytransferapp.repository.UserRepository;
import com.nursultanturdaliev.moneytransferapp.services.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/users")
@Api
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value = "find one user by id")
    @GetMapping("/{id}")
    @PostAuthorize("returnObject.getBody().firstName =='Akyl'")
    public ResponseEntity<User> findOne(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}/transactions")
    @ApiOperation(value = "find transactions by user id")
    public ResponseEntity<Iterable<Transaction>> findTransactionsByUserId(@PathVariable Long id) {
        Iterable<Transaction> transactions = transactionRepository.findByUserId(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/{id}/transactions/")
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction, @PathVariable Long id) {

        User user = userRepository.findById(id).get();

        transaction.setUser(user);

        Transaction savedTransaction = this.transactionRepository.save(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    //provide expression-based access control
    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Iterable<User>> index() {
        return ResponseEntity.ok()
                .header("Request-Id", "request-id")
                .body(userRepository.findAll());
    }

    @PostMapping(value = "/")
    @ApiOperation(value = "create user")
    public ResponseEntity<User> create(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) throws JsonMappingException {

        User savedUser = userRepository.findById(id).get();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        savedUser = objectMapper.updateValue(savedUser, user);
        userRepository.save(savedUser);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
            userRepository.deleteById(id);
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
