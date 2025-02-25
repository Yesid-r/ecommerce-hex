package co.edu.uptc.mongo;

import co.edu.uptc.model.customer.Customer;
import co.edu.uptc.model.customer.gateways.CustomerRepository;
import co.edu.uptc.mongo.entity.AddressEntity;
import co.edu.uptc.mongo.entity.CustomerEntity;
import co.edu.uptc.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Customer/* change for domain model */, CustomerEntity/* change for adapter model */, String, MongoDBRepository>
implements CustomerRepository
{

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Customer.class/* change for domain model */));
    }

    @Override
    public Mono<Customer> getCustomerById(String id) {
        return this.repository.findById(id).map(this::toEntity);
    }

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return this.save(customer);
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        return this.findAll();
    }

    @Override
    public Mono<Boolean> existCustomerById(String id) {
        return this.repository.existsById(id);
    }

    @Override
    public Mono<Customer> updateCustomer(Customer customer) {

        return this.repository.findById(customer.getId())
                .map(customerEntity -> {
                    customerEntity.setName(customer.getName());
                    customerEntity.setLastName(customer.getLastName());
                    customerEntity.setAddress(
                            AddressEntity.builder()
                                    .street(customer.getAddress().getStreet())
                                    .city(customer.getAddress().getCity())
                                    .state(customer.getAddress().getState())
                                    .zipCode(customer.getAddress().getZipCode())
                                    .build()
                    );
                    return customerEntity;
                })
                .flatMap(this.repository::save)
                .map(this::toEntity);
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {

        return this.repository.deleteById(id);

    }
}
