package co.edu.uptc.model.producto;
import co.edu.uptc.model.productvariant.ProductVariant;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
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
