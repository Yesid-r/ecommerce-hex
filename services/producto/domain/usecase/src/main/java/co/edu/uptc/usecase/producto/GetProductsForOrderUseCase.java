package co.edu.uptc.usecase.producto;

import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class GetProductsForOrderUseCase {

    private final ProductoRepository productoRepository;

    public Flux<Producto> getProductsForOrder(List<Integer> productIds) {
        return Flux.fromIterable(productIds)
                .flatMap(productoRepository::buscarPorId);
    }
}