package com.nursultanturdaliev.moneytransferapp.unit;

import com.nursultanturdaliev.moneytransferapp.model.User;
import com.nursultanturdaliev.moneytransferapp.repository.UserRepository;
import com.nursultanturdaliev.moneytransferapp.services.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;


public class UserServiceImplTestCorrectVersion {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserServiceImpl userServiceImpl;

    @Before
    public void initUserService() {
        userServiceImpl = new UserServiceImpl(userRepository);
    }

    @Test
    public void testFetchAll() {

        List<User> userList = new ArrayList<>();
        userList.add(new User("Askar", "Akaev"));

        when(userRepository.findAll()).thenReturn(userList);
        assertThat(userServiceImpl.fetchAllUsers()).isNotEmpty();
    }
}
