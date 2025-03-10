package co.edu.uptc.model.producto;

public record ProductPurchaseResponse(
        Integer id,
        String nombre,
        String descripcion,
        Integer cantidad,
        Double precio

) {
}
