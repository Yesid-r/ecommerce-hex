package co.edu.uptc.mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Document(collection = "customer")
public class CustomerEntity {

    @Id
    private String id;
    private String name;
    private String lastName;
    private String email;
    private AddressEntity address;
}
