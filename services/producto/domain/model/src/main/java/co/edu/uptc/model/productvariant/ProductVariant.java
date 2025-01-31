package co.edu.uptc.model.productvariant;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant {


    private Integer id;
    private Integer idProducto;
    private String sku;
    private String size;
    private int quantity;
    private BigDecimal priceOffset;

}
