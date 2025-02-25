package co.edu.uptc.usecase.deletecustomer;

import co.edu.uptc.model.customer.gateways.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteCustomerUseCase {
    private final CustomerRepository customerRepository;

    public Mono<Void> deleteCustomer(String id){
       return customerRepository.existCustomerById(id).flatMap(exist -> {
           if(Boolean.TRUE.equals(exist)) {
               return customerRepository.deleteCustomer(id);
           }
           return Mono.error(new RuntimeException("Customer not found"));
       });
    }
}
