package co.edu.uptc.model.productvariant;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductVariant {

    private String sku;
    private String size;
    private int quantity;
    private BigDecimal priceOffset;

}
