package co.edu.uptc.model.categoria.gateways;

import co.edu.uptc.model.categoria.Categoria;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriaRepository {
    Mono<Categoria> guardarCategoria(Categoria categoria);
    Mono<Categoria> buscarCategoriaPorId(Integer id);
    Flux<Categoria> buscarTodasLasCategorias();
}
