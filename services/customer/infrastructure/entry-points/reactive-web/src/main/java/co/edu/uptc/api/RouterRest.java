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
        return route(POST("/api/v1/customer"), handler::listenPostSaveCustomer)
                .andRoute(GET("/api/v1/customer"), handler::listenGETAllCustomerUseCase)
                .andRoute(GET("/api/v1/customer/{id}"), handler::listenGETCustomerUseCase)
                .andRoute(PUT("/api/v1/customer/{id}"), handler::listenPUTCustomerUseCase)
                .andRoute(DELETE("/api/v1/customer/{id}"), handler::listenDELETECustomerUseCase);
    }
}
