package co.edu.uptc.mongo.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Document
public class AddressEntity {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}
