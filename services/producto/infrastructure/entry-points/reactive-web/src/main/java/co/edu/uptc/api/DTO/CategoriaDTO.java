package co.edu.uptc.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Integer id;
    private String nombre;
    private CategoriaDTO parent;
}
