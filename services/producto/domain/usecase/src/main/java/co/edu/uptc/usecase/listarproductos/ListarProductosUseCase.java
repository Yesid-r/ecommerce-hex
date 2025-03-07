package co.edu.uptc.usecase.listarproductos;

import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import co.edu.uptc.model.productvariant.gateways.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.HashSet;

@RequiredArgsConstructor
public class ListarProductosUseCase {
    private final ProductoRepository productoRepository;
    private final ProductVariantRepository productVariantRepository;

    public Flux<Producto> action(){

//        return productoRepository.buscarProductos()
//                .flatMap(producto -> productVariantRepository.listarVariantesPorProducto(producto.getId())
//                        .collectList()
//                        .map(variantes -> {
//                            producto.setVariants(new HashSet<>(variantes));
//                            return producto;
//                        }));
        return productoRepository.buscarProductos();
    }
}
