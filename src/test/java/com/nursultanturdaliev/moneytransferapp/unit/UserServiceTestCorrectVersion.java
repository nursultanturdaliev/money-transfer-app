package com.nursultanturdaliev.moneytransferapp.unit;

import com.nursultanturdaliev.moneytransferapp.model.User;
import com.nursultanturdaliev.moneytransferapp.repository.UserRepository;
import com.nursultanturdaliev.moneytransferapp.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;


public class UserServiceTestCorrectVersion {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserService userService;

    @Before
    public void initUserService() {
        userService = new UserService(userRepository);
    }

    @Test
    public void testFetchAll() {

        List<User> userList = new ArrayList<>();
        userList.add(new User("Askar", "Akaev"));

        when(userRepository.findAll()).thenReturn(userList);
        assertThat(userService.fetchAllUsers()).isNotEmpty();
    }
}
