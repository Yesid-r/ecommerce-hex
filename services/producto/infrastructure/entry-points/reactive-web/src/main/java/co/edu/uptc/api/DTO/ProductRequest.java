package co.edu.uptc.api.DTO;

import java.math.BigDecimal;

public record ProductRequest (
        String nombre,
        String descripcion,
        String color,
        Integer categoriaId,
        BigDecimal precio,
        Boolean isActive
){
}
