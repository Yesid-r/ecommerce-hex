package co.edu.uptc.model.productvariant.gateways;

import co.edu.uptc.model.productvariant.ProductVariant;
import reactor.core.publisher.Mono;

public interface ProductVariantRepository {
    Mono<ProductVariant> guardarVariante(ProductVariant productVariant);
}
