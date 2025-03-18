package co.edu.uptc.model.order;

import co.edu.uptc.model.order.dto.CustomerDTO;
import co.edu.uptc.model.order.dto.PaymentMethod;
import co.edu.uptc.model.order.dto.ProductPurchaseResponseDTO;


import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerDTO customer,
        List<ProductPurchaseResponseDTO> products
) {
}

