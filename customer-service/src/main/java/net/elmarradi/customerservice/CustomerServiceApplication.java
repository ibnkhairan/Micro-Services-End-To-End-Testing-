package net.elmarradi.customerservice;

import net.elmarradi.customerservice.entities.Customer;
import net.elmarradi.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    @Profile("!test")
    CommandLineRunner start(CustomerRepository customerRepository){
        return args->{
            customerRepository.save(Customer.builder().firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build());
            customerRepository.save(Customer.builder().firstName("Imane").lastName("Ikram").email("imane@gmail.com").build());
            customerRepository.save(Customer.builder().firstName("Rajab").lastName("Yassine").email("rajab@gmail.com").build());
        };
    }

}
