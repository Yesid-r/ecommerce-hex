package co.edu.uptc.usecase.listarcategorias;

import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.categoria.gateways.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ListarCategoriasUseCase {

    private final CategoriaRepository categoriaRepository;

    public Flux<Categoria> action(){
        return categoriaRepository.buscarTodasLasCategorias();
    }
}
