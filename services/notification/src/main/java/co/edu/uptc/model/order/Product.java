package co.edu.uptc.model.order;

public record Product(
        Integer id,
        String nombre,
        String descripcion,
        Integer cantidad,
        Double precio
) {
}
