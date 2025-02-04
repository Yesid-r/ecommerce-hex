package co.edu.uptc.api.DTO;

import co.edu.uptc.model.productvariant.ProductVariant;
import org.springframework.stereotype.Service;

@Service
public class ProductVarantMapper {
    public ProductVariant toProductVariant(ProductVariantRequest request){

        return ProductVariant.builder()
                .idProducto(request.id_product())
                .sku(request.sku())
                .size(request.size())
                .quantity(request.quantity())
                .priceOffset(request.price_offset())
                .build();

    }
}
