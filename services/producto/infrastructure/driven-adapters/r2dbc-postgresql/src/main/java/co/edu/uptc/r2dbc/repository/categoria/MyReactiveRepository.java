package co.edu.uptc.r2dbc.repository.categoria;

import co.edu.uptc.r2dbc.entity.CategoriaEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

// TODO: This file is just an example, you should delete or modify it
public interface MyReactiveRepository extends R2dbcRepository<CategoriaEntity, Integer>, ReactiveQueryByExampleExecutor<CategoriaEntity> {

}
