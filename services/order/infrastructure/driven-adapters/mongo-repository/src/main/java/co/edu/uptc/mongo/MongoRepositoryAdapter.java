package co.edu.uptc.mongo;

import co.edu.uptc.model.order.Order;
import co.edu.uptc.model.order.gateways.OrderRepository;
import co.edu.uptc.mongo.entity.OrderEntity;
import co.edu.uptc.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Order/* change for domain model */, OrderEntity/* change for adapter model */, String, MongoDBRepository>
implements OrderRepository
{

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Order.class/* change for domain model */));
    }

    @Override
    public Mono<Order> saveOrder(Order order) {
        return this.save(order);
    }
}
