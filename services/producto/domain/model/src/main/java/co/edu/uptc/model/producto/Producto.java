package co.edu.uptc.model.producto;
import co.edu.uptc.model.productvariant.ProductVariant;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String color;
    private Integer categoryId;
    private BigDecimal precio;
    private boolean isActive;
    private ArrayList<ProductVariant> variants;

    public void addVariant(String sku, String size, int quantity, BigDecimal priceOffset) {
        variants.add(new ProductVariant(sku, size, quantity, priceOffset));

    }


}
