package co.edu.uptc.api;

import co.edu.uptc.model.customer.Customer;
import co.edu.uptc.usecase.savecustomer.SaveCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
//private  final UseCase useCase;
//private  final UseCase2 useCase2;

    private final SaveCustomerUseCase saveCustomerUseCase;

    public Mono<ServerResponse> listenPostSaveCustomer(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(Customer.class)
                .flatMap(element -> saveCustomerUseCase.action(element))
                .flatMap(element -> ServerResponse.ok().bodyValue(element))
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }

}
