package co.edu.uptc.api;

import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.usecase.producto.GetProductsForOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final GetProductsForOrderUseCase getProductsForOrderUseCase;

    @PostMapping("/for-order")
    public Flux<Producto> getProductsForOrder(@RequestBody List<Integer> productIds) {
        return getProductsForOrderUseCase.getProductsForOrder(productIds);
    }
}