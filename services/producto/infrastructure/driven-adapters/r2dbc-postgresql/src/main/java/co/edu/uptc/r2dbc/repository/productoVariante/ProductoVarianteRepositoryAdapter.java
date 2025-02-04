package co.edu.uptc.r2dbc.repository.productoVariante;

import co.edu.uptc.model.productvariant.ProductVariant;
import co.edu.uptc.model.productvariant.gateways.ProductVariantRepository;
import co.edu.uptc.r2dbc.entity.ProductVariantEntity;
import co.edu.uptc.r2dbc.entity.ProductoVariantMapper;
import co.edu.uptc.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProductoVariantMapper productoVariantMapper;
    public ProductoVarianteRepositoryAdapter(ProductoVarianteRepositoryR2DBC repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, ProductVariant.class));
    }

    @Override
    public Mono<ProductVariant> guardarVariante(ProductVariant productVariant) {

        ProductVariantEntity productVariantEntity = productoVariantMapper.toEntity(productVariant);
        System.out.println("productVariantEntity = " + productVariantEntity);
        return this.repository.save(productVariantEntity)
                .map(productoVariantMapper::toDomain)
                .onErrorResume(Mono::error);

//        return this.repository.save(productoVariantMapper.toEntity(productVariant))
//                .map(productoVariantMapper::toDomain)
//                .onErrorResume(Mono::error);
    }
}
