package co.edu.uptc.usecase.obtenerproducto;

import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ObtenerProductoUseCase {

    private final ProductoRepository productoRepository;

    public Mono<Producto> action(Integer id){
        return productoRepository.buscarPorId(id);
    }
}
