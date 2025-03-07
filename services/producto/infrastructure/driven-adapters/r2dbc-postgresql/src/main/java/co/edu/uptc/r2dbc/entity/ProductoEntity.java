package co.edu.uptc.r2dbc.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table("producto")
public class ProductoEntity {

    @Id Integer id;
    @Column("sku") String sku;
    @Column("nombre") String nombre;
    @Column("descripcion") String descripcion;
    @Column("color") String color;
    @Column("size") String size;
    @Column("precio")    BigDecimal precio;
    @Column("id_categoria") Integer idCategoria;
    @Column("stock") Integer stock;
    @Column("imagenes") List<String> images;
    @Column("is_active") Boolean isActive;


}
