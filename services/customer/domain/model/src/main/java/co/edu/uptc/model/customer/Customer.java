package co.edu.uptc.model.customer;
import co.edu.uptc.model.address.Address;
import lombok.*;
//import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private Address address;

}
