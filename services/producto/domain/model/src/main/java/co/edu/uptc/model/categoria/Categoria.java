package co.edu.uptc.model.categoria;
import co.edu.uptc.model.producto.Producto;
import lombok.*;

import java.util.List;
import java.util.Set;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    private Integer id;
    private String nombre;
    //private List<Producto> productos;

}
