package co.edu.uptc.model.order.gateways;

public record CustomerResponse (
        String id,
        String name,
        String email,
        String phone,
        String address
){
}
