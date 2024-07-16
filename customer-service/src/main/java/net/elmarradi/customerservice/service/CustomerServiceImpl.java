package net.elmarradi.customerservice.service;

import lombok.extern.slf4j.Slf4j;
import net.elmarradi.customerservice.dto.CustomerDTO;
import net.elmarradi.customerservice.entities.Customer;
import net.elmarradi.customerservice.exception.CustomerNotFoundException;
import net.elmarradi.customerservice.exception.EmailAlreadyExistException;
import net.elmarradi.customerservice.mapper.CustomerMapper;
import net.elmarradi.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) throws EmailAlreadyExistException {
        log.info("Saving new Customer => %s", customerDTO.toString());
        Optional<Customer> customerByEmail = customerRepository.findByEmail(customerDTO.getEmail());
        if(customerByEmail.isPresent()){
            log.error(String.format("This email %s already exist",customerDTO.getEmail()));
            throw new EmailAlreadyExistException();
        }
        Customer customerToSave = customerMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customerToSave);

        return customerMapper.fromCustomer(savedCustomer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        log.info("Méthode getAllCustomers");
        List<Customer> allCustomers = customerRepository.findAll();
        return customerMapper.fromListCustomers(allCustomers);
    }

    @Override
    public CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException {
        log.info("find customer by id=%s", id);
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) throw new CustomerNotFoundException();
        return customerMapper.fromCustomer(customer.get());
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        log.info("search customers by keyword=%s", keyword);
        List<Customer> customers = customerRepository.findByFirstNameContainingIgnoreCase(keyword);
        return customerMapper.fromListCustomers(customers);

    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException {
        log.info("update Customer => %s", customerDTO.toString());
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isEmpty()) throw new CustomerNotFoundException();
        customerDTO.setId(id);
        Customer customerToUpdate = customerMapper.fromCustomerDTO(customerDTO);
        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        return customerMapper.fromCustomer(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        log.info("delete Customer with id=%s", id );
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isEmpty()) throw new CustomerNotFoundException();
        customerRepository.deleteById(id);
    }
}
