package co.edu.uptc.api;

import co.edu.uptc.model.customer.Customer;
import co.edu.uptc.model.customer.gateways.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public Mono<Customer> getCustomerById(@PathVariable String id) {
        return customerRepository.getCustomerById(id);
    }

    @GetMapping("/{id}/exists")
    public Mono<Boolean> customerExists(@PathVariable String id) {
        return customerRepository.existCustomerById(id);
    }

    @GetMapping
    public Flux<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @PostMapping
    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    public Mono<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        customer.setId(id);
        return customerRepository.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id) {
        return customerRepository.deleteCustomer(id);
    }
}