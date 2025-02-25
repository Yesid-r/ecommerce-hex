package co.edu.uptc.usecase.getallcustomer;

import co.edu.uptc.model.customer.Customer;
import co.edu.uptc.model.customer.gateways.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllCustomerUseCase {
    private final CustomerRepository customerRepository;

    public Flux<Customer> action(){
        return customerRepository.getAllCustomers();
    }
}
