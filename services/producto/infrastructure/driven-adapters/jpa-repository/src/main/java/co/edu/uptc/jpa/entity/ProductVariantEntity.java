package co.edu.uptc.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

@Entity
public class ProductVariantEntity {

    @Id
    private Integer id;
    private String sku;
    private String size;
    private int quantity;
    private BigDecimal priceOffset;
    @OneToOne
    private ProductoEntity product;
}
