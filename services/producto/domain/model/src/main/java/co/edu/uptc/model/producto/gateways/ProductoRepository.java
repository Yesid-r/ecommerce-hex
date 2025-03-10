package co.edu.uptc.model.producto.gateways;

import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.productvariant.ProductVariant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductoRepository {
    Mono<Producto> guardarProducto(Producto producto);

    Mono<Producto> buscarPorId(Integer idProducto);

    Flux<Producto> buscarProductos();
    Mono<Producto> actualizarProducto(Producto producto);
    Mono<Void> eliminarProducto(Integer idProducto);

    Flux<Producto> obtenerProductosPorIds(List<Integer> productIds);
}
