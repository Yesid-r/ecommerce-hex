package co.edu.uptc.model.categoria;
import lombok.*;



@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    private Integer id;
    private String nombre;
    private Categoria parent;
}
