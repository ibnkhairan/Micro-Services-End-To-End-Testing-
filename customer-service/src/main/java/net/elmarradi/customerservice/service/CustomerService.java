package net.elmarradi.customerservice.service;

import net.elmarradi.customerservice.dto.CustomerDTO;
import net.elmarradi.customerservice.exception.CustomerNotFoundException;
import net.elmarradi.customerservice.exception.EmailAlreadyExistException;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveNewCustomer(CustomerDTO customerDTO)throws EmailAlreadyExistException;
    List<CustomerDTO>getAllCustomers();
    CustomerDTO findCustomerById(Long id)throws CustomerNotFoundException;
    List<CustomerDTO>searchCustomers(String keyword);
    CustomerDTO updateCustomer(Long id,CustomerDTO customerDTO)throws CustomerNotFoundException;
    void deleteCustomer(Long id) throws CustomerNotFoundException;
}
