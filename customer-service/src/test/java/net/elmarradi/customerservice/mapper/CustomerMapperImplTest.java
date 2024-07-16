package net.elmarradi.customerservice.mapper;

import net.elmarradi.customerservice.dto.CustomerDTO;
import net.elmarradi.customerservice.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperImplTest {

    public CustomerMapper customerMapper;

    CustomerMapperImplTest() {
        customerMapper = new CustomerMapperImpl();
    }

    @Test
    public void shouldMapCustomerToCustomerDTO(){
        Customer givenCustomer = Customer.builder().id(1L)
                                    .lastName("Youssfi")
                                    .firstName("Mohamed")
                                    .email("med@gmail.com")
                                    .build();

        CustomerDTO expectedCustomerDTO = CustomerDTO.builder().id(1L)
                                                            .lastName("Youssfi")
                                                            .firstName("Mohamed")
                                                            .email("med@gmail.com")
                                                            .build();
        CustomerDTO resultCustomerDTO = customerMapper.fromCustomer(givenCustomer);

        assertThat(expectedCustomerDTO).isNotNull();
        assertThat(expectedCustomerDTO).usingRecursiveComparison().isEqualTo(resultCustomerDTO);
    }

    @Test
    public void shouldMapCustomerDTOToCustomer(){

        CustomerDTO givenCustomerDTO = CustomerDTO.builder().id(1L)
                .lastName("Youssfi")
                .firstName("Mohamed")
                .email("med@gmail.com")
                .build();

        Customer expectedCustomer = Customer.builder().id(1L)
                .lastName("Youssfi")
                .firstName("Mohamed")
                .email("med@gmail.com")
                .build();


        Customer resultCustomer = customerMapper.fromCustomerDTO(givenCustomerDTO);

        assertThat(expectedCustomer).isNotNull();
        assertThat(expectedCustomer).usingRecursiveComparison().isEqualTo(resultCustomer);
    }

    @Test
    public void shouldMapListOfCustomerToCustomerDTO(){
        List<Customer> givenCustomers = List.of(
                Customer.builder().id(1L)
                        .lastName("Youssfi")
                        .firstName("Mohamed")
                        .email("med@gmail.com")
                        .build(),
                Customer.builder().id(2L)
                        .lastName("imane")
                        .firstName("lotfi")
                        .email("imane@gmail.com")
                        .build(),
                Customer.builder().id(3L)
                        .lastName("yassine")
                        .firstName("Lmed")
                        .email("yassine@gmail.com")
                        .build()
        );

        List<CustomerDTO> expectedCustomers = List.of(
                CustomerDTO.builder().id(1L)
                        .lastName("Youssfi")
                        .firstName("Mohamed")
                        .email("med@gmail.com")
                        .build(),
                CustomerDTO.builder().id(2L)
                        .lastName("imane")
                        .firstName("lotfi")
                        .email("imane@gmail.com")
                        .build(),
                CustomerDTO.builder().id(3L)
                        .lastName("yassine")
                        .firstName("Lmed")
                        .email("yassine@gmail.com")
                        .build()
        );
        List<CustomerDTO> resultCustomers = customerMapper.fromListCustomers(givenCustomers);
        assertThat(expectedCustomers).isNotNull();
        assertThat(expectedCustomers).usingRecursiveComparison().isEqualTo(resultCustomers);
    }

    @Test
    public void shouldNotMapNullCustomerToCustomerDTO(){
       // assertThatThrownBy(()->customerMapper.fromCustomer(null)).isInstanceOf(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class,
                ()->{
                    customerMapper.fromCustomer(null);
                });
    }
}