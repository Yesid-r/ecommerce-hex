package co.edu.uptc.model.order.dto;

public record ProductPurchaseResponseDTO (
        Integer id,
        String nombre,
        String descripcion,
        Integer cantidad,
        Double precio

) {
}