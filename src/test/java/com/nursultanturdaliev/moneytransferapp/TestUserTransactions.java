package com.nursultanturdaliev.moneytransferapp;

import com.nursultanturdaliev.moneytransferapp.model.Transaction;
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

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUserTransactions {

    private static final String HTTP_LOCALHOST = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testCreateTransactions() {
        ResponseEntity<Transaction> transactionResponseEntity =
                this.restTemplate.
                        postForEntity(HTTP_LOCALHOST + port +
                                        "/api/users/30000/transactions/",
                                new Transaction(1000l, "msu23"), Transaction.class);
        assertThat(transactionResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(transactionResponseEntity.getBody()).isNull();
    }

    @Test
    @Sql(scripts = "classpath:delete-users.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:insert-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testCreateTransactionsSuccess(){
        URI uri = URI.create(HTTP_LOCALHOST + port + "/api/users/1/transactions/");
        Transaction transaction = new Transaction(1000l, "msu23");

        ResponseEntity<Transaction> transactionResponseEntity =
                this.restTemplate.postForEntity(
                        uri,
                        transaction,
                        Transaction.class
                );

        assertThat(transactionResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(transactionResponseEntity.getBody()).hasFieldOrPropertyWithValue("transactionId", "msu23");
    }

}
