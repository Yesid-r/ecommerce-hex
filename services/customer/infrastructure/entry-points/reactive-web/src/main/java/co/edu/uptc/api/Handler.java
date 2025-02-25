package co.edu.uptc.api;

import co.edu.uptc.model.customer.Customer;
import co.edu.uptc.usecase.deletecustomer.DeleteCustomerUseCase;
import co.edu.uptc.usecase.getallcustomer.GetAllCustomerUseCase;
import co.edu.uptc.usecase.getcustomer.GetCustomerUseCase;
import co.edu.uptc.usecase.savecustomer.SaveCustomerUseCase;
import co.edu.uptc.usecase.updatecustomer.UpdateCustomerUseCase;
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
    private final GetAllCustomerUseCase getAllCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    public Mono<ServerResponse> listenGETAllCustomerUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().body(getAllCustomerUseCase.action(), Customer.class);
    }
    public Mono<ServerResponse> listenGETCustomerUseCase(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return getCustomerUseCase.getCustomer(id)
                .flatMap(element -> ServerResponse.ok().bodyValue(element))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> listenPUTCustomerUseCase(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return getCustomerUseCase.getCustomer(id)
                .flatMap(customer -> serverRequest.bodyToMono(Customer.class)
                        .flatMap(element -> updateCustomerUseCase.updateCustomer(id, element)))
                .flatMap(element -> ServerResponse.ok().bodyValue(element))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }


    public Mono<ServerResponse> listenDELETECustomerUseCase(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return deleteCustomerUseCase.deleteCustomer(id)
                .flatMap(element -> ServerResponse.noContent().build())
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }
    public Mono<ServerResponse> listenPostSaveCustomer(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(Customer.class)
                .flatMap(element -> saveCustomerUseCase.action(element))
                .flatMap(element -> ServerResponse.ok().bodyValue(element))
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }

}
