package co.edu.uptc.consumer.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
