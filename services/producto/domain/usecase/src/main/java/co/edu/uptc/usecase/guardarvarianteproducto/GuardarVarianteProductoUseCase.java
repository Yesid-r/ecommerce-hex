package co.edu.uptc.usecase.guardarvarianteproducto;

import co.edu.uptc.model.producto.gateways.ProductoRepository;
import co.edu.uptc.model.productvariant.ProductVariant;
import co.edu.uptc.model.productvariant.gateways.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuardarVarianteProductoUseCase {

    private final ProductVariantRepository productVariantRepository;
    private final ProductoRepository productoRepository;

    public Mono<ProductVariant> action(ProductVariant productVariant) {
        return productoRepository.buscarPorId(productVariant.getIdProducto()).
                switchIfEmpty(Mono.error(new RuntimeException("No existe el producto " + productVariant.getIdProducto())))
                .flatMap(productVariant1 -> {
                    productVariant.setIdProducto(productVariant1.getId());
                    return  productVariantRepository.guardarVariante(productVariant);
                }).switchIfEmpty(Mono.error(new Exception("No se puede guardar el producto")));
    }
}
