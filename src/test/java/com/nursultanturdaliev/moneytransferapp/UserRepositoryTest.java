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
    private UserRepository repository;

    @Test
    public void testFindByFirstName() throws Exception {
        User mockUser = new User();
        mockUser.setFirstName("Chyngyz");
        mockUser.setLastName("Aitmatov");
        this.entityManager.persist(mockUser);

        Iterable<User> users = this.repository.findByFirstName("Chyngyz");

        List<User> userList= new ArrayList<>();

        users.forEach(userList::add);

        assertThat(userList).isNotEmpty();
        assertThat(userList).contains(mockUser);

        User user = userList.get(0);

        assertThat(user.getLastName()).isEqualTo("Aitmatov");
        assertThat(user.getFirstName()).isEqualTo("Chyngyz");
    }

    @Test
    public void testFindByFirstNameAndLastNameTopTen() throws Exception {
        User mockUser1 = null;

        for (int i =0; i <12;i++){

            mockUser1 = new User();
            mockUser1.setFirstName("Mamat");
            mockUser1.setLastName("Muktar");
            this.entityManager.persist(mockUser1);
        }


        Iterable<User> users = this.repository.findTop10ByFirstNameAndLastName("Mamat", "Muktar");
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
       assertThat(userList).isNotEmpty();
        assertThat(userList).doesNotContain(mockUser1);
        User user = userList.get(0);
        assertThat(user.getLastName()).isEqualTo("Muktar");
        assertThat(user.getFirstName()).isEqualTo("Mamat");
        assertThat(userList.size()).isEqualTo(10);
    }
}