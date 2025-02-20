package co.edu.uptc.usecase.eliminarproducto;

import co.edu.uptc.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EliminarProductoUseCase {
    private final ProductoRepository productoRepository;

    public Mono<Void> eliminarProducto(Integer idProducto) {
        return  productoRepository.eliminarProducto(idProducto);
    }
}
