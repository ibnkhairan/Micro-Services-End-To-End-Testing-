package net.elmarradi.customerservice.mapper;

import net.elmarradi.customerservice.dto.CustomerDTO;
import net.elmarradi.customerservice.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMapperImpl implements CustomerMapper {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CustomerDTO fromCustomer(Customer customer)throws IllegalArgumentException
    {
        if(customer == null)
            throw new IllegalArgumentException("Customer cannot be null");

        return modelMapper.map(customer,CustomerDTO.class);
    }

    @Override
    public Customer fromCustomerDTO(CustomerDTO customerDTO) throws IllegalArgumentException
    {
       if(customerDTO == null)
            throw new IllegalArgumentException("customerDTO cannot be null");
        return modelMapper.map(customerDTO,Customer.class);
    }

    @Override
    public List<CustomerDTO> fromListCustomers(List<Customer> customers){
        return customers.stream().map(customer -> modelMapper.map(customer,CustomerDTO.class)).toList();
    }


}
