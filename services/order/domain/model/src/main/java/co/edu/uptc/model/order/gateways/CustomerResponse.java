package co.edu.uptc.model.order.gateways;

import co.edu.uptc.model.order.dto.AddressResponse;

public record CustomerResponse (
        String id,
        String name,
        String lastName,
        String email,
        String phone,
        AddressResponse address
){
}
