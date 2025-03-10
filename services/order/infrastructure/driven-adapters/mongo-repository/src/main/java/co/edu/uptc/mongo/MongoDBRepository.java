package co.edu.uptc.mongo;

import co.edu.uptc.mongo.entity.OrderEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepository extends ReactiveMongoRepository<OrderEntity/* change for adapter model */, String>, ReactiveQueryByExampleExecutor<OrderEntity/* change for adapter model */> {
}
