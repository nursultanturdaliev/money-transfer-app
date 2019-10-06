package com.nursultanturdaliev.moneytransferapp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nursultanturdaliev.moneytransferapp.model.Transaction;
import com.nursultanturdaliev.moneytransferapp.model.User;
import com.nursultanturdaliev.moneytransferapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users/")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id) {

        try {
            User user = userRepository.findById(id).get();
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public Iterable<User> all() {
        return userRepository.findAll();
    }

    //http://localhost:8080/api/users/search?firstName=Nursultan

    @GetMapping("/search")
    public Iterable<User> search(@RequestParam(required = false) String firstName,@RequestParam(required = false) String lastName)
    {
        if (firstName != null && lastName != null){
            return userRepository.findTop10ByFirstNameAndLastName(firstName,lastName);
        }

        if (firstName == null && lastName != null){
            return userRepository.findTop10ByLastName(lastName);
        }
        if (firstName != null && lastName == null){
            return userRepository.findTop10ByFirstName(firstName);
        }

        return userRepository.fetchTop10();
    }



    @PostMapping("/")
    public User create(@RequestBody User user) {
        user = userRepository.save(user);
        user = userRepository.findById(user.getId()).get();
        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Long id) throws JsonMappingException {
        User savedUser = userRepository.findById(id).get();

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        savedUser = objectMapper.updateValue(savedUser, user);
        userRepository.save(savedUser);

        return savedUser;
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<Iterable<Transaction>> findTransactionsByUserId(@PathVariable Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        return ResponseEntity.ok(user.getTransactions());
    }
}
