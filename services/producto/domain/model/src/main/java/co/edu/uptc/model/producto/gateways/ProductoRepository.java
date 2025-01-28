package co.edu.uptc.model.producto.gateways;

import co.edu.uptc.model.producto.Producto;
import reactor.core.publisher.Mono;

public interface ProductoRepository {
    Mono<Producto> guardarProducto(Producto producto);
}
