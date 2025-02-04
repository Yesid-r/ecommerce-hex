package co.edu.uptc.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table("product_variant")
public class ProductVariantEntity{

    @Id Integer id;
    @Column("id_producto")  Integer idProducto;
    @Column("sku") String sku;
    @Column("size") String size;
    @Column("quantity") Integer quantity;
    @Column("price_offset") BigDecimal priceOffset;
}
