package co.edu.uptc.api.DTO;

import java.math.BigDecimal;

public record ProductVariantRequest(
        Integer id_product,
        String sku,
        String size,
        int quantity,
        BigDecimal price_offset

) {
}
