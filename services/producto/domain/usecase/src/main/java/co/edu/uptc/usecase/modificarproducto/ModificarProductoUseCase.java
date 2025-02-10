package co.edu.uptc.usecase.modificarproducto;

import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ModificarProductoUseCase {
    private final ProductoRepository productoRepository;
    public Mono<Producto> modificarProducto(Integer id, Producto producto) {

        return productoRepository.buscarPorId(id)
                .flatMap(producto1 -> {
                    producto1.setNombre(producto.getNombre());
                    producto1.setDescripcion(producto.getDescripcion());
                    producto1.setColor(producto.getColor());
                    producto1.setPrecio(producto.getPrecio());
                    producto1.setActive(producto.isActive());
                    return productoRepository.actualizarProducto(producto1);
                });
    }
}
