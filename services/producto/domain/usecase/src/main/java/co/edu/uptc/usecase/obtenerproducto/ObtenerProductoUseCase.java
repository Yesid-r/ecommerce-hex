package co.edu.uptc.usecase.obtenerproducto;

import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import co.edu.uptc.model.productvariant.ProductVariant;
import co.edu.uptc.model.productvariant.gateways.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class ObtenerProductoUseCase {

    private final ProductoRepository productoRepository;
    private final ProductVariantRepository productVariantRepository;

    public Mono<Producto> action(Integer id){
        return productoRepository.buscarPorId(id)
                .flatMap(producto -> productVariantRepository.listarVariantesPorProducto(producto.getId())
                        .collectList()
                        .map(variantes -> {
                            producto.setVariants(new HashSet<>(variantes));
                            return producto;
                        }));
    }

}
