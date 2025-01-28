package co.edu.uptc.usecase.guardarproducto;

import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuardarProductoUseCase {
    private final ProductoRepository productoRepository;

    public Mono<Producto> action(Producto producto) {
        return productoRepository.guardarProducto(producto)
                .switchIfEmpty(Mono.error(new Exception("No se puede guardar producto")));

    }
}
