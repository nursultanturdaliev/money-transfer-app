package com.nursultanturdaliev.moneytransferapp.unit;

import com.nursultanturdaliev.moneytransferapp.services.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTestImprovedVersionOne {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    public void testFetchAll() {
        assertThat(userServiceImpl.fetchAllUsers()).isNotEmpty();
    }
}
