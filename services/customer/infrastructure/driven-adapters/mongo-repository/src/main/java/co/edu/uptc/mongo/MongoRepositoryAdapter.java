package co.edu.uptc.mongo;

import co.edu.uptc.model.customer.Customer;
import co.edu.uptc.model.customer.gateways.CustomerRepository;
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
    public Mono<Customer> getCustomerById(Integer id) {
        return null;
    }

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return this.save(customer);
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Boolean existCustomerById(Integer id) {
        return null;
    }

    @Override
    public Mono<Customer> updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Mono<Void> deleteCustomer(Integer id) {
        return null;
    }
}
