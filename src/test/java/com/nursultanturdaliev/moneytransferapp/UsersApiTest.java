package com.nursultanturdaliev.moneytransferapp;

import com.nursultanturdaliev.moneytransferapp.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersApiTest {

    private static final String HTTP_LOCALHOST = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testFetchAllUsersEndpoint() {
        assertThat(this.restTemplate.getForObject(HTTP_LOCALHOST + port + "/api/users/",
                User[].class)).isNotEmpty();
    }

    @Test
    @Sql(scripts = "classpath:insert-user.sql")
    public void testFetchUserEndpointWithExistingUser() {
        User firstUser = this.restTemplate.getForObject(HTTP_LOCALHOST + port + "/api/users/1", User.class);
        assertThat(firstUser).hasFieldOrPropertyWithValue("firstName", "Nursultan");
        assertThat(firstUser).hasFieldOrPropertyWithValue("lastName", "Turdaliev");
    }

    @Test
    public void testFetchUserEndpointWithNonExistentUser() {
        ResponseEntity<String> responseEntity = this.restTemplate.
                getForEntity(HTTP_LOCALHOST + port + "/api/users/30000", String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testCreateUserEndpoint() throws URISyntaxException {
        URI url = new URI(HTTP_LOCALHOST + port + "/api/users/");
        ResponseEntity<User> userResponseEntity = this.restTemplate
                .postForEntity(url, new User("Tokon", "Mamytov"), User.class);

        assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(userResponseEntity.getBody()).hasFieldOrPropertyWithValue("firstName", "Tokon");
        assertThat(userResponseEntity.getBody()).hasFieldOrPropertyWithValue("lastName", "Mamytov");
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:delete-users.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:testRunSpecificMethodRelatedSql.sql")
    public void testRunSpecificMethodRelatedSql() throws URISyntaxException {

        URI url = new URI(HTTP_LOCALHOST + port + "/api/users/");
        ResponseEntity<User> userResponseEntity = this.restTemplate.getForEntity(url + "100",User.class);

        assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userResponseEntity.getBody()).hasFieldOrPropertyWithValue("firstName", "Azamat");
        assertThat(userResponseEntity.getBody()).hasFieldOrPropertyWithValue("lastName", "Baimatov");
    }

}
