package co.edu.uptc.r2dbc.entity;

import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.producto.Producto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductoRepMapper {

    public Mono<Producto> toModel(Mono<ProductoEntity> entity, Categoria categoria) {
        return entity.map(e -> Producto.builder()
                .id(e.getId())
                .sku(e.getSku())
                .nombre(e.getNombre())
                .descripcion(e.getDescripcion())
                .color(e.getColor())
                .precio(e.getPrecio())
                .categoria(categoria)
                .size(e.getSize())
                .stock(e.getStock())
                .imagenes(e.getImages())
                .isActive(e.getIsActive())
                .build());
    }
    public ProductoEntity toEntity(Producto producto) {
        return ProductoEntity.builder()
                .sku(producto.getSku())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .color(producto.getColor())
                .precio(producto.getPrecio())
                .idCategoria(producto.getCategoria().getId())
                .size(producto.getSize())
                .stock(producto.getStock())
                .images(producto.getImagenes())
                .isActive(producto.isActive())
                .build();
    }




}
