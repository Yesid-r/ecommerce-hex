package co.edu.uptc.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantEntity{

    @Id Integer id;
    Integer idProducto;
    String sku;
    String size;
    Integer quantity;
    BigDecimal priceOffset;
}
