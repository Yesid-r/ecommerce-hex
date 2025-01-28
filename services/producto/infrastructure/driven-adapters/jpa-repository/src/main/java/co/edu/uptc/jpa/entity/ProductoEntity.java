package co.edu.uptc.jpa.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "producti")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private String color;
    private Integer categoryId;
    private BigDecimal precio;
    private boolean isActive;
    @OneToMany
    private ArrayList<ProductVariantEntity> variants;
}
