package co.edu.uptc.model.order;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Order {

    private String id;
    private String reference;
    private BigDecimal totalAmount;
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
