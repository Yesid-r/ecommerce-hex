package co.edu.uptc.mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Document(collection = "order")
public class OrderEntity {

    @Id
    private String id;
    private String reference;
    private BigDecimal totalAmount;
    private String customerId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
