package co.edu.uptc.model.orderline;
import co.edu.uptc.model.order.Order;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    private String id;
    private Order order;
    private Integer productId;
    private String skuProduct;
    private int quantity;
    private BigDecimal price;
}
