package com.shivraj.ecommerce.customer;

import com.shivraj.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id()).orElseThrow(() ->
                new CustomerNotFoundException(format("Can not update customer:: No Customer found with provided id: %s", request.id())));
        mergerCustomer(customer, request);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName()){
            customer.setFirstName(customer.getFirstName());
        }

        if(StringUtils.isNotBlank(request.lastName()){
            customer.setLastName(customer.getLastName());
        }

        if(StringUtils.isNotBlank(request.email()){
            customer.setFirstName(customer.getEmail());
        }

        if(request.address() != null){
            customer.setAddress(customer.getAddress());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId).map(mapper::fromCustomer).orElseThrow(() -> new CustomerNotFoundException(format("No customer found with the provided ID :: %s",customerId)));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
