


package com.nursultanturdaliev.moneytransferapp.repository;

import com.nursultanturdaliev.moneytransferapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findTop10ByFirstNameAndLastName(String firstName, String lastName);
    Iterable<User> findTop10ByLastName(String lastName);
    Iterable<User> findTop10ByFirstName(String firstName);

    @Query(value = "Select * From users limit 10",nativeQuery = true)
    Iterable<User> fetchTop10();



}
