package co.edu.uptc.model.address.gateways;

import co.edu.uptc.model.address.Address;
import reactor.core.publisher.Mono;

public interface AddressRepository {

    Mono<Address> saveAddress(Address address);
}
