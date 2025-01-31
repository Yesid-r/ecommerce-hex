package co.edu.uptc.usecase.listarproductos;

import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ListarProductosUseCase {
    private final ProductoRepository productoRepository;

    public Flux<Producto> action(){

        return productoRepository.buscarProductos();
    }
}
