package co.edu.uptc.usecase.updatecustomer;

import co.edu.uptc.model.customer.Customer;
import co.edu.uptc.model.customer.gateways.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public Mono<Customer> updateCustomer(String id, Customer customer){
        System.out.println("id = " + id);
        customer.setId(id);
        return customerRepository.existCustomerById(id)
                .flatMap(exist -> {
                    if(Boolean.TRUE.equals(exist)) {
                        return customerRepository.updateCustomer(customer);
                    }
                    return Mono.error(new RuntimeException("Customer not found"));
                });
    }
}
