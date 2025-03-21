package co.edu.uptc.model.order;
import co.edu.uptc.model.orderline.OrderLine;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private String reference;
    private BigDecimal totalAmount;
    private String customerId;
    private String status;
    private List<OrderLine> orderLines;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
