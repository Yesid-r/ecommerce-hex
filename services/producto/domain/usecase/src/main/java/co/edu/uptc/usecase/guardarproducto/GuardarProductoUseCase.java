package co.edu.uptc.usecase.guardarproducto;

import co.edu.uptc.model.categoria.gateways.CategoriaRepository;
import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuardarProductoUseCase {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public Mono<Producto> action(Producto producto, Integer idCategoria) {
        System.out.println("producto = " + producto);
        return categoriaRepository.buscarCategoriaPorId(idCategoria)
                .switchIfEmpty(Mono.error(new Exception("La categoría no existe")))
                .flatMap(categoria -> {
                    producto.setCategoria(categoria);
                    return productoRepository.guardarProducto(producto);
                })
                .switchIfEmpty(Mono.error(new Exception("No se puede guardar producto")));
    }
}
