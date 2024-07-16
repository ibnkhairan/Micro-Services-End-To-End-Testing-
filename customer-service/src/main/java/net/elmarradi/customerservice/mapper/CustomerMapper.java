package net.elmarradi.customerservice.mapper;

import net.elmarradi.customerservice.dto.CustomerDTO;
import net.elmarradi.customerservice.entities.Customer;

import java.util.List;

public interface CustomerMapper {

    CustomerDTO fromCustomer(Customer customer);

    Customer fromCustomerDTO(CustomerDTO customerDTO);

    List<CustomerDTO> fromListCustomers(List<Customer> customers);
}
