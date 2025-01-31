package co.edu.uptc.r2dbc.entity;

import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.producto.Producto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductoRepMapper {

    public Mono<Producto> toEntity(Mono<ProductoEntity> entity, Categoria categoria) {
        return entity.map(e -> Producto.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .descripcion(e.getDescripcion())
                .color(e.getColor())
                .precio(e.getPrecio())
                .categoria(categoria)
                .isActive(e.getIsActive())
                .build());
    }
    public ProductoEntity toModel(Producto producto) {
        return ProductoEntity.builder()
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .color(producto.getColor())
                .precio(producto.getPrecio())
                .idCategoria(producto.getCategoria().getId())
                .isActive(producto.isActive())
                .build();
    }


}
