package co.edu.uptc.r2dbc.repository.productoVariante;

import co.edu.uptc.model.productvariant.ProductVariant;
import co.edu.uptc.model.productvariant.gateways.ProductVariantRepository;
import co.edu.uptc.r2dbc.entity.ProductVariantEntity;
import co.edu.uptc.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ProductoVarianteRepositoryAdapter extends ReactiveAdapterOperations<
        ProductVariant,
        ProductVariantEntity,
        Integer,
        ProductoVarianteRepositoryR2DBC
        >
implements ProductVariantRepository
{
    public ProductoVarianteRepositoryAdapter(ProductoVarianteRepositoryR2DBC repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, ProductVariant.class));
    }

    @Override
    public Mono<ProductVariant> guardarVariante(ProductVariant productVariant) {
        return this.save(productVariant);
    }
}
