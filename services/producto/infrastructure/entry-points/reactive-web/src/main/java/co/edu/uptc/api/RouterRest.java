package co.edu.uptc.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/v1/categoria"), handler::listenGETListarCategorias)
                .andRoute(POST("/api/v1/categoria"), handler::listenPOSTGuardarCategoria)
                .andRoute(POST("/api/v1/producto/"), handler::listenPostGuardarProducto)
                .andRoute(POST("/api/v1/product-variant/"), handler::listenPostGuardarVariante)
                .andRoute(GET("/api/v1/producto"), handler::listenGETListarProductos)
                .andRoute(GET("/api/v1/producto/{id}"), handler::listenGETObtenerProducto)
                .andRoute(PUT("/api/v1/producto/{id}"), handler::listenPOSTModificarProducto)
                .andRoute(DELETE("/api/v1/producto/{id}"), handler::listenDeleteEliminarProducto)
                .andRoute(POST("/api/v1/producto/purchase"), handler::listenPOSTSolicitudFacturar);


    }
}
