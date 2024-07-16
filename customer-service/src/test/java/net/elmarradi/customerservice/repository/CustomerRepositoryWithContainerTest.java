package net.elmarradi.customerservice.repository;

import lombok.extern.slf4j.Slf4j;
import net.elmarradi.customerservice.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@ActiveProfiles("test")
@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryWithContainerTest {

    @Container
    @ServiceConnection
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:16");

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void connectionEstablishedTest(){
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

    @BeforeEach
    public void setUp(){
        log.info("--------------------------------------------------------------------------------");
        customerRepository.save(Customer.builder().firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Imane").lastName("Ikram").email("imane@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Rajab").lastName("Yassine").email("rajab@gmail.com").build());
        log.info("--------------------------------------------------------------------------------");
    }

    @Test
    public void shouldFindCustomerByEmail(){
        String givenEmail ="med@gmail.com";
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isPresent();
    }


    @Test
    public void shouldNotFindCustomerByEmail(){
        String givenEmail ="xxxxxx@gmail.com";
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldFindCustomersByFirstName(){
        String keyword = "e";
        List<Customer> listExpected = List.of(
                Customer.builder().firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build(),
                Customer.builder().firstName("Imane").lastName("Ikram").email("imane@gmail.com").build()
        );
        List<Customer> result = customerRepository.findByFirstNameContainingIgnoreCase(keyword);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(listExpected.size());
        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(listExpected);

    }

}