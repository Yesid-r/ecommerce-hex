package co.edu.uptc.api;

import co.edu.uptc.api.DTO.CategoriaDTO;
import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.usecase.guardarcategoria.GuardarCategoriaUseCase;
import co.edu.uptc.usecase.guardarproducto.GuardarProductoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final GuardarProductoUseCase guardarProductoUseCase;
    private final GuardarCategoriaUseCase guardarCategoriaUseCase;

    public Mono<ServerResponse> listenPostGuardarProducto(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(Producto.class)
                .flatMap(producto -> guardarProductoUseCase.action(producto))
                .flatMap(producto -> ServerResponse.ok().bodyValue(producto))
                .onErrorResume(e-> ServerResponse.badRequest().bodyValue(e.getMessage()));


    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTGuardarCategoria(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Categoria.class)
                .flatMap(categoria -> guardarCategoriaUseCase.action(categoria))
                .flatMap(categoria -> ServerResponse.ok().bodyValue(categoria))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }
}
