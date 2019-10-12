package com.nursultanturdaliev.moneytransferapp.unit;

import com.nursultanturdaliev.moneytransferapp.HomeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class HomeServiceTest {

    HomeService homeService;

    @Before
    public void init()
    {
        homeService = new HomeService();
    }

    @Test
    public void testWelcome()
    {
        assertThat(homeService.welcome()).isEqualTo("Welcome Home!");
    }

    @Test
    public void testWelcomeNegative()
    {
        assertThat(homeService.welcome()).isNotEqualTo("Welcome");
    }

}
