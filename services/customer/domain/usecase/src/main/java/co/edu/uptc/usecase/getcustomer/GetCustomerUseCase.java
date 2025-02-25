package co.edu.uptc.usecase.getcustomer;

import co.edu.uptc.model.customer.Customer;
import co.edu.uptc.model.customer.gateways.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetCustomerUseCase {

    private final CustomerRepository customerRepository;

    public Mono<Customer> getCustomer(String id){
        return customerRepository.getCustomerById(id);
    }
}
