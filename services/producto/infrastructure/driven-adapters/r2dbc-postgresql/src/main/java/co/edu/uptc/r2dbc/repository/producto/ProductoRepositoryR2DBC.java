package co.edu.uptc.r2dbc.repository.producto;

import co.edu.uptc.r2dbc.entity.CategoriaEntity;
import co.edu.uptc.r2dbc.entity.ProductoEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface ProductoRepositoryR2DBC extends ReactiveCrudRepository<ProductoEntity, Integer>, ReactiveQueryByExampleExecutor<ProductoEntity> {

}
