package co.edu.uptc.model.order.dto;

public record AddressResponse(
        String street,
        String city,
        String state,
        String zipCode
) {
}
