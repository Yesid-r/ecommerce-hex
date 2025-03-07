package co.edu.uptc.api.DTO;

import co.edu.uptc.model.producto.Producto;
import org.springframework.stereotype.Service;

@Service
public class ProductoMapper {

    public Producto toProducto(ProductRequest productRequest) {
        return Producto.builder()
                .sku(productRequest.sku())
                .nombre(productRequest.nombre())
                .descripcion(productRequest.descripcion())
                .color(productRequest.color())
                .precio(productRequest.precio())
                .size(productRequest.size())
                .stock(productRequest.stock())
                .imagenes(productRequest.imagenes())
                .isActive(productRequest.isActive())
                .build();
    }
}
