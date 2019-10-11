package com.nursultanturdaliev.moneytransferapp;

import com.nursultanturdaliev.moneytransferapp.model.User;
import com.nursultanturdaliev.moneytransferapp.repository.UserRepository;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testFindByFirstName() throws Exception {

        //Preparation
        User mockUser = new User();
        mockUser.setFirstName("Chyngyz");
        mockUser.setLastName("Aitmatov");
        this.entityManager.persist(mockUser);

        //We are testing this function
        Iterable<User> users = this.userRepository.findByFirstName("Chyngyz");


        //Assertions
        List<User> userList = new ArrayList<>();

        users.forEach(userList::add);

        assertThat(userList).isNotEmpty();
        assertThat(userList).contains(mockUser);

        User user = userList.get(0);

        assertThat(user.getLastName()).isEqualTo("Aitmatov");
        assertThat(user.getFirstName()).isEqualTo("Chyngyz");
    }

    @Test
    public void testFindTop10ByFirstNameAndLastName() {
        //Preparation
        User mockUser = null;
        for (int i = 0; i < 15; i++) {
            mockUser = new User();
            mockUser.setFirstName("Chyngyz");
            mockUser.setLastName("Aitmatov");
            this.entityManager.persist(mockUser);
        }
        //Preparation
        User mockUser2 = new User();
        mockUser2.setFirstName("Jenny");
        mockUser2.setLastName("Lou");
        this.entityManager.persist(mockUser2);
        Iterable<User> users = this.userRepository.findTop10ByFirstNameAndLastName("Chyngyz", "Aitmatov");
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        assertThat(userList).isNotEmpty();
        assertThat(userList).doesNotContain(mockUser);
        assertThat(userList.size()).isEqualTo(10);


    }
}
