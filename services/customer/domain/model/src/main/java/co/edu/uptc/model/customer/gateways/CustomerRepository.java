package co.edu.uptc.model.customer.gateways;

import co.edu.uptc.model.customer.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository {

    Mono<Customer> getCustomerById(Integer id);
    Mono<Customer> saveCustomer(Customer customer);
    Flux<Customer> getAllCustomers();
    Boolean existCustomerById(Integer id);
    Mono<Customer> updateCustomer(Customer customer);
    Mono<Void> deleteCustomer(Integer id);
}
