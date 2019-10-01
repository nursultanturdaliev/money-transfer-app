


package com.nursultanturdaliev.moneytransferapp.repository;

import com.nursultanturdaliev.moneytransferapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findByFirstName(String firstName);
}
