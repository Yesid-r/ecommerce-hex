package co.edu.uptc.mongo;

import co.edu.uptc.mongo.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepository extends ReactiveMongoRepository<CustomerEntity/* change for adapter model */, String>, ReactiveQueryByExampleExecutor<CustomerEntity/* change for adapter model */> {
}
