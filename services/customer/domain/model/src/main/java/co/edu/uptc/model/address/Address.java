package co.edu.uptc.model.address;
import lombok.*;



@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}
