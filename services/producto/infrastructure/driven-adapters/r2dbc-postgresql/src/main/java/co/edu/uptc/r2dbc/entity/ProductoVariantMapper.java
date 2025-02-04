package co.edu.uptc.r2dbc.entity;

import co.edu.uptc.model.productvariant.ProductVariant;
import org.springframework.stereotype.Service;

@Service
public class ProductoVariantMapper {

    public ProductVariantEntity toEntity(ProductVariant productVariant) {
        return ProductVariantEntity.builder()
                .idProducto(productVariant.getIdProducto())
                .sku(productVariant.getSku())
                .size(productVariant.getSize())
                .quantity(productVariant.getQuantity())
                .priceOffset(productVariant.getPriceOffset())
                .build();
    }
    public ProductVariant toDomain(ProductVariantEntity productVariantEntity) {
        return ProductVariant.builder()
                .id(productVariantEntity.getId())
                .idProducto(productVariantEntity.getIdProducto())
                .sku(productVariantEntity.getSku())
                .size(productVariantEntity.getSize())
                .quantity(productVariantEntity.getQuantity())
                .priceOffset(productVariantEntity.getPriceOffset())
                .build();
    }

}
