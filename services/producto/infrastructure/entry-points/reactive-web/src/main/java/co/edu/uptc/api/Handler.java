package co.edu.uptc.api;

import co.edu.uptc.api.DTO.*;
import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.usecase.guardarcategoria.GuardarCategoriaUseCase;
import co.edu.uptc.usecase.guardarproducto.GuardarProductoUseCase;
import co.edu.uptc.usecase.guardarvarianteproducto.GuardarVarianteProductoUseCase;
import co.edu.uptc.usecase.listarproductos.ListarProductosUseCase;
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
    private final ProductVarantMapper productVarantMapper;
    private final GuardarVarianteProductoUseCase guardarVarianteProductoUseCase;
    private final ListarProductosUseCase listarProductosUseCase;

    public Mono<ServerResponse> listenGETListarProductos(ServerRequest serverRequest) {
        return ServerResponse.ok().body(listarProductosUseCase.action(), Producto.class);
    }
    public Mono<ServerResponse> listenPostGuardarProducto(ServerRequest serverRequest) {



        System.out.println("serverRequest = " + serverRequest);
        return serverRequest.bodyToMono(ProductRequest.class)
                .flatMap(producto -> guardarProductoUseCase.action(productoMapper.toProducto(producto), producto.categoriaId() ))
                .flatMap(producto1 -> ServerResponse.ok().bodyValue(producto1))
                .onErrorResume(e-> ServerResponse.badRequest().bodyValue(e.getMessage()));


    }

    public Mono<ServerResponse> listenPostGuardarVariante(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductVariantRequest.class)
                .flatMap(variante -> guardarVarianteProductoUseCase.action(productVarantMapper.toProductVariant(variante)))
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
