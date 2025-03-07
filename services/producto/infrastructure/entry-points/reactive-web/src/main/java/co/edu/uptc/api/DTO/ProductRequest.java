package co.edu.uptc.api.DTO;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest (
        String nombre,
        String sku,
        String descripcion,
        String color,
        Integer categoriaId,
        BigDecimal precio,
        String size,
        Integer stock,
        List<String> imagenes,
        Boolean isActive
){
}
