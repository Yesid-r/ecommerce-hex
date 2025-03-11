package co.edu.uptc.api.request;

import co.edu.uptc.model.order.dto.ProductRequestDTO;

import java.util.List;

public record OrderRequest (
        String id,
        String date,
        String customerId,
        List<ProductRequestDTO> products
) {


}
