package com.nursultanturdaliev.moneytransferapp.unit;

import com.nursultanturdaliev.moneytransferapp.repository.UserRepository;
import com.nursultanturdaliev.moneytransferapp.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestImprovedVersionTwo {

    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void initUserService()
    {
        userService = new UserService(userRepository);
    }

    @Test
    public void testFetchAll() {
        assertThat(userService.fetchAllUsers()).isNotEmpty();
    }
}
