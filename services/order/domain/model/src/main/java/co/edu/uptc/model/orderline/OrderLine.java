package co.edu.uptc.model.orderline;
import co.edu.uptc.model.order.Order;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderLine {
    private String id;
    private Order order;
    private String productId;
    private String skuProduct;
    private int quantity;
    private BigDecimal price;
}
