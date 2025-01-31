package co.edu.uptc.api.DTO;

import java.math.BigDecimal;

public record ProductVariantRequest(
        Integer productId,
        String sku,
        String size,
        int quantity,
        BigDecimal priceOffset

) {
}
