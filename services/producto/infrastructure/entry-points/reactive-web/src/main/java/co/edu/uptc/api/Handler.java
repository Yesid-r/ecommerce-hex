package co.edu.uptc.api;

import co.edu.uptc.api.DTO.CategoriaDTO;
import co.edu.uptc.api.DTO.CategoriaMapper;
import co.edu.uptc.api.DTO.ProductRequest;
import co.edu.uptc.api.DTO.ProductoMapper;
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
    private final CategoriaMapper categoriaMapper;
    private final ProductoMapper productoMapper;

    public Mono<ServerResponse> listenPostGuardarProducto(ServerRequest serverRequest) {



        System.out.println("serverRequest = " + serverRequest);
        return serverRequest.bodyToMono(ProductRequest.class)
                .flatMap(producto -> guardarProductoUseCase.action(productoMapper.toProducto(producto), producto.categoriaId() ))
                .flatMap(producto1 -> ServerResponse.ok().bodyValue(producto1))
                .onErrorResume(e-> ServerResponse.badRequest().bodyValue(e.getMessage()));


    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");

    }

    public Mono<ServerResponse> listenPOSTGuardarCategoria(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(CategoriaDTO.class)
                .flatMap(categoria -> guardarCategoriaUseCase.action(categoriaMapper.toCategoria(categoria))
                .flatMap(categoria1 -> ServerResponse.ok().bodyValue(categoriaMapper.toCategoriaDTO(categoria1)))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage())));
    }
}
