package co.edu.uptc.api;

import co.edu.uptc.api.DTO.*;
import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.ProductoRequest;
import co.edu.uptc.usecase.eliminarproducto.EliminarProductoUseCase;
import co.edu.uptc.usecase.guardarcategoria.GuardarCategoriaUseCase;
import co.edu.uptc.usecase.guardarproducto.GuardarProductoUseCase;
import co.edu.uptc.usecase.guardarvarianteproducto.GuardarVarianteProductoUseCase;
import co.edu.uptc.usecase.listarcategorias.ListarCategoriasUseCase;
import co.edu.uptc.usecase.listarproductos.ListarProductosUseCase;
import co.edu.uptc.usecase.modificarproducto.ModificarProductoUseCase;
import co.edu.uptc.usecase.obtenerproducto.ObtenerProductoUseCase;
import co.edu.uptc.usecase.solicitudfacturar.SolicitudFacturarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

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
    private final ObtenerProductoUseCase obtenerProductoUseCase;
    private final ModificarProductoUseCase modificarProductoUseCase;
    private final ListarCategoriasUseCase listarCategoriasUseCase;
    private final EliminarProductoUseCase eliminarProductoUseCase;
    private final SolicitudFacturarUseCase solicitudFacturarUseCase;

    public Mono<ServerResponse> listenGETListarProductos(ServerRequest serverRequest) {
        return ServerResponse.ok().body(listarProductosUseCase.action(), Producto.class);
    }
    public Mono<ServerResponse> listenDeleteEliminarProducto(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        return eliminarProductoUseCase.eliminarProducto(id)
                .then(ServerResponse.noContent().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));

    }
    public Mono<ServerResponse> listenGETObtenerProducto(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));

        return ServerResponse.ok().body(obtenerProductoUseCase.action(id), Producto.class).onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }
    public Mono<ServerResponse> listenPOSTModificarProducto(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        return serverRequest.bodyToMono(Producto.class)
                .flatMap(producto -> modificarProductoUseCase.modificarProducto(id, producto))
                .flatMap(producto -> ServerResponse.ok().bodyValue(producto))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }
    public Mono<ServerResponse> listenPostGuardarProducto(ServerRequest serverRequest) {

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
    public Mono listenGETListarCategorias(ServerRequest serverRequest) {
        return ServerResponse.ok().body(listarCategoriasUseCase.action(), Categoria.class);
    }

    public Mono<ServerResponse> listenPOSTGuardarCategoria(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(CategoriaDTO.class)
                .flatMap(categoria -> guardarCategoriaUseCase.action(categoriaMapper.toCategoria(categoria))
                .flatMap(categoria1 -> ServerResponse.ok().bodyValue(categoriaMapper.toCategoriaDTO(categoria1)))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage())));
    }
    public Mono<ServerResponse> listenPOSTSolicitudFacturar(ServerRequest serverRequest) {
        // Convertir el cuerpo de la petición a un Flux de ProductoRequest y luego a una lista
        Mono<List<ProductoRequest>> productosListMono = serverRequest.bodyToFlux(ProductoRequest.class)
                .collectList();

        return productosListMono.flatMap(productosList ->
                        // Ejecutar el método facturar (bloqueante) en un scheduler para evitar bloquear el hilo principal
                        Mono.fromCallable(() -> solicitudFacturarUseCase.facturar(productosList))
                                .subscribeOn(Schedulers.boundedElastic())
                                .flatMap(productPurchaseResponseList ->
                                        ServerResponse.ok().bodyValue(productPurchaseResponseList)
                                )
                )
                .onErrorResume(e ->
                        ServerResponse.badRequest().bodyValue("Error processing request: " + e.getMessage())
                );
    }

}
