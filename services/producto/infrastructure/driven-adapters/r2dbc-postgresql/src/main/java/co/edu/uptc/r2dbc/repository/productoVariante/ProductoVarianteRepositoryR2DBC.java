package co.edu.uptc.r2dbc.repository.productoVariante;


import co.edu.uptc.r2dbc.entity.ProductVariantEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductoVarianteRepositoryR2DBC extends ReactiveCrudRepository<ProductVariantEntity, Integer>, ReactiveQueryByExampleExecutor<ProductVariantEntity> {

}
