package co.edu.uptc.usecase.savecustomer;

import co.edu.uptc.model.customer.Customer;
import co.edu.uptc.model.customer.gateways.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SaveCustomerUseCase {

    private final CustomerRepository customerRepository;
    public Mono<Customer> action(Customer customer){
        return customerRepository.saveCustomer(customer);
    }
}
