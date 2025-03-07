package co.edu.uptc.model.producto;
import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.productvariant.ProductVariant;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private Integer id;
    private String sku;
    private String nombre;
    private String descripcion;
    private String color;
    private Categoria categoria;
    private BigDecimal precio;
    private List<String> imagenes;
    private int stock;
    private boolean isActive;

}
