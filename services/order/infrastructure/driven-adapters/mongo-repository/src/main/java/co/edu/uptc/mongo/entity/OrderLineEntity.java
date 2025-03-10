package co.edu.uptc.mongo.entity;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Document(collection = "order_line")
public class OrderLineEntity {

    private String id;
    private Integer idProduct;
    private String skuProduct;
    private int quantity;
    private double price;

}
