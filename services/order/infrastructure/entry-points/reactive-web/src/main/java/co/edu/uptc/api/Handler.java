package co.edu.uptc.api;

import co.edu.uptc.api.request.OrderRequest;
import co.edu.uptc.usecase.order.CreateOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final CreateOrderUseCase createOrderUseCase;

    public Mono<ServerResponse> listenGetHello(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("Hello from the server");
    }

    public Mono<ServerResponse> listenPostSaveOrder(ServerRequest serverRequest) {

        Mono<OrderRequest> orderRequest = serverRequest.bodyToMono(OrderRequest.class).flatMap(Mono::just);
        //extraer el id cliente y List<Integer> del body
        return orderRequest.flatMap(order -> {
            createOrderUseCase.createOrder(order.customerId(), order.products());
            return ServerResponse.ok().bodyValue("Order created");
        });


    }

}
