package co.edu.uptc.usecase.guardarcategoria;

import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.categoria.gateways.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuardarCategoriaUseCase {
    private final CategoriaRepository categoriaRepository;

    public Mono<Categoria> action (Categoria categoria){
        System.out.println("categoria = " + categoria);
        return categoriaRepository.guardarCategoria(categoria).switchIfEmpty(Mono.error(new RuntimeException("No se puede guardar Categoria")));
    }
}
