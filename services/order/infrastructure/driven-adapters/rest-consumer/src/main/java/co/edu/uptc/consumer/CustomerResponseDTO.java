package co.edu.uptc.consumer;

import co.edu.uptc.consumer.DTO.AddressResponseDTO;
import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private AddressResponseDTO address;

}